package com.bwcx.blogs.article.provider.service.impl;

import com.bwcx.blogs.article.entity.Article;
import com.bwcx.blogs.article.provider.client.FastdfsClient;
import com.bwcx.blogs.article.provider.mapper.ArticleMapper;
import com.bwcx.blogs.article.provider.service.ArticleService;
import com.bwcx.blogs.article.vo.ArticleVO;
import com.bwcx.blogs.commons.entity.BWCXResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service(value = "articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private FastdfsClient fastdfsClient;

    @Override
    public void addArticle(Article article) {
        article.setCreateTime(new Date());
        //上传富文本内容到fastdfs
        String url = uploadArticleContentToFastdfs(article.getInfo());
        if (url == null) {
            new RuntimeException("添加失败,网络异常");
        }
        //把文章的内容url存入数据库
        article.setInfo(url);
        articleMapper.insertSelective(article);
    }

    private String uploadArticleContentToFastdfs(String info) {
        //生成一个保存文章详情的临时文件
        for (int i = 0; i < 3; i++) {
            try {
                //文件名
                String fileName = UUID.randomUUID().toString().replaceAll("-", "");
                File tempFile = File.createTempFile(fileName, ".txt");
                //把字符串的内容写入这个临时文件
                FileUtils.writeByteArrayToFile(tempFile, info.getBytes(Charset.forName("UTF-8")));
                //把这个临时文件转化为多组件的上传文件
                FileInputStream input = new FileInputStream(tempFile);
                MultipartFile multipartFile = new MockMultipartFile("file", tempFile.getName(), "text/plain", IOUtils.toByteArray(input));
                //文件上传
                BWCXResult bwcxResult = fastdfsClient.uploadFile(multipartFile);
                if (bwcxResult.isFlag()) {
                    return (String) bwcxResult.getData();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void delArticleById(Integer id) {

    }

    @Override
    public Article findById(Integer id) {
        return null;
    }

    @Override
    public void updateArticle(Article article) {

    }

    @Override
    public PageInfo<ArticleVO> getArticleList(Integer pageIndex, Integer pageSize, String author, Integer status) {
        PageHelper.startPage(pageIndex, pageSize);
        List<ArticleVO> articleVOS = articleMapper.selectArticleList(status, author);
        PageInfo<ArticleVO> pageInfo = new PageInfo<>(articleVOS);
        return pageInfo;
    }


}
