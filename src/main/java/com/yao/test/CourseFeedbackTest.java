package com.yao.test;


import com.yao.dao.CourseFeedbackMapper;
import com.yao.entity.CourseFeedback;
import com.yao.utils.ConstantValues;
import com.yao.utils.TimeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationConfig.xml")
public class CourseFeedbackTest {


    @Autowired
    private CourseFeedbackMapper feedbackMapper;

    @Test
    public void testInsert(){
        CourseFeedback feedback = new CourseFeedback("还不错",TimeUtils.dateToString(),ConstantValues.NONREADFEEDBACK);
        feedbackMapper.addFeedback(feedback);
    }

    @Test
    public void testSelectById(){
        CourseFeedback feedback = feedbackMapper.selectFeedbackById(2);
        System.out.println(feedback);
    }

    @Test
    public void testSelectAll(){
        List<CourseFeedback> feedbacks = feedbackMapper.selectAllFeedback(ConstantValues.DESC);
        for (CourseFeedback feedback:feedbacks)
            System.out.println(feedback);
    }

    @Test
    public void updateFeedback(){
        CourseFeedback feedback = new CourseFeedback(2,"还不错","2018/05/18 08:53:19",ConstantValues.READFEEDBACK);
        feedbackMapper.updateFeedback(feedback);
    }

    @Test
    public void testSelectByRead(){
        List<CourseFeedback> feedbacks = feedbackMapper.selectFeedbackIsReader(ConstantValues.NONREADFEEDBACK,ConstantValues.ASC);
        for (CourseFeedback feedback:feedbacks)
            System.out.println(feedback);
    }
}
