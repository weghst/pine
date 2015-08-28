package com.weghst.pine.template;

import java.io.IOException;

/**
 * @author Kevin Zou
 */
public interface TemplateExecutor {

    /**
     *
     * @param template
     * @throws Exception
     */
    void doExecute(Template template) throws IOException, TemplateException;
}
