package cn.mvpwyl.blogs.service;

import cn.mvpwyl.blogs.entity.ArticleComment;
import com.github.pagehelper.PageInfo;

public interface ArticleCommentService {

    /**
     * 进行(添加)评论
     *
     * @param articleComment
     */
    void addComment(ArticleComment articleComment);

    /**
     * 删除评论
     *
     * @param id
     */
    void delCommentById(Integer id);

    /**
     * 修改评论
     *
     * @param articleComment
     */
    void updateComment(ArticleComment articleComment);

    /**
     * 查看详情
     *
     * @param id
     * @return
     */
    ArticleComment findCommentInfoById(Integer id);


    /**
     * 根据文章id查询评论列表分页
     *
     * @param articleId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageInfo<ArticleComment> getListByArticleIdPage(Integer articleId, Integer pageIndex, Integer pageSize);
}
