package ru.ssau.tk.LR2.jdbc.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.junit.jupiter.api.Assertions.*;

public class MathResultTest extends TestCase {

    public void testSettersGetters(){
        MathResult res = new MathResult();

        res.setY(1.0);
        assertEquals(1.0, res.getY());
        res.setY(-10.0);
        assertEquals(-10.0, res.getY());

        res.setX(1.0);
        assertEquals(1.0, res.getX());
        res.setX(-10.0);
        assertEquals(-10.0, res.getX());

        res.setHash(1);
        assertEquals(1, res.getHash());
        res.setHash(1234567891011L);
        assertEquals(1234567891011L, res.getHash());
    }

    public void testHash(){
        MathResult res1 = new MathResult(1,2,3);
        MathResult res2 = new MathResult(1,2,3);

        assertEquals(res1.hashCode(), res2.hashCode());
    }

    public void testToString(){
        MathResult res = new MathResult(1,2,3);

        assertEquals("x: 1,000000 y: 2,000000 hash: 3", res.toString());

    }

    public MathResultTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(MathResultTest.class);
    }
}