package com.example.service02.javax1.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    @Autowired
    HttpSession session;

    public <T> T get(String name,String defaultValue) {
        T value = (T) session.getAttribute(name);
        if(value != null) {
            return value;
        }
        return (T) defaultValue;
    }

    public void set(String name, Object value) {
        session.setAttribute(name, value);
    }

    public void remove(String name) {
        session.removeAttribute(name);
    }
}
