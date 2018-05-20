package com.yao.entity;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class ContentWrapper<T> {
    private List<T>content;
    private PageInfo<T>pageInfo;

    public ContentWrapper(List<T> content, PageInfo<T> pageInfo) {
        this.content = content;
        this.pageInfo = pageInfo;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public PageInfo<T> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<T> pageInfo) {
        this.pageInfo = pageInfo;
    }
}
