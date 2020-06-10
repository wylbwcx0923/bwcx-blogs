package com.bwcx.blogs.article.consumer.controller;

import com.bwcx.blogs.article.consumer.client.ArticleClient;
import com.bwcx.blogs.article.entity.Article;
import com.bwcx.blogs.commons.entity.BWCXResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/bwcx/article")
public class ArticleController {

    @Autowired
    private ArticleClient articleClient;

    @GetMapping(value = "/list/{pageIndex}/{pageSize}")
    public BWCXResult list(@PathVariable Integer pageIndex,
                           @PathVariable Integer pageSize,
                           @RequestParam(value = "status", required = false) Integer status,
                           @RequestParam(value = "author", required = false) String author) {
        System.out.println(author);
        return articleClient.list(pageIndex, pageSize, status, author);
    }

    @PostMapping(value = "/add")
    public BWCXResult add(@RequestBody Article article) {
        return articleClient.add(article);
    }

    @PostMapping(value = "/upload/file")
    public BWCXResult upload(@RequestPart(value = "file") MultipartFile file) {
        return articleClient.upload(file);

    }
}
