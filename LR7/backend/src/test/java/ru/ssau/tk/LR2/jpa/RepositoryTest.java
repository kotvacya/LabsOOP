package ru.ssau.tk.LR2.jpa;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ssau.tk.LR2.functions.CompositeFunction;
import ru.ssau.tk.LR2.functions.IdentityFunction;
import ru.ssau.tk.LR2.functions.MathFunction;
import ru.ssau.tk.LR2.functions.SqrFunction;
import ru.ssau.tk.LR2.hash.HasherFactory;
import ru.ssau.tk.LR2.jpa.model.Log;
import ru.ssau.tk.LR2.jpa.model.MathResult;
import ru.ssau.tk.LR2.jpa.repository.LogRepository;
import ru.ssau.tk.LR2.jpa.repository.MathResultRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@SpringBootTest(classes = {CachedMathFunctionFactory.class, LogRepository.class, MathResultRepository.class, DBConfig.class})
@TestPropertySource(locations = "classpath:test.properties")
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@Import(DBConfig.class)
public class RepositoryTest {

    //private static final Logger log = LoggerFactory.getLogger(RepositoryTest.class);
    @Autowired
    DBConfig dbConfig;

    @Autowired
    MathResultRepository resRepo;

    @Autowired
    CachedMathFunctionFactory factory;

    @Autowired
    LogRepository logRepo;

    @Autowired
    HasherFactory hf;

    @Test
    public void testDatabase(){
        System.out.println(hf);

        resRepo.deleteAll();

        Assert.assertEquals(0, resRepo.count());

        MathResult res1 = new MathResult(10.0, 10.0, 1000);

        resRepo.save(res1);

        Assert.assertEquals(1, resRepo.count());
        Assert.assertEquals(res1, resRepo.findByXAndHash(10.0, 1000));

        resRepo.deleteByXAndHash(10.0, 1000);

        Assert.assertEquals(0, resRepo.count());

        resRepo.save(res1);

        for (int i = 0; i < 100; i++) {
            resRepo.save(new MathResult(i, 100-i, 10000));
        }

        Assert.assertEquals(101, resRepo.count());

        double prev = -Double.MAX_VALUE;
        for (MathResult res : resRepo.findByHashOrderByXDesc(10000)){
            double val = res.getY();

            Assert.assertTrue(val >= prev);
            prev = val;
        }

        Assert.assertEquals(5050.0, (double) resRepo.findByHash(10000).stream().map(MathResult::getY).reduce(0.0, (a, b) -> a + b), Double.MIN_VALUE);


        resRepo.updateYByXAndHash(52.0, 10000, 123.0);

        Assert.assertEquals(123.0, resRepo.findByXAndHash(52.0, 10000).getY(), Double.MIN_VALUE);

    }

    @Test
    public void testMathFunctionRepository(){
        MathFunction func1 = new SqrFunction();
        MathFunction func2 = new IdentityFunction();

        CompositeFunction comp1 = new CompositeFunction(func1, func2);
        CompositeFunction comp2 = new CompositeFunction(func2, func1);

        CachedMathFunction c_func1 = factory.create(comp1);
        CachedMathFunction c_func2 = factory.create(comp2);

        Assert.assertNotEquals(c_func1.getHash(), c_func2.getHash());

        Assert.assertEquals(func1.apply(1.0), c_func1.apply(1.0), Double.MIN_VALUE);
        Assert.assertEquals(func1.apply(1.0), c_func1.apply(1.0), Double.MIN_VALUE);
        Assert.assertEquals(func2.apply(1.0), c_func2.apply(1.0), Double.MIN_VALUE);
        Assert.assertEquals(func2.apply(1.0), c_func2.apply(1.0), Double.MIN_VALUE);
    }

    @Test
    public void testLogRepository(){
        logRepo.deleteAll();

        Instant now = Instant.now();

        Assert.assertEquals(0, logRepo.count());

        Log testLog = new Log("123123", Timestamp.from(now));

        logRepo.save(testLog);

        Assert.assertEquals(1, logRepo.count());

        logRepo.updateTextAndTsById( testLog.getId(),"321321", Timestamp.from(now));
        Assert.assertEquals("321321", logRepo.findById(testLog.getId()).orElse(null).getText());
        Assert.assertEquals(now.truncatedTo(ChronoUnit.MILLIS), logRepo.findById(testLog.getId()).orElse(null).getTs().toInstant().truncatedTo(ChronoUnit.MILLIS));

        logRepo.deleteById(testLog.getId());

        for (int i = 0; i < 100; i++) {
            logRepo.save(new Log("text" + i, Timestamp.from(now.plusSeconds(100-i))));
        }

        Instant prev = Instant.EPOCH;
        for (Log res : logRepo.findAllByOrderByTsAsc()){
            Instant val = res.getTs().toInstant();

            Assert.assertTrue(val.compareTo(prev) >= 0);
            prev = val;
        }

        Assert.assertEquals(100, logRepo.count());

    }

}