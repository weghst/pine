package com.weghst.pine.template;

import java.io.IOException;
import java.io.Writer;

/**
 * @author Kevin Zou
 */
class FreemarkerTemplate implements Template {

    freemarker.template.Template template;

    FreemarkerTemplate(freemarker.template.Template template) {
        this.template = template;
    }

    @Override
    public Object getDelegate() {
        return template;
    }

    @Override
    public void process(Object model, Writer writer) throws IOException, TemplateException {
        try {
            template.process(model, writer);
        } catch (freemarker.template.TemplateException e) {
            throw new TemplateException("解析[" + template.getName() + "]模板错误", e);
        }
    }
}
