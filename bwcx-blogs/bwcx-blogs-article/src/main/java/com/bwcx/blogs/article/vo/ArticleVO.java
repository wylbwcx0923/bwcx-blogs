package com.bwcx.blogs.article.vo;

import com.bwcx.blogs.article.entity.Article;

public class ArticleVO extends Article {

    private String authorName;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
