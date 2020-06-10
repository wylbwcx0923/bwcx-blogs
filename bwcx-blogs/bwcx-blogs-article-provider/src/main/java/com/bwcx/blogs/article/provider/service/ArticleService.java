package com.bwcx.blogs.article.provider.service;


import com.bwcx.blogs.article.entity.Article;
import com.bwcx.blogs.article.vo.ArticleVO;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

public interface ArticleService {
    /**
     * 添加文章
     *
     * @param article
     */
    void addArticle(Article article);

    /**
     * 删除文章
     *
     * @param id
     */
    void delArticleById(Integer id);

    /**
     * 通过ID查询文章
     *
     * @param id
     * @return
     */
    Article findById(Integer id);

    /**
     * 修改文章内容
     *
     * @param article
     */
    void updateArticle(Article article);

    /**
     * 分页显示文章列表
     *
     * @param pageIndex
     * @param pageSize
     * @param author
     * @return
     */
    PageInfo<ArticleVO> getArticleList(Integer pageIndex, Integer pageSize, String author, Integer status);

}
