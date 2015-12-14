package com.weghst.pine.web.resource;

import com.weghst.pine.domain.User;
import com.weghst.pine.service.UserService;
import com.weghst.pine.web.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Component
@Path("/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @GET
    public Restful getById(@PathParam("id") String idStr) {
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            throw new RestfulException(ErrorCodes.E10100, "用户ID必须为数字", e);
        }

        User user = userService.get(id);
        if (user == null) {
            throw new RestfulException(ErrorCodes.E12000, "未发现ID为[" + idStr + "]的用户");
        }
        return new Restful(user);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    @GET
    public Restful getByEmail(@QueryParam("email") String email) {
        User user = userService.get(email);
        return new Restful(user);
    }
}
