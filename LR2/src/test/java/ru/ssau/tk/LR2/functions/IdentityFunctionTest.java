package ru.ssau.tk.LR2.functions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class IdentityFunctionTest extends TestCase {

    public IdentityFunctionTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( IdentityFunctionTest.class );
    }

    public void testApply()
    {
        IdentityFunction func = new IdentityFunction();

        assertEquals(0.0, func.apply(0.0));
        assertEquals(Math.E, func.apply(Math.E));
        assertEquals(Double.MIN_NORMAL, func.apply(Double.MIN_NORMAL));

    }

}