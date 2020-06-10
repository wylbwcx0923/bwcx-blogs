package com.nine.one.yuedu.read.mapper;

import com.nine.one.yuedu.read.entity.BookInfo;
import com.nine.one.yuedu.read.entity.vo.BookInfoVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.MyMapper;

import java.util.List;

public interface BookInfoMapper extends MyMapper<BookInfo> {

    /**
     * 获得当前最大的书的ID
     *
     * @return
     */
    Integer getCurrentMaxId();

    /**
     * 根据不同参数查询图书列表
     * @param privoder
     * @param bookName
     * @param author
     * @param id
     * @param category
     * @param valid
     * @return
     */
    List<BookInfoVO> selectBookInfo4PageAndParam(
            @Param("privoder") String privoder,
            @Param("bookName") String bookName,
            @Param("author") String author,
            @Param("id") Integer id,
            @Param("category") String category,
            @Param("valid") Integer valid);

}
