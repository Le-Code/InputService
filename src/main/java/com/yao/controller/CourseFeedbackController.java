package com.yao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.yao.entity.ContentWrapper;
import com.yao.entity.CourseFeedback;
import com.yao.service.CourseFeedbackService;
import com.yao.utils.ConstantValues;
import com.yao.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("courseFeedback")
public class CourseFeedbackController {

    /**
     * 每一页显示10条信息
     */
    private final int pageSize = 10;

    @Autowired
    private CourseFeedbackService courseFeedbackService;

    @CrossOrigin(maxAge = 3600)
    @RequestMapping(value = "addCourseFeedback.do",method = RequestMethod.GET,produces = {"text/html;charset=UTF-8"})
    @ResponseBody
    public String addFeedback(String info, HttpServletRequest request){
        CourseFeedback feedback = new CourseFeedback(info,TimeUtils.dateToString(),ConstantValues.NONREADFEEDBACK);
        courseFeedbackService.addFeedback(feedback);
        return "ok";
    }

    @RequestMapping(value = "showCourseFeedback.do",produces = {"text/html;charset=UTF-8"})
    public String showCourseFeedback(@RequestParam(defaultValue = "0") int pageNum, Model model){
        PageHelper.startPage(pageNum,pageSize);
        List<CourseFeedback> feedbacks = courseFeedbackService.getAllFeedback(null);
        PageInfo pageInfo = new PageInfo(feedbacks,5);
        model.addAttribute("feedbacks",feedbacks);
        model.addAttribute("pageInfo",pageInfo);
        return "feedback/CourseFeedbackShow";
    }

    /**
     * 更具状态改变用户反馈的信息
     * @return
     */
    @RequestMapping(value = "changeCourseFeedbackList.do",produces="text/html;charset=UTF-8;")
    @ResponseBody
    public String changeCourseFeedbackList(@RequestParam(defaultValue = "0") int pageNum,int isRead,String timeOrder,Model model){
        System.out.println("进来啦");
        PageHelper.startPage(pageNum,pageSize);
        List<CourseFeedback>feedbacks;
        if (isRead==-1)
            feedbacks = courseFeedbackService.getAllFeedback(timeOrder);
        else
            feedbacks = courseFeedbackService.getFeedbackByReader(isRead,timeOrder);
        PageInfo info = new PageInfo(feedbacks,5);
        model.addAttribute("pageInfo",info);
        ContentWrapper<CourseFeedback>contentWrapper = new ContentWrapper<>(feedbacks,info);
        Gson gson = new Gson();
        String result =  gson.toJson(contentWrapper);
        return result;
    }

    @RequestMapping(value = "deleteFeedback.do",produces="text/html;charset=UTF-8;")
    @ResponseBody
    public String deleteFeedback(@RequestParam(defaultValue = "0") int pageNum,int id, int isRead,String timeOrder,Model model){
        courseFeedbackService.deleteFeedback(id);
        PageHelper.startPage(pageNum,pageSize);
        List<CourseFeedback>feedbacks;
        if (isRead==-1){
            feedbacks = courseFeedbackService.getAllFeedback(timeOrder);
        }else{
            feedbacks = courseFeedbackService.getFeedbackByReader(isRead,timeOrder);
        }
        PageInfo pageInfo = new PageInfo(feedbacks,5);
        ContentWrapper<CourseFeedback>contentWrapper = new ContentWrapper<>(feedbacks,pageInfo);
        Gson gson = new Gson();
        String result =  gson.toJson(contentWrapper);
        return result;
    }

    @RequestMapping(value = "getFeedbackById/{feedId}",produces="text/html;charset=UTF-8;")
    @ResponseBody
    public String getFeedbackById(@PathVariable(value = "feedId") int feedId){
        CourseFeedback feedback = courseFeedbackService.getFeedbackById(feedId);
        feedback.setIsRead(ConstantValues.READFEEDBACK);
        courseFeedbackService.updateFeedback(feedback);
        Gson gson = new Gson();
        return gson.toJson(feedback);
    }
}
