package com.yzg.spider.entity;

import java.util.Date;

public class UrlQueue {
    private Integer id;

    private String urlQueue;

    private Integer isVisited;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrlQueue() {
        return urlQueue;
    }

    public void setUrlQueue(String urlQueue) {
        this.urlQueue = urlQueue == null ? null : urlQueue.trim();
    }

    public Integer getIsVisited() {
        return isVisited;
    }

    public void setIsVisited(Integer isVisited) {
        this.isVisited = isVisited;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}