package com.weghst.pine.template;

/**
 * @author Kevin Zou
 * @date 2015-08-28
 */
public interface TemplateEngine {

    /**
     *
     * @param templateUrl
     * @param executor
     */
    void execute(String templateUrl, TemplateExecutor executor);
}
