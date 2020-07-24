package com.mzx.webcocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author ZhenXinMa
 * @date 2020/7/24 12:19
 */
@Configuration
public class StaticResourceConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //静态资源放行
        registry.addResourceHandler("/bootstrap/**").addResourceLocations("classpath:/bootstrap/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
//        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/images/");
//        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
//        registry.addResourceHandler("/lib/**").addResourceLocations("classpath:/lib/");
//        registry.addResourceHandler("/lib/**").addResourceLocations("classpath:/lib/");

    }



}
