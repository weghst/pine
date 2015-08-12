/**
 * Copyright (C) 2015 The Weghst Inc. (kevinz@weghst.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.weghst.pine.web;

import com.weghst.pine.ConfigUtils;
import com.weghst.pine.PineException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationContextListener implements ServletContextListener {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.info("Pine initializing...");

        loadPineProperties();

        LOG.info("Pine initialized...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.info("Pine destroyed...");
    }

    private void loadPineProperties() {
        try {

            URL url = ResourceUtils.getURL("classpath:pine.properties");
            LOG.info("load pine properties: [{}]", url);

            InputStream in = null;
            try {

                in = url.openStream();
                Properties properties = new Properties();
                properties.load(in);

                for (String name : properties.stringPropertyNames()) {
                    ConfigUtils.setProperty(name, properties.getProperty(name));
                }
            } catch (IOException e) {
                throw new PineException("加载[" + url + "]文件错误", e);
            } finally {
                IOUtils.closeQuietly(in);
            }
        } catch (FileNotFoundException e) {
            throw new PineException(e);
        }
    }
}
