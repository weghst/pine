package com.weghst.pine.web.resource;


import org.jboss.resteasy.spi.LoggableFailure;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Provider
@Component
public class DefaultExceptionMapper extends AbstractExceptionMapper<Exception> {

    @Override
    protected Response.ResponseBuilder doResponseBuilder(Exception e) {
        Response.ResponseBuilder builder;
        if (e instanceof BadRequestException || e instanceof RestfulException) {
            builder = Response.status(Response.Status.BAD_REQUEST);
        } else if (e instanceof NotAuthorizedException) {
            builder = Response.status(Response.Status.UNAUTHORIZED);
        } else if (e instanceof NotAllowedException) {
            builder = Response.status(Response.Status.METHOD_NOT_ALLOWED);
        } else if (e instanceof NotAcceptableException) {
            builder = Response.status(Response.Status.NOT_ACCEPTABLE);
        } else if (e instanceof NotFoundException) {
            builder = Response.status(Response.Status.NOT_FOUND);
        } else if (e instanceof LoggableFailure) {
            Response.Status status = Response.Status.fromStatusCode(((LoggableFailure) e).getErrorCode());
            if (status == null) {
                status = Response.Status.INTERNAL_SERVER_ERROR;
            }
            builder = Response.status(status);
        } else {
            builder = Response.serverError();
        }
        return builder.header("Content-Type", MediaType.APPLICATION_JSON).entity(e);
    }
}
