package com.springbootstarter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
@Configuration
@ConfigurationProperties
public class ConfigProperties {

    @Autowired
    private Environment env;



    public String getStreamLocation(){
       return env.getProperty("stream_file_path");
    }

}
