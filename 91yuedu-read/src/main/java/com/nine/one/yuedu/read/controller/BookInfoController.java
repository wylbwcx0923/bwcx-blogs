package com.nine.one.yuedu.read.controller;

import com.nine.one.yuedu.read.config.ApiConstant;
import com.nine.one.yuedu.read.config.JXResult;
import com.nine.one.yuedu.read.entity.BookInfo;
import com.nine.one.yuedu.read.service.BookInfoService;
import com.nine.one.yuedu.read.utils.AliyunOSSUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Api(tags = "图书接口", value = "图书接口")
@RequestMapping(value = "/91yuedu/bookInfo")
@RestController
public class BookInfoController {


    @Resource(name = "bookInfoService")
    private BookInfoService bookInfoService;

    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;

    //打印日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @PostMapping(value = "/bookUrl/upload")
    @ApiOperation(value = "上传封面图", notes = "上传封面图")
    public JXResult uploadHead(@RequestParam("file") @ApiParam(value = "上传的文件") MultipartFile uploadFile) {
        AliyunOSSUtil.FileUploadResult uploadResult = aliyunOSSUtil.upload(uploadFile);
        return new JXResult(true, ApiConstant.StatusCode.OK, "上传成功", uploadResult);
    }

    /**
     * 添加图书
     *
     * @param bookInfo
     */
    @PostMapping(value = "/add")
    @ApiOperation(value = "添加图书", notes = "添加图书")
    public JXResult addBookInfo(@ApiParam(value = "图书对象", required = true)
                                @RequestBody BookInfo bookInfo) {
        bookInfoService.addBookInfo(bookInfo);
        return new JXResult(true, ApiConstant.StatusCode.OK, "添加成功");
    }


    /**
     * 删除图书
     *
     * @param id
     */
    @DeleteMapping(value = "/del/{id}")
    @ApiOperation(value = "删除图书", notes = "删除图书")
    public JXResult delBookInfoById(@ApiParam(value = "图书ID") @PathVariable Integer id) {
        bookInfoService.delBookInfoById(id);
        return new JXResult(true, ApiConstant.StatusCode.OK, "删除成功");
    }


    /**
     * 修改图书信息
     *
     * @param bookInfo
     */
    @PutMapping(value = "/update")
    @ApiOperation(value = "修改图书信息", notes = "修改图书信息")
    public JXResult updateBookInfo(@ApiParam(value = "图书对象", required = true)
                                   @RequestBody BookInfo bookInfo) {
        bookInfoService.updateBookInfo(bookInfo);
        return new JXResult(true, ApiConstant.StatusCode.OK, "修改成功");
    }


    /**
     * 根据ID查询图书详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/find/{id}")
    @ApiOperation(value = "通过Id查询图书详情", notes = "通过Id查询图书详情")
    public JXResult findById(@ApiParam(value = "图书ID") @PathVariable Integer id) {
        return new JXResult(true, ApiConstant.StatusCode.OK, "请求成功", bookInfoService.findById(id));
    }


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
    @GetMapping(value = "/list/{pageIndex}/{pageSize}")
    @ApiOperation(value = "分页根据条件查询书籍列表", notes = "分页根据条件查询书籍列表")
    public JXResult getBookInfo4PageAndParam(@ApiParam(value = "页码") @PathVariable Integer pageIndex,
                                             @ApiParam(value = "每页显示的数量") @PathVariable Integer pageSize,
                                             @RequestParam(required = false, value = "privoder") @ApiParam(value = "书籍提供者") String privoder,
                                             @RequestParam(required = false, value = "bookName") @ApiParam(value = "书名") String bookName,
                                             @RequestParam(required = false, value = "author") @ApiParam(value = "作者") String author,
                                             @RequestParam(required = false, value = "id") @ApiParam(value = "书籍id") Integer id,
                                             @RequestParam(required = false, value = "category") @ApiParam(value = "书籍类型") String category,
                                             @RequestParam(required = false, value = "valid") @ApiParam(value = "是否上架") Integer valid) {

        return new JXResult(true, ApiConstant.StatusCode.OK, "请求成功", bookInfoService.getBookInfo4PageAndParam(pageIndex, pageSize, privoder, bookName, author, id, category, valid));

    }

    @GetMapping(value = "/find/bookInfo/{id}")
    @ApiOperation(value = "通过Id查询图书和章节详情", notes = "通过Id查询图书和章节详情")
    public JXResult findBookInfoById(@ApiParam(value = "图书ID") @PathVariable Integer id) {
        return new JXResult(true, ApiConstant.StatusCode.OK, "请求成功", bookInfoService.findBookInfoById(id));
    }

    @GetMapping(value = "/downbook/{bookId}")
    @ApiOperation(value = "下载一本书")
    public void downBookById(@PathVariable @ApiParam(value = "书籍ID") Integer bookId, HttpServletResponse response) {
        String fileName = bookId + ".txt";
        OutputStream os = null;
        try {
            response.reset();
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes(), "ISO8859-1"));
            String sb = bookInfoService.downLoadBook(bookId);

            byte[] bytes = sb.getBytes("UTF-8");
            os = response.getOutputStream();
            // 将字节流传入到响应流里,响应到浏览器
            os.write(bytes);
            os.close();
        } catch (Exception ex) {
            logger.error("导出失败:", ex);
            throw new RuntimeException("导出失败");
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
            } catch (IOException ioEx) {
                logger.error("导出失败:", ioEx);
            }
        }
    }

}
