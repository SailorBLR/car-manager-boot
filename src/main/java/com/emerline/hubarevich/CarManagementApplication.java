
package com.emerline.hubarevich;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.web.bind.annotation.RestController;

@EntityScan(
        basePackageClasses = {CarManagementApplication.class, Jsr310JpaConverters.class}
)
@RestController
@EnableAutoConfiguration
@ComponentScan("com.emerline.hubarevich")
@SpringBootApplication (exclude = {SecurityAutoConfiguration.class })
public class CarManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarManagementApplication.class, args);
    }
}
