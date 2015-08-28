package com.weghst.pine.web.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Kevin Zou
 */
@Provider
@Component
public class DefaultExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOG = LoggerFactory.getLogger("com.weghst.pine.web.ErrorHandling");

    @Override
    public Response toResponse(Exception e) {
        LOG.error("ErrorHandling", e);

        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));

        ModelAndView mav = new ModelAndView("error");
        mav.addObject("stackTrace", writer.toString());

        Response.ResponseBuilder builder = Response.serverError().entity(mav);
        builder.type(MediaType.TEXT_HTML_TYPE);
        return builder.build();
    }
}
