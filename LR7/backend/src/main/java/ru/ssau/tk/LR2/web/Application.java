package ru.ssau.tk.LR2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ru.ssau.tk.LR2.jpa.DBConfig;

@SpringBootApplication(scanBasePackages = {"ru.ssau.tk.LR2.ui", "ru.ssau.tk.LR2.web"})
@Import(DBConfig.class)
public class Application {

    @Autowired
    DBConfig dbConfig;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
