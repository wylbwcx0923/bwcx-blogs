package com.nine.one.yuedu.read.cp.impl;

import com.nine.one.yuedu.read.config.ApiConstant;
import com.nine.one.yuedu.read.cp.ZhongShiToNxstoryService;
import com.nine.one.yuedu.read.entity.BookInfo;
import com.nine.one.yuedu.read.entity.ChapterInfo;
import com.nine.one.yuedu.read.entity.CsBookinfo;
import com.nine.one.yuedu.read.entity.CsChapterinfo;
import com.nine.one.yuedu.read.mapper.BookInfoMapper;
import com.nine.one.yuedu.read.mapper.ChapterInfoMapper;
import com.nine.one.yuedu.read.mapper.CsBookinfoMapper;
import com.nine.one.yuedu.read.mapper.CsChapterinfoMapper;
import com.nine.one.yuedu.read.utils.AliyunOSSUtil;
import com.nine.one.yuedu.read.utils.FileOptionUtil;
import com.nine.one.yuedu.read.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service(value = "zhongShiToNxstoryService")
public class ZhongShiToNxstoryServiceImpl implements ZhongShiToNxstoryService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookInfoMapper bookInfoMapper;
    @Autowired
    private ChapterInfoMapper chapterInfoMapper;
    @Autowired
    private CsBookinfoMapper csBookinfoMapper;
    @Autowired
    private FileOptionUtil fileOptionUtil;
    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;
    @Autowired
    private CsChapterinfoMapper csChapterinfoMapper;


    @Override
    public void syncBookToNxstory() {
        //获取所有91阅读网所有的书籍
        List<BookInfo> bookInfos = bookInfoMapper.selectAll();
        if (bookInfos != null && bookInfos.size() > 0) {
            for (BookInfo bookInfo : bookInfos) {
                //看这本书在景像后台有没有
                Example exampleCsBook = new Example(CsBookinfo.class);
                exampleCsBook.createCriteria().andEqualTo("cpid", 1056).andEqualTo("cpbookid", bookInfo.getId());
                List<CsBookinfo> csBookinfos = csBookinfoMapper.selectByExample(exampleCsBook);
                if (csBookinfos == null || csBookinfos.size() <= 0) {
                    logger.info("这本书没有被抓取过");
                    //书籍同步
                    CsBookinfo csBookinfoNew = packageCsbook(bookInfo);
                    //入库
                    csBookinfoMapper.insertSelective(csBookinfoNew);
                    logger.info("图书信息添加成功");
                } else {
                    CsBookinfo csBookinfoNew = packageCsbook(bookInfo);
                    csBookinfoNew.setId(csBookinfos.get(0).getId());
                    csBookinfoMapper.updateByPrimaryKey(csBookinfoNew);
                    logger.info("这本书已经被抓取过,内容更新");


                }
            }
        }
    }

    @Override
    public void syncChapter() {
        Example exampleCsBook = new Example(CsBookinfo.class);
        exampleCsBook.createCriteria().andEqualTo("cpid", 1056);
        List<CsBookinfo> csBookinfos = csBookinfoMapper.selectByExample(exampleCsBook);
        for (CsBookinfo csBookinfo : csBookinfos) {
            //根据91阅读这边的书籍ID查询章节列表
            Example example = new Example(ChapterInfo.class);
            example.createCriteria().andEqualTo("bookId", csBookinfo.getCpbookid());
            List<ChapterInfo> chapterInfos = chapterInfoMapper.selectByExample(example);
            for (ChapterInfo chapterInfo : chapterInfos) {
                //查询这本书的章节,在景像的数据库存在吗
                Example exampleCsNxChapter = new Example(CsChapterinfo.class);
                exampleCsNxChapter.createCriteria()
                        .andEqualTo("cpbookid", chapterInfo.getBookId())
                        .andEqualTo("cpchapterid", chapterInfo.getId());
                CsChapterinfo csChapterFlag = csChapterinfoMapper.selectOneByExample(exampleCsNxChapter);
                if (csChapterFlag == null) {
                    logger.info("这个章节没有抓取过");
                    CsChapterinfo csChapterinfo = new CsChapterinfo();
                    csChapterinfo.setName(chapterInfo.getChapterName());
                    csChapterinfo.setBookid(csBookinfo.getId());
                    csChapterinfo.setCpbookid(Long.valueOf(chapterInfo.getBookId()));
                    csChapterinfo.setCpchapterid(Long.valueOf(chapterInfo.getId()));
                    csChapterinfo.setVolumnid(0);
                    csChapterinfo.setSort(chapterInfo.getChapterId());
                    csChapterinfo.setSize(chapterInfo.getWords());
                    csChapterinfo.setCreateTime(new Date());
                    csChapterinfo.setStatus(1);
                    csChapterinfo.setIsvip(1);
                    logger.info("章节入库");
                    csChapterinfoMapper.insertSelective(csChapterinfo);
                    logger.info(chapterInfo.getChapterName() + "抓取成功");
                }

            }
            //抓取书籍的内容
            getChapterContentByBookId(csBookinfo.getId());
        }
    }

    private void getChapterContentByBookId(Integer csBookId) {
        //查询这本书的章节列表
        Example example = new Example(CsChapterinfo.class);
        example.createCriteria().andEqualTo("bookid", csBookId);
        List<CsChapterinfo> csChapterinfos = csChapterinfoMapper.selectByExample(example);
        for (CsChapterinfo csChapterinfo : csChapterinfos) {
            //看文件夹存在吗,创建并抓取
            File fileDir = new File("/data2/nfs/www/booktxt/" + csBookId + "/" + csChapterinfo.getBookid());
            if (!fileDir.exists() || !fileDir.isDirectory()) {
                //新建出文件夹
                fileDir.mkdirs();
            }
            //开始抓取章节内容
            final String URL = String.format("%s/%s/%s/%s.txt", ApiConstant.Config.BASE_PATH, "booktxt", csChapterinfo.getCpbookid(), csChapterinfo.getSort());
            String bookContent = HttpClientUtil.doGet(URL);

            File file = new File("/data2/nfs/www/booktxt/" + csBookId + "/" + csChapterinfo.getId() + ".txt");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                //如果已经存在,就直接
                continue;
            }
            try {
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(bookContent);
                logger.info(csChapterinfo.getName() + ":章节内容保存成功,保存成功!");
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            logger.info("章节抓取成功");
        }
    }


    private CsBookinfo packageCsbook(BookInfo bookInfo) {
        CsBookinfo csBookinfo = new CsBookinfo();
        csBookinfo.setName(bookInfo.getBookName());
        csBookinfo.setArticlename(bookInfo.getBookName());
        csBookinfo.setAuthor(bookInfo.getAuthor());
        //获取网络图片
        File tempFile = fileOptionUtil.getFileByUrl(bookInfo.getPicUrl());
        MultipartFile multipartFile = fileOptionUtil.file2MultipartFile(tempFile);
        //上传到北京区景像的OSS
        AliyunOSSUtil.FileUploadResult uploadResult = aliyunOSSUtil.uploadFileToNxstory(multipartFile);
        String picUrl = uploadResult.getName().replace("http://res.nxstory.com/cover/", "");
        csBookinfo.setCover(picUrl);
        csBookinfo.setCpid(1056);
        csBookinfo.setCpbookid(Long.valueOf(bookInfo.getId()));
        csBookinfo.setKeyword(bookInfo.getBookName());
        csBookinfo.setCategory(0);
        csBookinfo.setCategoryname(bookInfo.getCategory());
        csBookinfo.setWords(bookInfo.getWords());
        csBookinfo.setSerial(bookInfo.getCompleteState());
        csBookinfo.setStatus(1);
        csBookinfo.setReadtype(false);
        csBookinfo.setAddtime(new Date());
        csBookinfo.setUpdatetime(new Date());
        csBookinfo.setChargetype(0);
        csBookinfo.setUnitprice(0);
        csBookinfo.setTotalprice(0);
        csBookinfo.setIntro(bookInfo.getDescription());
        return csBookinfo;
    }
}
