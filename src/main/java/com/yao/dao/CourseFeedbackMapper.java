package com.yao.dao;

import com.yao.entity.CourseFeedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseFeedbackMapper {

    /**
     * 添加一个反馈
     * @param feedback
     */
    void addFeedback(CourseFeedback feedback);

    /**
     * 查找所有的反馈
     * @return
     */
    List<CourseFeedback> selectAllFeedback(@Param("orderRule") String orderRule);

    /**
     * 按照是否已读作为条件查找
     * @param isRead
     * @return
     */
    List<CourseFeedback> selectFeedbackIsReader(@Param("isRead") int isRead,@Param("orderRule") String orderRule);

    /**
     * 更具id查找反馈
     * @param id
     * @return
     */
    CourseFeedback selectFeedbackById(int id);

    /**
     * 更新反馈
     * @param feedback
     */
    void updateFeedback(CourseFeedback feedback);

    /**
     * 删除一个反馈
     * @param id
     */
    void deleteFeedback(int id);
}
