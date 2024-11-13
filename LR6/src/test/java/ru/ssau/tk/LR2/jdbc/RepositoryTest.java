package ru.ssau.tk.LR2.jdbc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ssau.tk.LR2.functions.CompositeFunction;
import ru.ssau.tk.LR2.functions.IdentityFunction;
import ru.ssau.tk.LR2.functions.MathFunction;
import ru.ssau.tk.LR2.functions.SqrFunction;
import ru.ssau.tk.LR2.jdbc.model.Log;
import ru.ssau.tk.LR2.jdbc.model.MathResult;
import ru.ssau.tk.LR2.jdbc.repository.LogRepository;
import ru.ssau.tk.LR2.jdbc.repository.MathResultRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@SpringBootTest(classes = {MathResultRepository.class, DBConfig.class})
@TestPropertySource(locations = "classpath:test.properties")
@RunWith(SpringRunner.class)
public class RepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(RepositoryTest.class);
    @Autowired
    MathResultRepository resRepo;

    @Autowired
    CachedMathFunctionFactory factory;

    @Autowired
    LogRepository logRepo;

    @Test
    public void testDatabase(){
        resRepo.deleteAll();

        Assert.assertEquals(0, resRepo.getCount());

        MathResult res1 = new MathResult(10.0, 10.0, 1000);

        resRepo.insert(res1);

        Assert.assertEquals(1, resRepo.getCount());
        Assert.assertEquals(res1, resRepo.findByXAndHash(10.0, 1000));

        resRepo.deleteByXAndHash(10.0, 1000);

        Assert.assertEquals(0, resRepo.getCount());

        resRepo.insert(res1);

        for (int i = 0; i < 100; i++) {
            resRepo.insert(new MathResult(i, 100-i, 10000));
        }

        Assert.assertEquals(101, resRepo.getCount());

        double prev = -Double.MAX_VALUE;
        for (MathResult res : resRepo.findByHashSortedByX(10000)){
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

        Assert.assertEquals(0, logRepo.getCount());

        Log testLog = new Log("123123", Timestamp.from(now));

        logRepo.insert(testLog);

        Assert.assertEquals(1, logRepo.getCount());

        logRepo.updateTextAndTsById( testLog.getId(),"321321", Timestamp.from(now));
        Assert.assertEquals("321321", logRepo.findById(testLog.getId()).getText());
        Assert.assertEquals(now.truncatedTo(ChronoUnit.MILLIS), logRepo.findById(testLog.getId()).getTs().toInstant());

        logRepo.deleteById(testLog.getId());

        for (int i = 0; i < 100; i++) {
            logRepo.insert(new Log("text" + i, Timestamp.from(now.plusSeconds(100-i))));
        }

        Instant prev = Instant.EPOCH;
        for (Log res : logRepo.findSortedByTimestamp()){
            Instant val = res.getTs().toInstant();

            Assert.assertTrue(val.compareTo(prev) >= 0);
            prev = val;
        }

        Assert.assertEquals(100, logRepo.getCount());

    }

}