package com.weghst.pine.web.resource;

import com.weghst.pine.domain.User;
import com.weghst.pine.service.UserService;
import com.weghst.pine.web.ErrorCodes;
import com.weghst.pine.web.vo.SimpleQueryVo;
import com.weghst.pine.web.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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

    private static final Logger LOG = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    private UserService userService;

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Restful create(UserVo userVo) {
        LOG.debug("{}", userVo);

        User user = new User();
        BeanUtils.copyProperties(userVo, user);

        userService.save(user);
        return Restful.withPrimitive(user.getId());
    }

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
        return Restful.with(user);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @GET
    public Restful getByEmail(@QueryParam("email") String email) {
        User user = userService.get(email);
        return Restful.with(user);
    }

    @Path("/")
    @GET
    public Restful search(@BeanParam SimpleQueryVo simpleQueryVo) {
        System.out.println(simpleQueryVo);
        return null;
    }
}
