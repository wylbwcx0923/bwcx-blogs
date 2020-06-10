package com.nine.one.yuedu.read.cp.impl;

import com.nine.one.yuedu.read.config.ApiConstant;
import com.nine.one.yuedu.read.cp.BiKanService;
import com.nine.one.yuedu.read.entity.BookInfo;
import com.nine.one.yuedu.read.entity.ChapterInfo;
import com.nine.one.yuedu.read.entity.cp.BiKanBookInfo;
import com.nine.one.yuedu.read.entity.cp.BiKanVolume;
import com.nine.one.yuedu.read.mapper.BookInfoMapper;
import com.nine.one.yuedu.read.mapper.ChapterInfoMapper;
import com.nine.one.yuedu.read.mapper.CpAuthBookMapper;
import com.nine.one.yuedu.read.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "biKanService")
public class BiKanServiceImpl implements BiKanService {

    @Autowired
    private CpAuthBookMapper cpAuthBookMapper;
    @Autowired
    private BookInfoMapper bookInfoMapper;
    @Autowired
    private ChapterInfoMapper chapterInfoMapper;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public Map<String, Object> getBookIds(String identity) {
        if ("FF483D1FF591898A9942916050D2CA3F".equals(identity)) {
            identity = "10";
        }
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        List<Integer> bookIds = cpAuthBookMapper.getBookIdListByCpAuthId(Integer.valueOf(identity));
        for (Integer bookId : bookIds) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", bookId);
            list.add(map);
        }
        result.put("books", list);
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }

    @Override
    public BiKanBookInfo getBookInfoByBookId(Integer bookId) {
        BookInfo bookInfo = bookInfoMapper.selectByPrimaryKey(bookId);
        logger.info("抓取图书名称为:" + bookInfo.getBookName());
        BiKanBookInfo biKanBookInfo = new BiKanBookInfo();
        biKanBookInfo.setId(bookInfo.getId());
        biKanBookInfo.setName(bookInfo.getBookName());
        biKanBookInfo.setAliasName("");
        biKanBookInfo.setForeignName("");
        biKanBookInfo.setAuthor(bookInfo.getAuthor());
        biKanBookInfo.setTranslator("");
        biKanBookInfo.setBriefDescription(bookInfo.getDescription());
        biKanBookInfo.setPromptDescription("");
        biKanBookInfo.setKeywords("");
        biKanBookInfo.setCoverImg(bookInfo.getPicUrl());
        biKanBookInfo.setBookType(2);
        biKanBookInfo.setBookMediaType(1);
        biKanBookInfo.setProgress(bookInfo.getCompleteState() == 0 ? 1 : 2);
        biKanBookInfo.setChapterCount(bookInfo.getLastChapterId() + "");
        biKanBookInfo.setCpUpdateTime(System.currentTimeMillis());
        biKanBookInfo.setCategory(bookInfo.getCategory());
        return biKanBookInfo;
    }

    @Override
    public BiKanVolume getBookStructureByBookId(Integer bookId) {
        Example example = new Example(ChapterInfo.class);
        example.createCriteria().andEqualTo("bookId", bookId);
        List<ChapterInfo> chapterInfos = chapterInfoMapper.selectByExample(example);

        BiKanVolume biKanVolume=new BiKanVolume();
        List<BiKanVolume.VolumesBean> volumesBeans = new ArrayList<>();
        BiKanVolume.VolumesBean volumesBean = new BiKanVolume.VolumesBean();
        volumesBean.setVolumeId(bookId);
        volumesBean.setVolumeTitle("正文");
        volumesBean.setVolumeType(1);

        List<BiKanVolume.VolumesBean.ChaptersBean> chaptersBeans = new ArrayList<>();
        for (ChapterInfo chapterInfo : chapterInfos) {
            BiKanVolume.VolumesBean.ChaptersBean chaptersBean = new BiKanVolume.VolumesBean.ChaptersBean();
            chaptersBean.setChapterId(chapterInfo.getId());
            chaptersBean.setVip(0);
            chaptersBean.setTitle(chapterInfo.getChapterName());
            chaptersBean.setChapterNumber(chapterInfo.getChapterId());
            chaptersBeans.add(chaptersBean);
        }
        volumesBean.setChapterCount(chapterInfos.size());
        volumesBean.setVolumeNumber(1);
        volumesBean.setChapters(chaptersBeans);
        volumesBeans.add(volumesBean);

        biKanVolume.setVolumes(volumesBeans);
        return biKanVolume;
    }

    @Override
    public Map<String, Object> getChapterContentByBookIdAndChapterId(Integer bookId, Integer chapterId) {
        ChapterInfo chapterInfo = chapterInfoMapper.selectByPrimaryKey(chapterId);
        final String BOOK_URL = String.format("%s/%s/%s/%s.txt", ApiConstant.Config.BASE_PATH, "booktxt", bookId, chapterInfo.getChapterId());
        String bookContent = HttpClientUtil.doGet(BOOK_URL);
        Map<String, Object> map = new HashMap<>();
        map.put("id", chapterInfo.getId());
        map.put("title", chapterInfo.getChapterName());
        map.put("volumeId", bookId);
        map.put("bookId", bookId);
        map.put("content", bookContent);
        map.put("digression", "");
        logger.info("必看小说获取书籍章节内容:" + bookId + "\t章节ID:" + chapterId);
        return map;
    }
}
