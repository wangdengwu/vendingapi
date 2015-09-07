package com.easygovm.vendingapi;

import com.easygovm.vendingapi.web.TestFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dwwang on 9/2/15.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Bean
    public FilterRegistrationBean authorizationFilter(){
        FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        TestFilter authorizationFilter = new TestFilter();
        filterRegBean.setFilter(authorizationFilter);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");
        filterRegBean.setUrlPatterns(urlPatterns);
        return filterRegBean;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
