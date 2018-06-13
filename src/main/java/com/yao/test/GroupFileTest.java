package com.yao.test;

import com.yao.dao.GroupFileMapper;
import com.yao.entity.GroupFileInfo;
import com.yao.utils.TimeUtils;
import com.yao.utils.TranslateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
    public void testUpdate(){
        GroupFileInfo info = mapper.findGroupFileByNameReturnSingle("明星名字");
        info.setCreateTime(TimeUtils.dateToString());
        mapper.updateGroup(info);
    }

    @Test
    public void f() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("d:\\test.txt",true));
        writer.write("我们");
        writer.newLine();
        writer.flush();
    }

}
