package com.digrabok.crx.rainyhills.web.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

class ValidatingHttpRequest extends HttpServletRequestWrapper {
    ValidatingHttpRequest(HttpServletRequest request) {
        super(request);
    }
}
