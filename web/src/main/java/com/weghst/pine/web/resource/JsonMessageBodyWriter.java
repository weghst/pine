package com.weghst.pine.web.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weghst.pine.Pines;
import com.weghst.pine.web.ErrorCodes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 * {@code JSON} 消息输出实现.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Component
@Provider
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class JsonMessageBodyWriter implements MessageBodyWriter<Exception> {

    @Autowired
    @Qualifier("viewObjectMapper")
    private ObjectMapper viewObjectMapper;

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        // 所有的异常都使用 JSON 格式输出至客户端
        return true;
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
            restful = Restful.withException((RestfulException) e);
        } else {
            restful = Restful.withErrorCode(ErrorCodes.E10000);
        }

        // OutputStreamWriter writer = new OutputStreamWriter(entityStream, Pines.charset());
        viewObjectMapper.writeValue(entityStream, restful);
    }
}
