package com.yao.controller;

import com.yao.entity.CourseFeedback;
import com.yao.service.CourseFeedbackService;
import com.yao.utils.ConstantValues;
import com.yao.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CourseFeedbackController {

    @Autowired
    private CourseFeedbackService courseFeedbackService;

    @RequestMapping(value = "addCourseFeedback.do",method = RequestMethod.GET)
    @ResponseBody
    public String addFeedback(String info){
        CourseFeedback feedback = new CourseFeedback(info,TimeUtils.dateToString(),ConstantValues.NONREADFEEDBACK);
        courseFeedbackService.addFeedback(feedback);
        return "ok";
    }

}
