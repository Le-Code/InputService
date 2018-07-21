package com.yao.service.impl;

import com.yao.dao.IMEVersionMapper;
import com.yao.entity.IMEVersion;
import com.yao.service.IMEVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IMEVersionServiceImpl implements IMEVersionService {

    @Autowired
    private IMEVersionMapper mapper;

    @Override
    public void addNewVersion(IMEVersion newVersion) {
        mapper.insertVersion(newVersion);
    }

    @Override
    public IMEVersion findLast() {
        return mapper.findLast();
    }
}
