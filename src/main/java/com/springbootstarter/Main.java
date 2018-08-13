package com.springbootstarter;

import com.springbootstarter.config.ConfigProperties;
import com.springbootstarter.stream.StreamProcessor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@ImportResource("classpath:app-config.xml")
public class Main implements ApplicationRunner {

    private StreamProcessor streamProcessor;
    private ConfigProperties configProperties;

    public void setStreamProcessor(StreamProcessor streamProcessor) {
        this.streamProcessor = streamProcessor;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);

    }

    @EventListener(ApplicationReadyEvent.class)
    public void processingInputStream() {
        streamProcessor.execute();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        streamProcessor.setStreamLocation(((DefaultApplicationArguments) args).getSourceArgs()[0]);
    }
}
