package com.bwcx.blogs.article.consumer.client;

import com.bwcx.blogs.article.consumer.client.fallback.ArticleClientFallBack;
import com.bwcx.blogs.article.entity.Article;
import com.bwcx.blogs.commons.entity.BWCXResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Component
@FeignClient(value = "bwcx-blogs-article-provider", fallback = ArticleClientFallBack.class)
public interface ArticleClient {
    @GetMapping(value = "/bwcx/article/list/{pageIndex}/{pageSize}")
    BWCXResult list(@PathVariable Integer pageIndex,
                    @PathVariable Integer pageSize,
                    @RequestParam(value = "status", required = false) Integer status,
                    @RequestParam(value = "author", required = false) String author);

    @PostMapping(value = "/bwcx/article/add")
    BWCXResult add(@RequestBody Article article);

    @PostMapping(value = "/bwcx/article/upload/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    BWCXResult upload(@RequestPart(value = "file") MultipartFile file);
}
