package com.bwcx.blogs.article.consumer.client.fallback;

import com.bwcx.blogs.article.consumer.client.ArticleClient;
import com.bwcx.blogs.article.entity.Article;
import com.bwcx.blogs.commons.entity.BWCXResult;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ArticleClientFallBack implements ArticleClient {

    private BWCXResult bwcxResult = new BWCXResult(false, 500, "请求失败,网络异常");

    @Override
    public BWCXResult list(Integer pageIndex, Integer pageSize, Integer status, String author) {
        return bwcxResult;
    }

    @Override
    public BWCXResult add(Article article) {
        return bwcxResult;
    }

    @Override
    public BWCXResult upload(MultipartFile file) {
        return bwcxResult;
    }
}
