package ru.ssau.tk.LR2.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.ssau.tk.LR2.jdbc.model.MathResult;
import ru.ssau.tk.LR2.jdbc.repository.MathResultRepository;

public class main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(DBConfig.class);
        MathResultRepository repo = ctx.getBean(MathResultRepository.class);

        MathResult res = new MathResult();
        res.setY(10);
        res.setX(10);
        res.setHash(1234567910);
        repo.insert(res);


    }
}
