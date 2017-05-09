
package com.emerline.hubarevich;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
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

   /* @Bean(name = "dataSource")
    @Primary
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:carsdb;MV_STORE=false");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        // schema init
        Resource initSchema = new ClassPathResource("schema-h2.sql");
        Resource initData = new ClassPathResource("data-h2.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, initData);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);

        return dataSource;
    }*/
}
