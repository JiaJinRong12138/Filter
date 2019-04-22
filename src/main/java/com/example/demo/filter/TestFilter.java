package com.example.demo.filter;

import org.apache.catalina.Session;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@WebFilter(filterName = "TestFilter", urlPatterns = {"/other.html"})
public class TestFilter implements Filter {

//    不需要过滤的url
    String[] includeUrls = new String[]{"/index.html", "/register"};
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>初始化>>>>>>>>>>>>>>>>>>>>>");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>doFilter>>>>>>>>>>>>>>>>>>>>>");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();

        System.out.println("filter url:" + uri);
        if(!isNeedFilter(uri)){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            if(session != null && session.getAttribute("name")!=null){
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                String requestType = request.getHeader("X-Requested-With");
                if(requestType != null && "XMLHttpRequest".equals(requestType)){
                    response.getWriter().write("NO LOGIN");
                }else{
                    response.sendRedirect(request.getContextPath() + "/index.html");
                }
            }
        }



    }

    @Override
    public void destroy() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>销毁>>>>>>>>>>>>>>>>>>>>>");
    }


    //是否需要过滤
    public boolean isNeedFilter(String uri){
        for(String url:includeUrls){
            if(uri.equals(url)){
                return false;
            }
        }
        return true;
    }
}
