package com.weghst.pine.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.weghst.pine.domain.User;
import com.weghst.pine.service.PasswordNotMatchedException;
import com.weghst.pine.service.UserNotFoundException;
import com.weghst.pine.service.UserService;
import com.weghst.pine.util.BeanUtils;
import com.weghst.pine.web.ErrorCodes;
import com.weghst.pine.web.RestfulException;
import com.weghst.pine.web.vo.ErrorResult;
import com.weghst.pine.web.vo.PrimitiveVo;
import com.weghst.pine.web.vo.UserVo;

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

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Object login(@RequestBody UserVo userVo) {
        if (!StringUtils.hasLength(userVo.getEmail())) {
            throw new RestfulException(ErrorCodes.E10100, "邮箱格式错误");
        }
        if (!StringUtils.hasLength(userVo.getPassword())) {
            throw new RestfulException(ErrorCodes.E10100, "密码格式错误");
        }

        try {
            return userService.login(userVo.getEmail(), userVo.getPassword());
        } catch (UserNotFoundException | PasswordNotMatchedException e) {
            LOG.debug("登录失败", userVo.getEmail(), e);
            return new ErrorResult(ErrorCodes.E12100);
        }
    }

}
