package com.yao.service.impl;

import com.yao.dao.GroupFileMapper;
import com.yao.entity.GroupFileInfo;
import com.yao.service.GroupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupFileinfoServiceImpl implements GroupInfoService {

    @Autowired
    private GroupFileMapper mapper;

    @Override
    public List<GroupFileInfo> findAllGroupFile(String orderRule) {
        List<GroupFileInfo>list = mapper.findAllGroupFile(orderRule);
        return list;
    }

    @Override
    public List<GroupFileInfo> findGroupFileByNameReturnList(String groupName) {
        List<GroupFileInfo>list = mapper.findGroupFileByNameReturnList(groupName);
        return list;
    }

    @Override
    public GroupFileInfo findGroupFileByNameReturnSingle(String groupName) {
        GroupFileInfo info = mapper.findGroupFileByNameReturnSingle(groupName);
        return info;
    }

    @Override
    public GroupFileInfo getGroupFileById(int id) {
        GroupFileInfo info = mapper.getGroupInfoById(id);
        return info;
    }

    @Override
    public void addGroupFile(GroupFileInfo fileInfo) {
        mapper.addGroupFile(fileInfo);
    }

    @Override
    public void deleteGroupFile(int id) {
        mapper.deleteGroupFile(id);
    }

    @Override
    public void updateGroup(GroupFileInfo info) {
        mapper.updateGroup(info);
    }
}
