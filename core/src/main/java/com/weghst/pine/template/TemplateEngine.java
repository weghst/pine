package com.weghst.pine.template;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public interface TemplateEngine {

    /**
     *
     * @param templateUrl
     * @param executor
     */
    void execute(String templateUrl, TemplateExecutor executor);
}
