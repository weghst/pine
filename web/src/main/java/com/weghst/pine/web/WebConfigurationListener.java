package com.weghst.pine.web;

import com.weghst.pine.Constants;
import com.weghst.pine.PineException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.EnumSet;
import java.util.Properties;

@WebListener("Web configuration listener")
public class WebConfigurationListener implements ServletContextListener {

    private static final Logger LOG = LoggerFactory.getLogger(WebConfigurationListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();

        // 加载系统基础配置
        loadPineProperties("classpath:pine.properties", false);
        loadPineProperties(System.getProperty("user.home") + "/.pine/pine.properties", true);

        // 注册JavaEE组件
        registerCharacterEncodingFilter(sc);
        registerDispatcherServlet(sc);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    private void loadPineProperties(String path, boolean ignoreResourceNotFound) {
        try {

            URL url = ResourceUtils.getURL(path);
            InputStream in = null;
            try {

                in = url.openStream();
                LOG.info("load pine properties: [{}]", url);

                Properties properties = new Properties();
                properties.load(in);

                String val;
                for (String name : properties.stringPropertyNames()) {
                    val = properties.getProperty(name);
                    System.setProperty(name, val);

                    LOG.info("set system property[{}: {}]", name, val);
                }
            } catch (FileNotFoundException e) {
                if (ignoreResourceNotFound) {
                    LOG.info("ignore not found resource: {}", url);
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
                LOG.info("ignore not found resource: {}", path);
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
        XmlWebApplicationContext webApplicationContext = new XmlWebApplicationContext();
        webApplicationContext.setConfigLocation("classpath:spring-pine-web.xml");

        DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);
        ServletRegistration.Dynamic dynamic = sc.addServlet("dispatcherServlet", dispatcherServlet);
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping(System.getProperty(Constants.RESTFUL_PATH_PREFIX_PROP) + "/*");
    }

}
