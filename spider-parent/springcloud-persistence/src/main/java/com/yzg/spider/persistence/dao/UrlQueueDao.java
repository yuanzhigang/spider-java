package com.yzg.spider.persistence.dao;

import com.yzg.spider.entity.UrlQueue;

public interface UrlQueueDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UrlQueue record);

    int insertSelective(UrlQueue record);

    UrlQueue selectByPrimaryKey(Integer id);

    UrlQueue selectUrlRecently();

    UrlQueue selectByUrlVisited(String urlQueue);

    UrlQueue selectByUrl(String urlQueue);

    int updateByPrimaryKeySelective(UrlQueue record);

    int updateByPrimaryKey(UrlQueue record);

    int selectCountVisited();

    int selectCountNotVisited();
}
