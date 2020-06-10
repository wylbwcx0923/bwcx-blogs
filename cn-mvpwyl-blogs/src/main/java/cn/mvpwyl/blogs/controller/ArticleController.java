package cn.mvpwyl.blogs.controller;

import cn.mvpwyl.blogs.config.ApiConstant;
import cn.mvpwyl.blogs.config.MvpwylResult;
import cn.mvpwyl.blogs.entity.Article;
import cn.mvpwyl.blogs.entity.vo.ArticleVO;
import cn.mvpwyl.blogs.service.ArticleService;
import cn.mvpwyl.blogs.utils.FastDFSClientWrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "文章接口", tags = "文章接口")
@RestController
@RequestMapping(value = "/api/article")
public class ArticleController {

    @Autowired
    private FastDFSClientWrapper fastDFSClientWrapper;
    @Autowired
    private ArticleService articleService;

    @PostMapping(value = "/file")
    @ApiOperation(value = "文件上传接口", notes = "文件上传接口", httpMethod = "POST")
    public MvpwylResult uploadFile(@ApiParam(value = "file", required = true)
                                   @RequestPart MultipartFile file) {
        String url = fastDFSClientWrapper.uploadFile(file);
        return new MvpwylResult(true, ApiConstant.StatusCode.OK, "上传成功", url);
    }

    @PostMapping(value = "/content")
    @ApiOperation(value = "上传文本接口", notes = "上传文本接口", httpMethod = "POST")
    public MvpwylResult uploadTxt(@ApiParam(value = "content", required = true)
                                  @RequestParam(value = "content") String content) {
        String url = fastDFSClientWrapper.uploadFile(content);
        return new MvpwylResult(true, ApiConstant.StatusCode.OK, "上传成功", url);
    }

    @DeleteMapping(value = "/delete/file")
    @ApiOperation(value = "从fastdfs删除文件", notes = "从fastdfs删除文件", httpMethod = "DELETE")
    public MvpwylResult deleteFile(@ApiParam(value = "需要删除的文件路径", required = true)
                                   @RequestParam(value = "url") String url) {
        fastDFSClientWrapper.deleteFile(url);
        System.out.println("程序执行了");
        return new MvpwylResult(true, ApiConstant.StatusCode.OK, "删除成功");
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "添加文章接口", notes = "添加文章接口", httpMethod = "POST")
    public MvpwylResult addArticle(@ApiParam(value = "文章对象", required = true)
                                   @RequestBody Article article) {
        articleService.addArticle(article);
        return new MvpwylResult(true, ApiConstant.StatusCode.OK, "文章添加成功");
    }

    @DeleteMapping(value = "/del/{id}")
    @ApiOperation(value = "删除文章", notes = "删除文章", httpMethod = "DELETE")
    public MvpwylResult delById(@ApiParam(value = "文章的Id", required = true)
                                @PathVariable Integer id) {
        articleService.delArticleById(id);
        return new MvpwylResult(true, ApiConstant.StatusCode.OK, "删除成功");
    }

    @PutMapping(value = "/update")
    @ApiOperation(value = "修改文章", notes = "修改文章", httpMethod = "PUT")
    public MvpwylResult updateArticle(@ApiParam(value = "文章对象", required = true)
                                      @RequestBody Article article) {
        articleService.updateArticle(article);
        return new MvpwylResult(true, ApiConstant.StatusCode.OK, "修改成功");
    }

    @GetMapping(value = "/find/{id}")
    @ApiOperation(value = "通过Id获取", notes = "通过Id获取", httpMethod = "GET")
    public MvpwylResult findById(@ApiParam(value = "文章的Id", required = true)
                                 @PathVariable Integer id) {
        ArticleVO article = articleService.findArticleById(id);
        return new MvpwylResult(true, ApiConstant.StatusCode.OK, "请求成功", article);
    }


    @GetMapping(value = "/list/{pageIndex}/{pageSize}")
    @ApiOperation(value = "获取文章列表", notes = "获取文章列表", httpMethod = "GET")
    public MvpwylResult list(@ApiParam(value = "当前页", required = false, defaultValue = "1")
                             @PathVariable Integer pageIndex,
                             @ApiParam(value = "每页显示数量", required = false, defaultValue = "20")
                             @PathVariable Integer pageSize,
                             @ApiParam(value = "作者名", required = false, defaultValue = "")
                             @RequestParam(value = "author", required = false) String author,
                             @ApiParam(value = "状态", required = false, defaultValue = "")
                             @RequestParam(value = "status", required = false) Integer status,
                             @ApiParam(value = "标题", required = false, defaultValue = "")
                             @RequestParam(value = "title", required = false) String title
    ) {
        PageInfo<ArticleVO> list = articleService.getArticleListByPage(pageIndex, pageSize, status, author, title);
        return new MvpwylResult(true, ApiConstant.StatusCode.OK, "请求成功", list);
    }


}
