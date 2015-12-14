package com.weghst.pine.web.resource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Provider
@Component
public class DefaultExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOG = LoggerFactory.getLogger("com.weghst.pine.web.ErrorHandling");

    @Override
    public Response toResponse(Exception e) {
        LOG.error("ErrorHandling", e);

        Response.ResponseBuilder builder = Response.serverError().entity(e);
        return builder.build();
    }
}
