package org.jiang.shiro.filter;

import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Li.Linhua
 * @description 自定义filter
 * @Date 2019/7/18
 */
public class MyFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        if (!(servletRequest instanceof HttpServletRequest)) {
            return true;
        }
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        ServletContext servletContext = httpServletRequest.getServletContext();
        Map<String, ? extends FilterRegistration> filterRegistrations = servletContext.getFilterRegistrations();
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return false;
    }
}
