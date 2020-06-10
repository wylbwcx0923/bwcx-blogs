package cn.mvpwyl.blogs.service.impl;

import cn.mvpwyl.blogs.config.ApiConstant;
import cn.mvpwyl.blogs.entity.Article;
import cn.mvpwyl.blogs.entity.ArticleComment;
import cn.mvpwyl.blogs.entity.vo.ArticleVO;
import cn.mvpwyl.blogs.mapper.ArticleCommentMapper;
import cn.mvpwyl.blogs.mapper.ArticleMapper;
import cn.mvpwyl.blogs.service.ArticleService;
import cn.mvpwyl.blogs.utils.FastDFSClientWrapper;
import cn.mvpwyl.blogs.utils.HttpClientUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service(value = "articleServiceImpl")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private FastDFSClientWrapper fastDFSClientWrapper;
    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Override
    public void addArticle(Article article) {
        article.setCreateTime(new Date());
        //把文章的内容当做文本上传到文件服务器
        String infoUrl = info2TxtUploadFastdfs(article.getInfo());
        article.setInfo(infoUrl);
        articleMapper.insertSelective(article);
    }

    /**
     * @param info
     * @return
     */
    private String info2TxtUploadFastdfs(String info) {
        String url = fastDFSClientWrapper.uploadFile(info);
        return url;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delArticleById(Integer id) {
        //删除Fastdfs中该文章的封面图和文章内容
        try {
            Article article = articleMapper.selectByPrimaryKey(id);
            fastDFSClientWrapper.deleteFile(article.getInfo());
            fastDFSClientWrapper.deleteFile(article.getImageUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //删除一篇文章,需要删除所有的评论
        Example example = new Example(ArticleComment.class);
        example.createCriteria().andEqualTo("articleId", id);
        articleCommentMapper.deleteByExample(example);
        articleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ArticleVO findArticleById(Integer id) {
        ArticleVO article = articleMapper.selectVOByPrimaryKey(id);
        String infoUrl = ApiConstant.FastDFSConstant.BASE_URL + article.getInfo();
        article.setInfo(infoUrl);
        article.setImageUrl(ApiConstant.FastDFSConstant.BASE_URL + article.getImageUrl());
        return article;
    }

    @Override
    public void updateArticle(Article article) {
        Article articleOld = articleMapper.selectByPrimaryKey(article.getId());
        //先判断内容
        String infoOld = HttpClientUtil.doGet(ApiConstant.FastDFSConstant.BASE_URL + articleOld.getInfo());
        if (!article.getInfo().equals(infoOld)) {
            //如果跟原来不一样了,就删除原来的
            fastDFSClientWrapper.deleteFile(articleOld.getInfo());
            //上传新的内容
            String url = fastDFSClientWrapper.uploadFile(article.getInfo());
            article.setInfo(url);
        } else {
            //如果跟原来相同,就不修改了
            article.setInfo(null);
        }

        //再判断图片
        String imageUrl = article.getImageUrl().replaceAll(ApiConstant.FastDFSConstant.BASE_URL, "");
        String imageUrlOld = articleOld.getImageUrl();
        if (!imageUrl.equals(imageUrlOld)) {
            //如果不一样了,就删除原来的
            fastDFSClientWrapper.deleteFile(imageUrlOld);
            article.setImageUrl(imageUrl);
        } else {
            article.setImageUrl(null);
        }
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public PageInfo<ArticleVO> getArticleListByPage(Integer pageIndex,
                                                    Integer pageSize,
                                                    Integer status,
                                                    String author,
                                                    String title) {

        PageHelper.startPage(pageIndex, pageSize);
        List<ArticleVO> articleVOS = articleMapper.selectArticleListByParamsAndPage(author, status, title);
        for (ArticleVO articleVO : articleVOS) {
            articleVO.setImageUrl(ApiConstant.FastDFSConstant.BASE_URL + articleVO.getImageUrl());
            articleVO.setInfo(ApiConstant.FastDFSConstant.BASE_URL + articleVO.getInfo());
        }
        PageInfo<ArticleVO> pageInfo = new PageInfo<>(articleVOS);
        return pageInfo;
    }
}
