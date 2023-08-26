
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@SpringBootApplication
public class SlmsApplication {

    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }


    public static void main(String[] args) {
        SpringApplication.run(SlmsApplication.class, args);
    }

}
