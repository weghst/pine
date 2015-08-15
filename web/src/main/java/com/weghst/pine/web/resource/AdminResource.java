package com.weghst.pine.web.resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
@Component
public class AdminResource {

    @Path("/dashboard")
    @GET
    public ModelAndView execute() {
        ModelAndView mav = new ModelAndView("dashboard");

        return mav;
    }
}
