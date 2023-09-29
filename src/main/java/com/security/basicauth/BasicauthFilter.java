package com.security.basicauth;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class BasicauthFilter implements Filter {

    private static final Map<String, String> hashMap = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        hashMap.put("admin", "password");
        hashMap.put("ajay", "@j@y");
        hashMap.put("ping", "pong");
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String authHeader = httpRequest.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Basic ")) {
            String credentials = new String(Base64.getDecoder().decode(authHeader.substring(6)), StandardCharsets.UTF_8);
            String[] usernamePassword = credentials.split(":");
            String username = usernamePassword[0];
            String password = usernamePassword[1];

            if (hashMap.containsKey(username) && hashMap.get(username).equals(password)) {
                System.out.println("["+username+"]"+" Accessing the data");
                httpRequest.setAttribute("username", username);
                filterChain.doFilter(httpRequest, httpResponse);
                return ;
            }


        }

        httpResponse.setHeader("WWW-Authenticate", "Basic realm=\"Restricted Area\"");
        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
