package com.weghst.pine.web.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weghst.pine.web.WebConstants;
import com.weghst.pine.web.resource.Restful;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Component
public class RestfulHandlerExceptionResolver implements HandlerExceptionResolver {

    private static final Logger LOG = LoggerFactory.getLogger(WebConstants.EXCEPTION_MAPPER_LOGGER);

    @Autowired
    @Qualifier("viewObjectMapper")
    private ObjectMapper objectMapper;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        if (ex instanceof WebApplicationException) {
            LOG.error("", ex);

            Response.StatusType statusType = ((WebApplicationException) ex).getResponse().getStatusInfo();

            response.setHeader("Content-Type", MediaType.APPLICATION_JSON);
            response.setStatus(statusType.getStatusCode());

            Restful restful = Restful.with(statusType.getStatusCode(), statusType.getReasonPhrase());
            try {
                objectMapper.writeValue(response.getOutputStream(), restful);
            } catch (IOException e) {
                // ignore
            }
        }
        return null;
    }
}
