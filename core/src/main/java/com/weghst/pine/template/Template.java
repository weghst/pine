package com.weghst.pine.template;

import java.io.IOException;
import java.io.Writer;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public interface Template {

    /**
     *
     * @return
     */
    Object getDelegate();

    /**
     *
     * @param model
     * @param writer
     * @throws IOException
     * @throws TemplateException
     */
    void process(Object model, Writer writer) throws IOException, TemplateException;
}
