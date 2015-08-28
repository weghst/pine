package com.weghst.pine.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kevin Zou
 */
@Controller
public class ErrorHandlingController {

    private static final Logger LOG = LoggerFactory.getLogger("com.weghst.pine.web.ErrorHandling");

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest request, Exception e) {
        LOG.error("ErrorHandling", e);

        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));

        ModelAndView mav = new ModelAndView("error");
        mav.addObject("stackTrace", writer.toString());

        return mav;
    }
}
