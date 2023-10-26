package com.example.service02.javax1.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", "An error occurred" + ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({IOException.class, java.sql.SQLException.class})
    public ModelAndView handleIOException (Exception ex){
        ModelAndView model = new ModelAndView("error");
        model.addObject("errorMessage", ex.getMessage());
        return model;
    }
}
