package com.yao.test;

import com.yao.dao.GroupFileMapper;
import com.yao.entity.CustomGroup;
import com.yao.entity.GroupFileInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationConfig.xml")
public class GroupFileTest {

    @Autowired
    private GroupFileMapper mapper;

    @Test
    public void testSelect(){
        List<GroupFileInfo>list = mapper.findAllGroupFile(null);
        for (GroupFileInfo info:list)
            System.out.println(info);
    }

    @Test
    public void f(){

    }

}
