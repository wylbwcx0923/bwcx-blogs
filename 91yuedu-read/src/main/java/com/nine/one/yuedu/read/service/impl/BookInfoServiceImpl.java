package com.nine.one.yuedu.read.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nine.one.yuedu.read.config.ApiConstant;
import com.nine.one.yuedu.read.entity.BookInfo;
import com.nine.one.yuedu.read.entity.ChapterInfo;
import com.nine.one.yuedu.read.entity.vo.BookInfoVO;
import com.nine.one.yuedu.read.mapper.BookInfoMapper;
import com.nine.one.yuedu.read.mapper.ChapterInfoMapper;
import com.nine.one.yuedu.read.service.BookInfoService;
import com.nine.one.yuedu.read.service.ChapterService;
import com.nine.one.yuedu.read.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "bookInfoService")
public class BookInfoServiceImpl implements BookInfoService {

    //打印日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookInfoMapper bookInfoMapper;
    @Autowired
    private ChapterInfoMapper chapterInfoMapper;
    @Resource(name = "chapterService")
    private ChapterService chapterService;

    @Override
    public void addBookInfo(BookInfo bookInfo) {
        //获得当前数据库中最大的ID
        Integer maxId = bookInfoMapper.getCurrentMaxId();
        //这本书的ID,让原来的ID+1
        bookInfo.setId(maxId + 1);
        //字数在上传章节以后要变化,现在先设置为0
        bookInfo.setWords(0);
        //上架下架,刚上传完毕,默认下架状态
        bookInfo.setValid(0);
        //书的创建时间
        bookInfo.setCreateTime(new Date());
        //书籍的更新时间
        bookInfo.setUpdateTime(new Date());
        //阅读人数
        bookInfo.setVisitCount(0);
        //是否允许搜索,默认不允许
        bookInfo.setOpenSearch(0);
        //上传
        bookInfoMapper.insertSelective(bookInfo);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delBookInfoById(Integer id) {
        //删除书籍,需要删除这本书所有章节
        chapterInfoMapper.delByBookId(id);
        //删除这本书的详情
        bookInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateBookInfo(BookInfo bookInfo) {
        bookInfo.setUpdateTime(new Date());
        bookInfoMapper.updateByPrimaryKeySelective(bookInfo);
    }

    @Override
    public BookInfo findById(Integer id) {
        BookInfo bookInfo = bookInfoMapper.selectByPrimaryKey(id);
        //如果有人查看,就让阅读次数+1
        bookInfo.setVisitCount(bookInfo.getValid() + 1);
        bookInfoMapper.updateByPrimaryKeySelective(bookInfo);
        return bookInfo;
    }

    @Override
    public PageInfo<BookInfoVO> getBookInfo4PageAndParam(Integer pageIndex,
                                                         Integer pageSize,
                                                         String privoder,
                                                         String bookName,
                                                         String author,
                                                         Integer id,
                                                         String category,
                                                         Integer valid) {
        PageHelper.startPage(pageIndex, pageSize);
        List<BookInfoVO> bookInfos = bookInfoMapper.selectBookInfo4PageAndParam(privoder, bookName, author, id, category, valid);
        PageInfo<BookInfoVO> bookInfoPageInfo = new PageInfo<>(bookInfos);
        logger.info("分页查询的数据为:", bookInfoPageInfo);
        return bookInfoPageInfo;
    }

    @Override
    public Map<String, Object> findBookInfoById(Integer id) {
        BookInfo bookInfo = this.findById(id);
        //获得最新的章节
        Integer lastChapterId = bookInfo.getLastChapterId();
        Map<String, Object> lastChapterInfo = chapterService.getChapterInfoByBookIdAndChapterId(id, lastChapterId, "H5");
        Map<String, Object> map = new HashMap<>();
        map.put("bookInfo", bookInfo);
        map.put("lastChapterInfo", lastChapterInfo);
        return map;
    }

    @Override
    public String downLoadBook(Integer bookId) {
        Example example = new Example(ChapterInfo.class);
        example.createCriteria().andEqualTo("bookId", bookId);
        List<ChapterInfo> chapterInfos = chapterInfoMapper.selectByExample(example);
        String bookContent = null;
        if (chapterInfos != null && chapterInfos.size() > 0) {
            StringBuilder bookBuffer = new StringBuilder();
            for (ChapterInfo chapterInfo : chapterInfos) {
                final String URL = String.format("%s/%s/%s/%s.txt", ApiConstant.Config.BASE_PATH, "booktxt", bookId, chapterInfo.getChapterId());
                String chapterContent = HttpClientUtil.doGet(URL);
                bookContent = bookBuffer.append("###").append(chapterInfo.getChapterName()).append("\n").append(chapterContent).toString();
            }

        }
        return bookContent;
    }

    @Override
    public List<BookInfo> getBookInfos() {
        List<BookInfo> bookInfos = bookInfoMapper.selectAll();
        return bookInfos;
    }
}
