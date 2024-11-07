package ru.ssau.tk.LR2.jdbc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ssau.tk.LR2.functions.CompositeFunction;
import ru.ssau.tk.LR2.functions.IdentityFunction;
import ru.ssau.tk.LR2.functions.MathFunction;
import ru.ssau.tk.LR2.functions.SqrFunction;
import ru.ssau.tk.LR2.jdbc.model.MathResult;
import ru.ssau.tk.LR2.jdbc.repository.MathResultRepository;

@SpringBootTest(classes = {MathResultRepository.class, DBConfig.class})
@TestPropertySource(locations = "classpath:test.properties")
@RunWith(SpringRunner.class)
public class RepositoryTest {

    @Autowired
    MathResultRepository repo;

    @Autowired
    CachedMathFunctionFactory factory;

    @Test
    public void testDatabase(){
        repo.deleteAll();

        Assert.assertEquals(0, repo.getCount());

        MathResult res1 = new MathResult(10.0, 10.0, 1000);

        repo.insert(res1);

        Assert.assertEquals(1, repo.getCount());
        Assert.assertEquals(res1, repo.findByXAndHash(10.0, 1000));

        repo.deleteByXAndHash(10.0, 1000);

        Assert.assertEquals(0, repo.getCount());

        repo.insert(res1);

        for (int i = 0; i < 100; i++) {
            repo.insert(new MathResult(i, 100-i, 10000));
        }

        Assert.assertEquals(101, repo.getCount());

        double prev = -Double.MAX_VALUE;
        for (MathResult res : repo.findByHashSortedByX(10000)){
            double val = res.getY();

            Assert.assertTrue(val >= prev);
            prev = val;
        }

        Assert.assertEquals(5050.0, (double)repo.findByHash(10000).stream().map(MathResult::getY).reduce(0.0, (a, b) -> a + b), Double.MIN_VALUE);


        repo.updateYByXAndHash(52.0, 10000, 123.0);

        Assert.assertEquals(123.0, repo.findByXAndHash(52.0, 10000).getY(), Double.MIN_VALUE);

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

}