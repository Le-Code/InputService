package com.yao.dao;

import com.yao.entity.GroupFileInfo;

import java.util.List;

public interface GroupFileMapper {
    /**
     * 查找全部的词库文件
     * @param orderRule
     * @return
     */
    List<GroupFileInfo>findAllGroupFile(String orderRule);

    /**
     * 根据词库名称来查找
     * @param groupName
     * @return
     */
    List<GroupFileInfo>findGroupFileByName(String groupName);

    /**
     * 根据id来进行查找词库名
     * @param id
     * @return
     */
    GroupFileInfo getGroupInfoById(int id);

    /**
     * 添加一个词库
     * @param groupFileInfo
     */
    void  addGroupFile(GroupFileInfo groupFileInfo);

    /**
     * 阐述一个词库
     * @param id
     */
    void deleteGroupFile(int id);
}
