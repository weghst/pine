package com.weghst.pine.template;

import com.weghst.pine.PineException;

import freemarker.template.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class FreemarkerTemplateEngine implements TemplateEngine {

    private static final Logger LOG = LoggerFactory.getLogger(TemplateEngine.class);

    private Configuration configuration;

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void execute(String templateUrl, TemplateExecutor executor) {
        LOG.debug("process template [{}]", templateUrl);

        try {
            freemarker.template.Template template = configuration.getTemplate(templateUrl);
            executor.doExecute(new FreemarkerTemplate(template));
        } catch (IOException e) {
            LOG.debug("读取模板[{}]错误", templateUrl, e);
            throw new PineException(e);
        } catch (TemplateException e) {
            LOG.debug("解析模板[{}]错误", templateUrl, e);
            throw new PineException(e);
        }
    }
}
