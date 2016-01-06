package com.weghst.pine.web;

import com.weghst.pine.Constants;
import com.weghst.pine.PineException;
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
@WebListener("Web configuration listener")
public class WebConfigurationListener implements ServletContextListener {

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

                String msg = String.format("load pine properties: %s", url);
                System.out.println(msg);

                Properties properties = new Properties();
                properties.load(in);

                String val;
                for (String name : properties.stringPropertyNames()) {
                    val = properties.getProperty(name);
                    System.setProperty(name, val);

                    msg = String.format("set system property[%s: %s]", name, val);
                    System.out.println(msg);
                }
            } catch (FileNotFoundException e) {
                if (ignoreResourceNotFound) {
                    System.out.println("ignore not found resource: " + url);
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
                System.out.println("ignore not found resource: " + path);
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
        dynamic.addMapping("/*");
    }

}
