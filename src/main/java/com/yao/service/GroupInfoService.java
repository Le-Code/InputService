package com.yao.service;

import com.yao.entity.GroupFileInfo;

import java.util.List;

public interface GroupInfoService {
    /**
     * 查找所有的词库文件
     * @return
     */
    List<GroupFileInfo> findAllGroupFile(String orderRule);

    /**
     * 根据词库名查找
     */
    List<GroupFileInfo> findGroupFileByName(String groupName);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    GroupFileInfo getGroupFileById(int id);

    /**
     * 添加一个词库文件
     * @param fileInfo
     */
    void addGroupFile(GroupFileInfo fileInfo);

    /**
     * 删除一个词库文件
     * @param id
     */
    void deleteGroupFile(int id);
}
