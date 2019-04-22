package com.example.demo.webComponent2Config;

import com.example.demo.filter.TestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebComponent2Config {

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){

//        新建过滤器注册类
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        添加自定义过滤器
        registrationBean.setFilter(new TestFilter());
//        设置过滤器的URL模式
        registrationBean.addUrlPatterns("/other.html");

        return registrationBean;
    }

}
