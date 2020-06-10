package cn.mvpwyl.blogs.controller;

import cn.mvpwyl.blogs.config.ApiConstant;
import cn.mvpwyl.blogs.config.MvpwylResult;
import cn.mvpwyl.blogs.entity.ArticleComment;
import cn.mvpwyl.blogs.service.ArticleCommentService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(value = "文章评论接口", tags = "文章评论接口")
@RequestMapping(value = "/api/article/comment")
public class ArticleCommentController {

    @Resource(name = "articleCommentService")
    private ArticleCommentService articleCommentService;


    /**
     * 进行(添加)评论
     *
     * @param articleComment
     */
    @PostMapping(value = "add")
    @ApiOperation(value = "添加评论", notes = "添加评论", httpMethod = "POST")
    public MvpwylResult addComment(@ApiParam(value = "文章评论对象", required = true)
                                   @RequestBody ArticleComment articleComment) {
        articleCommentService.addComment(articleComment);
        return new MvpwylResult(true, ApiConstant.StatusCode.OK, "添加成功");
    }


    /**
     * 删除评论
     *
     * @param id
     */
    @DeleteMapping(value = "del/{id}")
    @ApiOperation(value = "删除评论", notes = "删除评论", httpMethod = "DELETE")
    public MvpwylResult delCommentById(@PathVariable
                                       @ApiParam(value = "评论的id") Integer id) {
        articleCommentService.delCommentById(id);
        return new MvpwylResult(true, ApiConstant.StatusCode.OK, "删除成功");
    }

    /**
     * 修改评论
     *
     * @param articleComment
     */
    @PutMapping(value = "update")
    @ApiOperation(value = "修改评论", notes = "修改评论", httpMethod = "PUT")
    public MvpwylResult updateComment(@ApiParam(value = "文章评论对象", required = true)
                                      @RequestBody ArticleComment articleComment) {
        articleCommentService.updateComment(articleComment);
        return new MvpwylResult(true, ApiConstant.StatusCode.OK, "修改成功");
    }

    /**
     * 查看详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "find/{id}")
    @ApiOperation(value = "根据Id获得评论", notes = "根据Id获得评论", httpMethod = "GET")
    public MvpwylResult findCommentById(@PathVariable
                                        @ApiParam(value = "评论的id") Integer id) {
        ArticleComment comment = articleCommentService.findCommentInfoById(id);
        return new MvpwylResult(true, ApiConstant.StatusCode.OK, "请求成功", comment);
    }


    /**
     * 根据文章id查询评论列表分页
     *
     * @param articleId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping(value = "list/{pageIndex}/{pageSize}/{articleId}")
    public MvpwylResult getListByArticleIdPage(@PathVariable
                                               @ApiParam(value = "文章的Id") Integer articleId,
                                               @PathVariable
                                               @ApiParam(value = "当前第几页") Integer pageIndex,
                                               @PathVariable
                                               @ApiParam(value = "每页显示数量") Integer pageSize) {
        PageInfo<ArticleComment> list = articleCommentService.getListByArticleIdPage(articleId, pageIndex, pageSize);
        return new MvpwylResult(true, ApiConstant.StatusCode.OK, "请求成功", list);
    }

}
