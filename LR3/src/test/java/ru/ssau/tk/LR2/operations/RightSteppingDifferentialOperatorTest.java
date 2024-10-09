package ru.ssau.tk.LR2.operations;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import ru.ssau.tk.LR2.functions.ConstantFunction;
import ru.ssau.tk.LR2.functions.MathFunction;
import ru.ssau.tk.LR2.functions.SqrFunction;

import static org.junit.jupiter.api.Assertions.*;

public class RightSteppingDifferentialOperatorTest extends TestCase {

    public void testDerive() {
        MathFunction sqr = new SqrFunction();
        MathFunction ct = new ConstantFunction(52.);

        RightSteppingDifferentialOperator ls_do = new RightSteppingDifferentialOperator(1e-6);

        MathFunction mf1 = ls_do.derive(sqr);
        MathFunction mf2 = ls_do.derive(ct);

        for (int i = 0; i < 100; ++i) {
            assertEquals(2. * i, mf1.apply(i), 0.1);
            assertEquals(0., mf2.apply(i), 0.1);
        }
    }

    public RightSteppingDifferentialOperatorTest(String testName) {
        super(testName);
    }

    public static junit.framework.Test suite() {
        return new TestSuite(RightSteppingDifferentialOperatorTest.class);
    }
}