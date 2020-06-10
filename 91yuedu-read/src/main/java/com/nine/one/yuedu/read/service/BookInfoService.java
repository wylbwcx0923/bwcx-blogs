package com.nine.one.yuedu.read.service;

import com.github.pagehelper.PageInfo;
import com.nine.one.yuedu.read.entity.BookInfo;
import com.nine.one.yuedu.read.entity.vo.BookInfoVO;

import java.util.Map;

/**
 * @author wangyuliang
 * <p>
 * 图书服务
 */
public interface BookInfoService {


    /**
     * 添加图书
     *
     * @param bookInfo
     */
    void addBookInfo(BookInfo bookInfo);


    /**
     * 删除图书
     *
     * @param id
     */
    void delBookInfoById(Integer id);


    /**
     * 修改图书信息
     *
     * @param bookInfo
     */
    void updateBookInfo(BookInfo bookInfo);


    /**
     * 根据ID查询图书详情
     *
     * @param id
     * @return
     */
    BookInfo findById(Integer id);


    /**
     * 根据不同的条件分页查询书籍
     *
     * @param pageIndex 开始页
     * @param pageSize  每页显示的数量
     * @param privoder  作品提供者
     * @param bookName  书的名字
     * @param author    作者
     * @param id        图书Id
     * @param category  图书类型
     * @param valid     是否上线
     * @return
     */
    PageInfo<BookInfoVO> getBookInfo4PageAndParam(Integer pageIndex,
                                                  Integer pageSize,
                                                  String privoder,
                                                  String bookName,
                                                  String author,
                                                  Integer id,
                                                  String category,
                                                  Integer valid);

    /**
     * 通过书的ID查询书的信息
     *
     * @param id
     * @return
     */
    Map<String, Object> findBookInfoById(Integer id);


    /**
     * 下载一本书的内容
     *
     * @param bookId
     */
    String downLoadBook(Integer bookId);

}
