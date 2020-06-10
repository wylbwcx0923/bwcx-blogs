package cn.mvpwyl.blogs.service.impl;


import cn.mvpwyl.blogs.entity.ArticleComment;
import cn.mvpwyl.blogs.mapper.ArticleCommentMapper;
import cn.mvpwyl.blogs.service.ArticleCommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service(value = "articleCommentService")
public class ArticleCommentServiceImpl implements ArticleCommentService {
    @Autowired
    private ArticleCommentMapper articleCommentMapper;


    @Override
    public void addComment(ArticleComment articleComment) {
        articleComment.setCreateTime(new Date());
        articleComment.setStatus(0);
        articleCommentMapper.insertSelective(articleComment);
    }

    @Override
    public void delCommentById(Integer id) {
        articleCommentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateComment(ArticleComment articleComment) {
        articleCommentMapper.updateByPrimaryKeySelective(articleComment);
    }

    @Override
    public ArticleComment findCommentInfoById(Integer id) {
        return articleCommentMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<ArticleComment> getListByArticleIdPage(Integer articleId, Integer pageIndex, Integer pageSize) {
        String orderBy = " status ASC,create_time DESC";
        PageHelper.startPage(pageIndex, pageSize, orderBy);
        Example example = new Example(ArticleComment.class);
        example.createCriteria().andEqualTo("articleId", articleId);
        List<ArticleComment> articleComments = articleCommentMapper.selectByExample(example);
        PageInfo<ArticleComment> pageInfo = new PageInfo<>(articleComments);
        return pageInfo;
    }
}
