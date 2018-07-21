package com.yao.dao;

import com.yao.entity.IMEVersion;

public interface IMEVersionMapper {
    /**
     * 插入一条数据
     * @param imeVersion
     */
    void insertVersion(IMEVersion imeVersion);

    /**
     * 找到一个新的版本
     * @return
     */
    IMEVersion findLast();
}
