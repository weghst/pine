package com.weghst.pine.web.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zou Yong(zouyong@mychebao.com)
 * @date 2015-09-12 16:38
 */
@Service
public class TestSpring {

    public void test1() {
        System.out.println("Test1...........");
    }

    public String test2() {
        return "Test2";
    }

}
