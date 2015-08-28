package com.weghst.pine.web.resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/")
@Component
public class AdminResource {

    @Path("/dashboard")
    @GET
    public ModelAndView doDashboard() {
        ModelAndView mav = new ModelAndView("dashboard");

        return mav;
    }

    @Path("/login")
    @GET
    public ModelAndView doLoginView() {
        return null;
    }

    @Path("/login")
    @POST
    public String doLogin() {
        return "redirect:/dashboard";
    }

    @Path("/logout")
    @GET
    public String doLogout() {
        return null;
    }

    private String loadAdminMenu() {
        return null;
    }
}
