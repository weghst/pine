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


import com.weghst.pine.web.vo.SimpleQueryVo;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Path("/hello")
@Component
public class AsyncResource {

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
    public ModelAndView test4() {
        ModelAndView mav = new ModelAndView("test");
        mav.getModelMap().put("hello", "Hello");
        return mav;
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
    public void query(@BeanParam SimpleQueryVo q) {
        System.out.println("Hello");
    }
}
