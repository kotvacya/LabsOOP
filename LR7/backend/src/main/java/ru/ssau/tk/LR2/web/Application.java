package ru.ssau.tk.LR2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import ru.ssau.tk.LR2.jpa.DBConfig;

@SpringBootApplication(scanBasePackages = {"ru.ssau.tk.LR2.ui", "ru.ssau.tk.LR2.web"})
@Import(DBConfig.class)
@PropertySource("classpath:application.properties")
public class Application {

    @Autowired
    DBConfig dbConfig;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
