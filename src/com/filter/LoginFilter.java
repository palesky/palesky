package com.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.bean.UserBean;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
    	
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession();

        String path = servletRequest.getRequestURI();
        UserBean user = (UserBean) session.getAttribute("user");
//        String id=user.getId();
        String id="";
        
        //登录页面无需过滤
        if(path.indexOf("login.jsp") > -1||path.indexOf("LoginServlet.do") > -1) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (id == null || "".equals(id)) {
            servletResponse.sendRedirect("login.jsp");
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}