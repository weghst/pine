package com.weghst.pine.service.impl;

import com.weghst.pine.SpringTestSupport;
import com.weghst.pine.domain.User;
import com.weghst.pine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public class UserServiceImplTest extends SpringTestSupport {

    @Autowired
    private UserService userService;

    @Test
    public void testRegisterForMobile() throws Exception {
        User user = new User();
        user.setMobile("13085162323");
        user.setPassword("[PASSWORD]");
        user.setEnabled(true);

        String mobile = "13085162323";
        String inteCode = userService.sendMobileValidate(mobile);

        userService.registerForMobile(user, inteCode);
    }

    @Test
    public void testSendMobileValidate() throws Exception {
        String mobile = "13085162323";
        String inteCode = userService.sendMobileValidate(mobile);
        assertTrue(inteCode.length() == 6);
    }

    @Test
    public void testMobileValidate() throws Exception {
        String mobile = "13085162323";
        String inteCode = userService.sendMobileValidate(mobile);
        assertTrue(userService.mobileValidate(mobile, inteCode));
    }
}