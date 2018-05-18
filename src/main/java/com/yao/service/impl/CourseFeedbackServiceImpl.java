package com.yao.service.impl;

import com.yao.dao.CourseFeedbackMapper;
import com.yao.entity.CourseFeedback;
import com.yao.service.CourseFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseFeedbackServiceImpl implements CourseFeedbackService {

    @Autowired
    private CourseFeedbackMapper feedbackMapper;

    @Override
    public void addFeedback(CourseFeedback feedback) {
        feedbackMapper.addFeedback(feedback);
    }

    @Override
    public CourseFeedback getFeedbackById(int id) {
        return feedbackMapper.selectFeedbackById(id);
    }

    @Override
    public List<CourseFeedback> getAllFeedback(String orderRule) {
        return feedbackMapper.selectAllFeedback(orderRule);
    }

    @Override
    public List<CourseFeedback> getFeedbackByReader(int isRead,String orderRule) {
        return feedbackMapper.selectFeedbackIsReader(isRead,orderRule);
    }

    @Override
    public void updateFeedback(CourseFeedback feedback) {
        feedbackMapper.updateFeedback(feedback);
    }

    @Override
    public void deleteFeedback(int id) {
        feedbackMapper.deleteFeedback(id);
    }
}
