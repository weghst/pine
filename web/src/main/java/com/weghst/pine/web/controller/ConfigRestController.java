package com.weghst.pine.web.controller;

import com.weghst.pine.domain.Config;
import com.weghst.pine.service.ConfigService;
import com.weghst.pine.web.annotation.Get;
import com.weghst.pine.web.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Controller
@RequestMapping("/configs")
public class ConfigRestController {

    @Autowired
    public ConfigService configService;

    @Get("/{hello}")
    public Config execute(@PathVariable String hello) {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(hello);

        Config config = new Config();
        config.setKey("hello.config");
        config.setValue("hello.value");
        config.setComments("这是注释");
        return config;
    }

    @Post("/{hello}")
    public Config execute(@RequestBody Config config) {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(config);

        return config;
    }

}
