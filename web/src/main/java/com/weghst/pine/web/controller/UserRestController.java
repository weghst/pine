package com.weghst.pine.web.controller;

import com.weghst.pine.domain.User;
import com.weghst.pine.service.UserService;
import com.weghst.pine.util.BeanUtils;
import com.weghst.pine.web.ErrorCodes;
import com.weghst.pine.web.RestfulException;
import com.weghst.pine.web.vo.PrimitiveVo;
import com.weghst.pine.web.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@RestController
@RequestMapping("/users")
public class UserRestController {

    private static final Logger LOG = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PrimitiveVo save(@RequestBody UserVo userVo) {
        LOG.debug("{}", userVo);

        User user = new User();
        BeanUtils.copyProperties(userVo, user);

        userService.save(user);
        return new PrimitiveVo(user.getId());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getById(@PathVariable("id") int id) {
        User user = userService.get(id);
        if (user == null) {
            throw new RestfulException(ErrorCodes.E12000, "未发现ID为[" + id + "]的用户");
        }
        return user;
    }

    @RequestMapping(method = RequestMethod.GET)
    public User getByEmail(@RequestParam String email) {
        User user = userService.get(email);
        return user;
    }
}
