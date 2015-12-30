package com.weghst.pine.web.resource;

import com.weghst.pine.web.WebConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public abstract class AbstractExceptionMapper<E extends Throwable> implements ExceptionMapper<E> {

    private static final Logger LOG = LoggerFactory.getLogger(WebConstants.EXCEPTION_MAPPER_LOGGER);

    @Override
    public final Response toResponse(E e) {
        LOG.error("", e);

        return doResponseBuilder(e).build();
    }

    /**
     * @param e
     * @return
     */
    protected abstract Response.ResponseBuilder doResponseBuilder(E e);
}
