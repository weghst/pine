package com.weghst.pine.web.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weghst.pine.web.ErrorCodes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 * @author Zou Yong(zouyong@mychebao.com)
 * @date 2015-09-30 14:55
 */
@Component
@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class JsonMessageBodyWriter implements MessageBodyWriter<Exception> {

    @Autowired
    @Qualifier("viewObjectMapper")
    private ObjectMapper viewObjectMapper;

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return mediaType.equals(MediaType.APPLICATION_JSON_TYPE);
    }

    @Override
    public long getSize(Exception e, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Integer.MAX_VALUE;
    }

    @Override
    public void writeTo(Exception e, Class<?> type, Type genericType, Annotation[] annotations,
                        MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
                        OutputStream entityStream) throws IOException, WebApplicationException {
        Restful restful;
        if (e instanceof RestfulException) {
            restful = new Restful((RestfulException) e);
        } else {
            restful = new Restful(ErrorCodes.E10000.getCode());
        }

        viewObjectMapper.writeValue(new PrintWriter(entityStream), restful);
    }
}
