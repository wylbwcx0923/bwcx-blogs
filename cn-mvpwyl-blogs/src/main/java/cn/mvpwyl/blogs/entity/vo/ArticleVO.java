package cn.mvpwyl.blogs.entity.vo;

import cn.mvpwyl.blogs.entity.Article;

/**
 * @author 不忘初心
 * 文章对象的视图显示类
 */
public class ArticleVO extends Article {

    private String authorName;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
