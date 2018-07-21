package com.yao.service;

import com.yao.entity.IMEVersion;

public interface IMEVersionService {

    void addNewVersion(IMEVersion newVersion);

    IMEVersion findLast();

}
