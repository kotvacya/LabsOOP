package ru.ssau.tk.LR2.operations;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.Assertions;


public class SteppingDifferentialOperatorTest extends TestCase {

    public void testSetStep() {
        RightSteppingDifferentialOperator test = new RightSteppingDifferentialOperator(1e-6);
        Assertions.assertThrows(IllegalArgumentException.class, () -> test.setStep(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> test.setStep(Double.POSITIVE_INFINITY));
        test.setStep(1.0);
        assertEquals(1.0, test.getStep());
    }

    public SteppingDifferentialOperatorTest(String testName) {
        super(testName);
    }

    public static junit.framework.Test suite() {
        return new TestSuite(SteppingDifferentialOperatorTest.class);
    }

}