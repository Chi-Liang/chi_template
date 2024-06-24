package com.fundodo.fayaospace.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class HttpTools {
    private static String authEntryPoint;

    public static String getIp() {
        HttpServletRequest request = getRequest();

        String ipAddress = request.getHeader("X-Forwarded-For");

        if (StringUtils.isBlank(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if (StringUtils.isBlank(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }

        return ipAddress;
    }

    public static boolean isApiRequest() {
        return getRequest().getServletPath().replace(getContextPath(), "").startsWith("/api");
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return Objects.requireNonNull(servletRequestAttributes).getRequest();
    }

    public static HttpServletResponse getResponse() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return Objects.requireNonNull(servletRequestAttributes).getResponse();
    }

    public static String getContextPath() {
        return getRequest().getContextPath();
    }

    public static String getUri() {
        return getRequest().getRequestURI();
    }

    public static String getQueryUri() {
        String uri = getRequest().getRequestURI();
        String queryString = getRequest().getQueryString();

        if (Objects.nonNull(queryString)) {
            uri = uri.concat("?").concat(queryString);
        }

        return uri;
    }

    public static boolean isLocal() {
        return StringUtils.equalsAny(getRequest().getRemoteAddr(), "127.0.0.1", "0:0:0:0:0:0:0:1");
    }

    public static HttpSession getSession() {
        return getRequest().getSession(Boolean.FALSE);
    }
}
