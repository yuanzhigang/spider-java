package com.yzg.spider.persistence.dao;

import com.yzg.spider.entity.UrlError;

public interface UrlErrorDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UrlError record);

    int insertSelective(UrlError record);

    UrlError selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UrlError record);

    int updateByPrimaryKey(UrlError record);
}