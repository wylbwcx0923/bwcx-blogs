package com.nine.one.yuedu.read.controller;

import com.nine.one.yuedu.read.config.ApiConstant;
import com.nine.one.yuedu.read.config.JXResult;
import com.nine.one.yuedu.read.entity.UserAuthor;
import com.nine.one.yuedu.read.service.UserAuthorService;
import com.nine.one.yuedu.read.utils.AliyunOSSUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@Api(tags = "管理员和作者接口", value = "管理员和作者接口")
@RequestMapping(value = "/91yuedu/userAuthor")
public class UserAuthorController {

    @Resource(name = "userAuthorService")
    private UserAuthorService userAuthorService;

    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;

    @PostMapping(value = "/head/upload")
    @ApiOperation(value = "上传头像", notes = "上传头像")
    public JXResult uploadHead(@RequestParam("file") @ApiParam(value = "上传的文件") MultipartFile uploadFile) {
        AliyunOSSUtil.FileUploadResult uploadResult = aliyunOSSUtil.upload(uploadFile);
        return new JXResult(true, ApiConstant.StatusCode.OK, "上传成功", uploadResult);
    }


    @PostMapping(value = "/add")
    @ApiOperation(value = "作者注册", notes = "作者注册")
    public JXResult addUserAuthor(@RequestBody @ApiParam(value = "作者对象", required = true) UserAuthor userAuthor) {

        userAuthorService.register(userAuthor);
        return new JXResult(true, ApiConstant.StatusCode.OK, "注册成功");
    }

    @DeleteMapping(value = "/del/{id}")
    @ApiOperation(value = "根据id删除作者接口", notes = "根据id删除作者接口")
    public JXResult delUserAuthorById(@PathVariable @ApiParam(value = "id") Integer id) {
        userAuthorService.delUserAuthorById(id);
        return new JXResult(true, ApiConstant.StatusCode.OK, "删除成功");
    }

    @PutMapping(value = "/update")
    @ApiOperation(value = "修改作者信息接口", notes = "修改作者信息接口")
    public JXResult updateUserAuthor(@RequestBody @ApiParam(value = "作者对象", required = true) UserAuthor userAuthor) {
        userAuthorService.updateUserAuthor(userAuthor);
        return new JXResult(true, ApiConstant.StatusCode.OK, "修改成功");

    }

    @GetMapping(value = "/find/{id}")
    @ApiOperation(value = "根据id查询作者接口", notes = "根据id查询作者接口")
    public JXResult findById(@PathVariable @ApiParam(value = "id") Integer id) {
        UserAuthor userAuthor = userAuthorService.findById(id);
        return new JXResult(true, ApiConstant.StatusCode.OK, "请求成功", userAuthor);
    }

    @GetMapping(value = "/list/{pageIndex}/{pageSize}")
    @ApiOperation(value = "分页获取作者列表", notes = "分页获取作者列表")
    public JXResult getUserAuthorsByPage(
            @PathVariable @ApiParam(value = "第几页") Integer pageIndex
            , @PathVariable @ApiParam(value = "每页显示数量") Integer pageSize
            , @RequestParam(value = "nikeName", required = false) @ApiParam(value = "笔名") String nikeName) {

        return new JXResult(true, ApiConstant.StatusCode.OK, "请求成功", userAuthorService.getUserAuthorsByPage(pageIndex, pageSize, nikeName));
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "作者或者管理员登录接口", notes = "作者或者管理员登录接口")
    public JXResult login(@RequestParam(value = "username") @ApiParam(value = "用户名", required = true) String username,
                          @RequestParam(value = "password") @ApiParam(value = "密码", required = true) String password) {
        Map<String, Object> login = userAuthorService.login(username, password);
        boolean flag = (boolean) login.get("flag");
        if (flag) {
            return new JXResult(true, ApiConstant.StatusCode.OK, "登录成功", login);
        }
        return new JXResult(false, ApiConstant.StatusCode.LOGINERROR, "登录失败,用户名或密码错误");
    }

    @GetMapping(value = "/judge/{username}")
    @ApiOperation(value = "判断用户名是否可以被注册", notes = "判断用户名是否可以被注册")
    public JXResult judgeUsername(@ApiParam(value = "用户名") @PathVariable String username) {
        return new JXResult(true, ApiConstant.StatusCode.OK, "请求成功", userAuthorService.judgeUsername(username));
    }

}
