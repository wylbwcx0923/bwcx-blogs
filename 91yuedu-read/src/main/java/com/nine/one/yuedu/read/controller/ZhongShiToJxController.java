package com.nine.one.yuedu.read.controller;

import com.nine.one.yuedu.read.config.ApiConstant;
import com.nine.one.yuedu.read.config.JXResult;
import com.nine.one.yuedu.read.cp.ZhongShiToNxstoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(value = "报联中视到景像后台的书籍同步接口",tags = "报联中视到景像后台的书籍同步接口")
@RequestMapping(value = "/91yuedu/cp/nxstory/")
public class ZhongShiToJxController {

    @Resource(name = "zhongShiToNxstoryService")
    private ZhongShiToNxstoryService zhongShiToNxstoryService;


    @GetMapping(value = "/book")
    @ApiOperation(value = "抓取书籍")
    public JXResult getBook() {
        zhongShiToNxstoryService.syncBookToNxstory();
        return new JXResult(true, ApiConstant.StatusCode.OK, "抓取成功");
    }

    @GetMapping(value = "/chapter")
    @ApiOperation(value = "抓取章节")
    public JXResult getChapter() {
        zhongShiToNxstoryService.syncChapter();
        return new JXResult(true, ApiConstant.StatusCode.OK, "抓取成功");
    }


}
