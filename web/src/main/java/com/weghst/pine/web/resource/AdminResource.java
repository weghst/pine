package com.weghst.pine.web.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weghst.pine.PineException;
import com.weghst.pine.web.vo.SidebarMenuVo;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

@Path("/")
@Component
public class AdminResource implements InitializingBean {

    @Autowired
    @Qualifier("viewObjectMapper")
    private ObjectMapper objectMapper;
    @Autowired
    private ResourceLoader resourceLoader;

    private SidebarMenuVo[] sidebarMenus;

    @Override
    public void afterPropertiesSet() throws Exception {
        Resource resource = resourceLoader.getResource("classpath:admin_menu.json");
        try {
            sidebarMenus = objectMapper.readValue(resource.getFile(), SidebarMenuVo[].class);
        } catch (IOException e) {
            throw new PineException("加载[classpath:admin_menu.json]文件错误", e);
        }
    }

    @Path("/dashboard")
    @GET
    public ModelAndView doDashboard(@Context HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("dashboard");

        try {
            mav.addObject("sidebarMenu", buildSidebar(request));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @Path("/{id}")
    @GET
    public String test1(@PathParam("id") String id, @MatrixParam("type") String type) {
        return "HELLO test1";
    }

    @Path("/{id}")
    @GET
    public String test2(@PathParam("id") String id, @MatrixParam("type2") String type2) {
        return "hello TEST2";
    }

    private String buildSidebar(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<ul class=\"sidebar-menu\">");
        for (SidebarMenuVo menu : sidebarMenus) {
            buildSidebar0(request, menu, sb, 1);
        }
        sb.append("</ul>");
        return sb.toString();
    }

    private void buildSidebar0(HttpServletRequest request, SidebarMenuVo menu,
                               StringBuilder sb, int level) {
        if (level > 3) {
            throw new IllegalArgumentException("Sidebar Menu 最多只允许有3级菜单");
        }

        sb.append("<li>").append("<a href=\"#\"");
        if (!StringUtils.isEmpty(menu.getUrl()) && !"#".equals(menu.getUrl())) {
            sb.append(" ng-click=\"sidebarMenuClick('").append(request.getContextPath())
                    .append(menu.getUrl()).append("')\"");
        }
        sb.append(">");

        if (menu.getIcon() != null) {
            sb.append("<i class=\"").append(menu.getIcon()).append("\"></i>");
        }

        sb.append(" <span>").append(menu.getText()).append("</span>");
        if (ArrayUtils.isNotEmpty(menu.getSubmenus())) {
            sb.append("<i class=\"fa fa-angle-left pull-right\"></i>");
        }
        sb.append("</a>");

        if (ArrayUtils.isNotEmpty(menu.getSubmenus())) {
            sb.append("<ul class=\"treeview-menu\">");

            for (SidebarMenuVo sub : menu.getSubmenus()) {
                buildSidebar0(request, sub, sb, level + 1);
            }
            sb.append("</ul>");
        }

        sb.append("</li>");
    }

}
