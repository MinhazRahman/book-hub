package com.bookhub.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppRestControllerConfig implements WebMvcConfigurer {
    @Value("${allowed.origins}")
    private String[] theAllowedOrigins;

    @Value("${spring.data.rest.base-path}")
    private String basePath;

    @Override
    public void addCorsMappings(CorsRegistry cors) {
        // set up the cors mapping
        cors.addMapping(basePath + "/**").allowedOrigins(theAllowedOrigins);
    }
}
