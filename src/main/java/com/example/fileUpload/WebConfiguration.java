package com.example.fileUpload;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {
    public String path = "C:///Users/Vova/Desktop/Study/YAPZ/demo/src/main/resources/uploads/";
//    public String json = "C:///Users/Vova/Desktop/Study/YAPZ/demo/src/main/resources/json/";
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("**")
                .addResourceLocations("file:" + path);
        registry.addResourceHandler("view/**")
                .addResourceLocations("file:" + path);
//        registry.addResourceHandler("**")
//                .addResourceLocations("file:" + json);
    }
}
