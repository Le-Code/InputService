package com.yao.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页信息
 */
public class CustomPageInfo<T> {
    /**
     * 总个数
     */
    private int totalCount;
    /**
     * 当前的页数
     */
    private int pageNum;
    /**
     * 一页显示多少条数据
     */
    private int pageSize;
    /**
     * 一共有多少条数据
     */
    private int totalNum;
    /**
     * 开始
     */
    private int pageStart;
    /**
     * 结束
     */
    private int pageEnd;
    /**
     * 是否还有上一页
     */
    private boolean hasPrevious;
    /**
     * 是否还有下一页
     */
    private boolean hasNext;
    /**
     * 显示的数据
     */
    private List<T>showData = new ArrayList<>();

    public CustomPageInfo(List<T>data, int pageSize){
        this.pageSize = pageSize;
        this.totalCount = data.size();
        this.totalNum = (int) Math.ceil(totalCount*1.0/pageSize);
    }

    public void split(List<T>data,int pageNum,int navigatePages){
        this.pageNum = pageNum;
        pageStart = (pageNum-(navigatePages/2))>1?(pageNum-navigatePages/2):1;
        pageEnd = (pageStart+navigatePages)>totalNum?totalNum:(pageStart+navigatePages);
        hasPrevious = pageNum!=1?true:false;
        hasNext = pageNum!=totalNum?true:false;
        showData.clear();
        int contentStart = (pageNum-1)*pageSize;
        int contentEnd = pageNum*pageSize>totalCount?totalCount:pageNum*pageSize;
        showData.addAll(data.subList(contentStart,contentEnd));
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public List<T> getShowData() {
        return showData;
    }

    public void setShowData(List<T> showData) {
        this.showData = showData;
    }
}
