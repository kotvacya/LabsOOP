package ru.ssau.tk.LR2.hash;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.Assertions;
import ru.ssau.tk.LR2.functions.*;
import ru.ssau.tk.LR2.operations.LeftSteppingDifferentialOperatorTest;


public class BasicHasherTest extends TestCase {
    public void testHasher() {
        Hasher h1 = new BasicHasher();
        Hasher h2 = new BasicHasher();
        Hasher h3 = new BasicHasher();

        h1.addInt(1);
        h2.addInt(1);
        h3.addInt(2);
        assertEquals(h1.getHash(MathFunction.class), h2.getHash(MathFunction.class));
        Assertions.assertNotEquals(h1.getHash(MathFunction.class), h3.getHash(MathFunction.class));

        h1.addDouble(1.);
        h2.addString("1");
        h1.addString("1");
        h2.addDouble(1.);
        Assertions.assertNotEquals(h1.getHash(MathFunction.class), h3.getHash(MathFunction.class));
    }

    public BasicHasherTest(String testName) {
        super(testName);
    }

    public static junit.framework.Test suite() {
        return new TestSuite(BasicHasherTest.class);
    }

}