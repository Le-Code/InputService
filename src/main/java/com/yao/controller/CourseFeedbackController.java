package com.yao.controller;

import com.google.gson.Gson;
import com.yao.entity.CourseFeedback;
import com.yao.service.CourseFeedbackService;
import com.yao.utils.ConstantValues;
import com.yao.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("courseFeedback")
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

    @RequestMapping(value = "showCourseFeedback.do")
    public String showCourseFeedback(Model model){
        List<CourseFeedback> feedbacks = courseFeedbackService.getAllFeedback(null);
        model.addAttribute("feedbacks",feedbacks);
        return "feedback/CourseFeedbackShow";
    }

    /**
     * 更具状态改变用户反馈的信息
     * @return
     */
    @RequestMapping(value = "changeCourseFeedbackList.do",produces="text/html;charset=UTF-8;")
    @ResponseBody
    public String changeCourseFeedbackList(int isRead,String timeOrder){
        List<CourseFeedback>feedbacks;
        if (isRead==-1)
            feedbacks = courseFeedbackService.getAllFeedback(timeOrder);
        else
            feedbacks = courseFeedbackService.getFeedbackByReader(isRead,timeOrder);
        Gson gson = new Gson();
       String result =  gson.toJson(feedbacks);
        return result;
    }

    @RequestMapping(value = "deleteFeedback.do",produces="text/html;charset=UTF-8;")
    @ResponseBody
    public String deleteFeedback(int id, int isRead,String timeOrder){
        courseFeedbackService.deleteFeedback(id);
        List<CourseFeedback>feedbacks;
        if (isRead==-1){
            feedbacks = courseFeedbackService.getAllFeedback(timeOrder);
        }else{
            feedbacks = courseFeedbackService.getFeedbackByReader(isRead,timeOrder);
        }
        Gson gson = new Gson();
        String result =  gson.toJson(feedbacks);
        return result;
    }
}
