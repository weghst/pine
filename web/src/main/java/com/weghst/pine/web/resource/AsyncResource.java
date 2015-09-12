/**
 * Copyright (C) 2015 The Weghst Inc. (kevinz@weghst.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.weghst.pine.web.resource;


import com.weghst.pine.spi.Test22;
import com.weghst.pine.web.vo.SimpleQueryVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@Component
public class AsyncResource {

    @Autowired
    public TestSpring testSpring;

    @GET
    public String test(@Context HttpServletResponse response) {
        response.setStatus(500);
        // FIXME
        throw new RuntimeException();
    }

//    @Path("/json")
//    @Produces(MediaType.APPLICATION_JSON)
//    @GET
//    public TestVo test2() {
//        TestVo vo = new TestVo();
//        vo.setName("kevin");
//        vo.setAge(25);
//        vo.setAddress("Nanjing");
//
//        return vo;
//    }
//
//    @Consumes(MediaType.APPLICATION_JSON)
//    @POST
//    public String test3(TestVo vo) {
//        System.out.println(vo);
//        return "TEST3 - SUCCESS";
//    }

    @Path("/list")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    public String test4() {
        test3();
        String r = test6();
        System.out.println(r + "+++++++++++++++++++++");
        System.out.println(Test22.get("My name is Kevin!"));
        System.out.println("----------99999999999999999999");
        System.out.println("New LINE2222222222222222222+++++++");
        return "TEST4 -list" + MyNewMethod() + Test22.getNew() + "WWWW" + testSpring + "End.....";
    }

    @Path("/list9")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    public String test9() {
        return "Test9";
    }

    public String MyNewMethod() {
        return "MyNewMethod";
    }

    @Path("/list2")
    @GET
    public ModelAndView test5() {
        ModelAndView mav = new ModelAndView("test/test2");
        mav.getModelMap().put("hello", "Hello");
        return mav;
    }

    @Path("/query")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    public String query(@BeanParam SimpleQueryVo q) {
        System.out.println("Hello World");

        test3();

        return "HELLO";
    }

    public void test3() {
        System.out.println("New TEST3 METHOD");
    }

    public String test6() {
        System.out.println("New TEST5 METHOD");
        return "NEW return";
    }

}
