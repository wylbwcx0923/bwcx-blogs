package com.bwcx.blogs.article.provider.controller;

import com.bwcx.blogs.article.entity.Article;
import com.bwcx.blogs.article.provider.client.FastdfsClient;
import com.bwcx.blogs.article.provider.service.ArticleService;
import com.bwcx.blogs.article.vo.ArticleVO;
import com.bwcx.blogs.commons.entity.BWCXResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/bwcx/article")
public class ArticleController {

    @Resource(name = "articleService")
    private ArticleService articleService;
    @Autowired
    private FastdfsClient fastdfsClient;

    @GetMapping(value = "/list/{pageIndex}/{pageSize}")
    public BWCXResult list(@PathVariable Integer pageIndex,
                           @PathVariable Integer pageSize,
                           @RequestParam(value = "status", required = false) Integer status,
                           @RequestParam(value = "author", required = false) String author) {
        PageInfo<ArticleVO> articleList = articleService.getArticleList(pageIndex, pageSize, author, status);
        return new BWCXResult(true, 200, "请求成功", articleList);
    }

    @PostMapping(value = "/add")
    public BWCXResult add(@RequestBody Article article) {
        articleService.addArticle(article);
        return new BWCXResult(true, 200, "添加文章成功");
    }

    @PostMapping(value = "/upload/file")
    public BWCXResult upload(@RequestPart(value = "file") MultipartFile file) {
        return fastdfsClient.uploadFile(file);

    }
}
