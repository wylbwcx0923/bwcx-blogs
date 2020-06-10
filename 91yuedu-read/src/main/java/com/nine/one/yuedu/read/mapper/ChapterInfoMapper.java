package com.nine.one.yuedu.read.mapper;

import com.nine.one.yuedu.read.entity.ChapterInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.MyMapper;

import java.util.List;

public interface ChapterInfoMapper extends MyMapper<ChapterInfo> {

    /**
     * 根据书籍的ID删除章节
     * @param bookId
     * @return
     */
    int delByBookId(@Param("bookId") Integer bookId);

    Integer selectMaxSortByBookId(@Param("bookId") Integer bookId);

    List<ChapterInfo> selectChapterListByBookId(@Param("bookId") Integer bookId);

}
