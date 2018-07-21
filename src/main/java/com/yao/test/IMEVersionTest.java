package com.yao.test;

import com.yao.dao.IMEVersionMapper;
import com.yao.entity.IMEVersion;
import com.yao.utils.TimeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationConfig.xml")
public class IMEVersionTest {
    @Autowired
    private IMEVersionMapper mapper;

    @Test
    public void testInsert(){
        IMEVersion version = new IMEVersion("1.1.2","icboard.apk",TimeUtils.dateToString());
        mapper.insertVersion(version);
    }

    @Test
    public void testFind(){
        IMEVersion version = mapper.findLast();
        System.out.println(version);
    }

    @Test
    public void t(){
        System.out.println(UUID.randomUUID().toString());
    }

}
