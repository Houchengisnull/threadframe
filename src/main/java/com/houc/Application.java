package com.houc;

import com.houc.ui.ThreadFrame;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(basePackages = {"com.houc.ui", "com.houc.service", "com.houc.common"})
@EnableScheduling
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
        builder.run(args).getBean(ThreadFrame.class).rendering();
    }

}
