package com.weghst.pine.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.weghst.pine.Constants;
import com.weghst.pine.TestConstants;
import com.weghst.pine.util.ObjectMapperUtils;
import com.weghst.pine.web.ErrorCodes;
import com.weghst.pine.web.SpringTestSupport;
import com.weghst.pine.web.vo.ErrorResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author zouyong (zouyong@mychebao.com)
 */
public class UserRestControllerTest extends SpringTestSupport {

    private static final Logger LOG = LoggerFactory.getLogger(UserRestControllerTest.class);

    @DataProvider
    public Object[][] loginUserData() {
        Map<String, String> map = new HashMap<>();
        map.put("email", "kevinz@weghst.com");
        map.put("password", "PASSWORD");

        return new Object[][]{{map}};
    }

    @Test
    public void testLogin() throws Exception {
        Map<String, String> loginUser = new HashMap<>();
        loginUser.put("email", Constants.ROOT_EMAIL);
        loginUser.put("password", TestConstants.ROOT_PASSWORD);

        ResultActions resultActions = mockMvc.perform(post("/users/login").contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapperUtils.writeValueAsString(loginUser)));
        resultActions.andExpect(result -> {
            MockHttpServletResponse response = result.getResponse();

            LOG.debug(response.getContentAsString());

            assertTrue(response.getStatus() >= 200 && response.getStatus() < 300,
                    "http status is " + response.getStatus());
        });
    }

    @Test(dataProvider = "loginUserData")
    public void testLogin1(Map<String, String> loginUser) throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/users/login").contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapperUtils.writeValueAsString(loginUser)));
        resultActions.andExpect(result -> {
            MockHttpServletResponse response = result.getResponse();

            LOG.debug(response.getContentAsString());

            ErrorResult errorResult = ObjectMapperUtils.readValue(response.getContentAsString(), ErrorResult.class);
            assertEquals(errorResult.getErrorCode(), ErrorCodes.E12100.getCode());
        });
    }
}