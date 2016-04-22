package com.weghst.pine.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.EnumSet;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.annotation.WebListener;

import com.weghst.pine.service.ConfigService;
import com.weghst.pine.service.impl.ConfigServiceImpl;
import com.weghst.pine.web.controller.ConfigRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.session.SessionRepository;
import org.springframework.session.web.http.SessionRepositoryFilter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import ch.qos.logback.ext.spring.web.WebLogbackConfigurer;
import com.weghst.pine.Constants;
import com.weghst.pine.PineException;

/**
 * Pine Web 配置监听器实现.
 * <ol>
 * <li>最先加载配置 {@code classpath:pine.properties}, 然后再加载配置 {@code ~/.pine/pine.properties}.
 * 如果 {@code ~/.pine/pine.properties}不存在则忽略, 如果配置属性名称相同则后加载会覆盖之前的属性值.</li>
 * <li>注册字符编码过滤器 {@link CharacterEncodingFilter}, 编码设置 {@link Constants#ENCODING_PROP}.</li>
 * <li>注册 {@link DispatcherServlet} 并初始化Spring配置, 默认加载配置文件为 {@code classpath:spring-pine-web.xml}.</li>
 * </ol>
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
@WebListener("Web configurer listener")
public class WebConfigurerListener extends ContextLoader implements ServletContextListener {

    private WebApplicationContext applicationContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();

        // 初始化logback
        sc.setInitParameter(WebLogbackConfigurer.CONFIG_LOCATION_PARAM, findLogbackConfigLocation());
        WebLogbackConfigurer.initLogging(sc);

        // 加载系统基础配置
        loadPineProperties("classpath:pine.properties", false);
        loadPineProperties(System.getProperty("user.home") + "/.pine/pine.properties", true);

        // 初始化Spring配置
        sc.setInitParameter(ContextLoader.CONFIG_LOCATION_PARAM, "classpath:spring-pine-web.xml");
        applicationContext = initWebApplicationContext(sc);

        // 注册JavaEE组件
        registerCharacterEncodingFilter(sc);
        registerDispatcherServlet(sc);

        // 注册SessionFilter
        registerSessionRepositoryFilter(sc);


        // ====================================== 测试重新注入
        ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
        ConfigServiceImpl configService = (ConfigServiceImpl) applicationContext.getBean(ConfigService.class);
        ConfigRestController configRestController = applicationContext.getBean(ConfigRestController.class);

        System.out.println(configService.testKey);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");

        System.setProperty("pine.timeZone", "GMT+12:0000000000000000000000");

        applicationContext.getAutowireCapableBeanFactory().configureBean(configService, "configServiceImpl");
        applicationContext.getAutowireCapableBeanFactory().configureBean(configRestController, "configRestController");

        System.out.println(configService.testKey);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++" + configRestController.configService.hashCode());

        configRestController = applicationContext.getBean(ConfigRestController.class);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++" + configRestController.configService.hashCode());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        getLog().info("pine destroy");

        closeWebApplicationContext(sce.getServletContext());

        WebLogbackConfigurer.shutdownLogging(sce.getServletContext());
    }

    private String findLogbackConfigLocation() {
        File file = new File(System.getProperty("user.home") + "/.pine/logback.xml");
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        return "classpath:logback.xml";
    }

    private void loadPineProperties(String path, boolean ignoreResourceNotFound) {
        try {

            URL url = ResourceUtils.getURL(path);
            InputStream in = null;
            try {

                in = url.openStream();

                getLog().info("load pine properties: {}", url);

                Properties properties = new Properties();
                properties.load(in);

                String val;
                for (String name : properties.stringPropertyNames()) {
                    val = properties.getProperty(name);
                    System.setProperty(name, val);

                    getLog().info("set system property[%s: %s]", name, val);
                }
            } catch (FileNotFoundException e) {
                if (ignoreResourceNotFound) {
                    getLog().info("ignore not found resource: {}", url);
                } else {
                    throw new PineException(e);
                }
            } catch (IOException e) {
                throw new PineException("加载[" + url + "]文件错误", e);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                    }
                }
            }
        } catch (FileNotFoundException e) {
            if (ignoreResourceNotFound) {
                getLog().info("ignore not found resource: {}", path);
            } else {
                throw new PineException(e);
            }
        }
    }

    private void registerCharacterEncodingFilter(ServletContext sc) {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding(System.getProperty(Constants.ENCODING_PROP));
        characterEncodingFilter.setForceEncoding(true);

        FilterRegistration.Dynamic filterRegistration = sc.addFilter("characterEncodingFilter", characterEncodingFilter);
        filterRegistration.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

    private void registerDispatcherServlet(ServletContext sc) {
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
        ServletRegistration.Dynamic dynamic = sc.addServlet("dispatcherServlet", dispatcherServlet);
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("/v1/*");
        // dynamic.addMapping("/*");
    }

    private void registerSessionRepositoryFilter(ServletContext sc) {
        SessionRepository sessionRepository = applicationContext.getBean(SessionRepository.class);

        // FIXME: SessionRepositoryFilter -> commitSession() 该方法会被调用多次, 后期需要优化
        SessionRepositoryFilter sessionRepositoryFilter = new SessionRepositoryFilter(sessionRepository);

        FilterRegistration.Dynamic filterRegistration = sc.addFilter("sessionRepositoryFilter", sessionRepositoryFilter);
        filterRegistration.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

    private Logger getLog() {
        return LoggerFactory.getLogger(WebLogbackConfigurer.class);
    }

}
