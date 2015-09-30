package com.weghst.pine.web.resource;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 * @author Kevin Zou
 */
@Component
@Provider
@Produces(MediaType.TEXT_HTML)
public class HtmlMessageBodyWriter implements MessageBodyWriter<Exception> {

    @Autowired
    private FreeMarkerConfig freeMarkerConfig;

    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations,
                               MediaType mediaType) {
        return mediaType.equals(MediaType.TEXT_HTML_TYPE);
    }

    @Override
    public long getSize(Exception e, Class<?> aClass, Type type, Annotation[] annotations,
                        MediaType mediaType) {
        return Integer.MAX_VALUE;
    }

    @Override
    public void writeTo(Exception e, Class<?> aClass, Type type, Annotation[] annotations,
                        MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap,
                        OutputStream outputStream) throws IOException, WebApplicationException {

        Configuration configuration = freeMarkerConfig.getConfiguration();
        Template template = configuration.getTemplate("error.ftl");

        Map<String, Object> model = new HashMap<>();

        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        model.put("stackTrace", stringWriter.toString());

        try {
            template.process(model, new PrintWriter(outputStream));
        } catch (TemplateException ex) {
            throw new WebApplicationException(ex);
        }
    }
}
