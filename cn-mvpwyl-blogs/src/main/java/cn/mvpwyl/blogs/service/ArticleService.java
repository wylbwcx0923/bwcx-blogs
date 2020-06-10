package cn.mvpwyl.blogs.service;

import cn.mvpwyl.blogs.entity.Article;
import cn.mvpwyl.blogs.entity.vo.ArticleVO;
import com.github.pagehelper.PageInfo;

/**
 * 文章服务接口
 */
public interface ArticleService {

    /**
     * 添加文章
     *
     * @param article
     */
    void addArticle(Article article);

    /**
     * 通过Id删除文章
     *
     * @param id
     */
    void delArticleById(Integer id);

    /**
     * 通过id查询文章对象
     *
     * @param id
     * @return
     */
    ArticleVO findArticleById(Integer id);

    /**
     * 修改文章内容
     *
     * @param article
     */
    void updateArticle(Article article);


    /**
     * 查询文章列表并分页
     *
     * @param pageIndex
     * @param pageSize
     * @param status
     * @param author
     * @param title
     * @return
     */
    PageInfo<ArticleVO> getArticleListByPage(Integer pageIndex, Integer pageSize, Integer status, String author, String title);


}
