package com.nine.one.yuedu.read.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "book_info")
public class BookInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**
     * 书籍名称
     */
    @Column(name = "book_name")
    private String bookName;

    /**
     * 作者
     */
    private String author;

    /**
     * 关键字
     */
    private String keywords;

    /**
     * 字数
     */
    private Integer words;

    /**
     * 分类
     */
    private String category;


    /**
     * 1上架，2下架
     */
    private Integer valid;

    /**
     * 1完结,0连载
     */
    @Column(name = "complete_state")
    private Integer completeState;

    /**
     * 最后章节ID
     */
    @Column(name = "last_chapter_id")
    private Integer lastChapterId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 封面图
     */
    @Column(name = "pic_url")
    private String picUrl;

    /**
     * 阅读人数
     */
    @Column(name = "visit_count")
    private Integer visitCount;

    /**
     * 是否搜索
     */
    @Column(name = "open_search")
    private Integer openSearch;

    /**
     * 提供者
     */
    private String provider;

    /**
     * 简介
     */
    private String description;

    @Column(name = "cp_id")
    private Integer cpId;
    @Column(name = "cp_book_id")
    private String cpBookId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取书籍名称
     *
     * @return book_name - 书籍名称
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * 设置书籍名称
     *
     * @param bookName 书籍名称
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * 获取作者
     *
     * @return author - 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取关键字
     *
     * @return keywords - 关键字
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * 设置关键字
     *
     * @param keywords 关键字
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * 获取字数
     *
     * @return words - 字数
     */
    public Integer getWords() {
        return words;
    }

    /**
     * 设置字数
     *
     * @param words 字数
     */
    public void setWords(Integer words) {
        this.words = words;
    }

    /**
     * 获取分类
     *
     * @return category - 分类
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置分类
     *
     * @param category 分类
     */
    public void setCategory(String category) {
        this.category = category;
    }


    /**
     * 获取1上架，2下架
     *
     * @return valid - 1上架，2下架
     */
    public Integer getValid() {
        return valid;
    }

    /**
     * 设置1上架，2下架
     *
     * @param valid 1上架，2下架
     */
    public void setValid(Integer valid) {
        this.valid = valid;
    }

    /**
     * 获取1完结,0连载
     *
     * @return complete_state - 1完结,0连载
     */
    public Integer getCompleteState() {
        return completeState;
    }

    /**
     * 设置1完结,0连载
     *
     * @param completeState 1完结,0连载
     */
    public void setCompleteState(Integer completeState) {
        this.completeState = completeState;
    }

    /**
     * 获取最后章节ID
     *
     * @return last_chapter_id - 最后章节ID
     */
    public Integer getLastChapterId() {
        return lastChapterId;
    }

    /**
     * 设置最后章节ID
     *
     * @param lastChapterId 最后章节ID
     */
    public void setLastChapterId(Integer lastChapterId) {
        this.lastChapterId = lastChapterId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取封面图
     *
     * @return pic_url - 封面图
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * 设置封面图
     *
     * @param picUrl 封面图
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * 获取阅读人数
     *
     * @return visit_count - 阅读人数
     */
    public Integer getVisitCount() {
        return visitCount;
    }

    /**
     * 设置阅读人数
     *
     * @param visitCount 阅读人数
     */
    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    /**
     * 获取是否搜索
     *
     * @return open_search - 是否搜索
     */
    public Integer getOpenSearch() {
        return openSearch;
    }

    /**
     * 设置是否搜索
     *
     * @param openSearch 是否搜索
     */
    public void setOpenSearch(Integer openSearch) {
        this.openSearch = openSearch;
    }

    /**
     * 获取提供者
     *
     * @return provider - 提供者
     */
    public String getProvider() {
        return provider;
    }

    /**
     * 设置提供者
     *
     * @param provider 提供者
     */
    public void setProvider(String provider) {
        this.provider = provider;
    }

    /**
     * 获取简介
     *
     * @return description - 简介
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置简介
     *
     * @param description 简介
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCpId() {
        return cpId;
    }

    public void setCpId(Integer cpId) {
        this.cpId = cpId;
    }

    public String getCpBookId() {
        return cpBookId;
    }

    public void setCpBookId(String cpBookId) {
        this.cpBookId = cpBookId;
    }
}
