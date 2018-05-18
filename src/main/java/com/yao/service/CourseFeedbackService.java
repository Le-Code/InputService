package com.yao.service;

import com.yao.entity.CourseFeedback;

import java.util.List;

public interface CourseFeedbackService {

    void addFeedback(CourseFeedback feedBack);

    CourseFeedback getFeedbackById(int id);

    List<CourseFeedback>getAllFeedback(String orderRule);

    List<CourseFeedback>getFeedbackByReader(int isRead,String orderRule);

    void updateFeedback(CourseFeedback feedBack);

    void deleteFeedback(int id);

}
