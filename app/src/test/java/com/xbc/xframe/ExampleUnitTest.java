package com.xbc.xframe;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SSS");

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void add() throws Exception {
        int a = 4;
        int b = 5;
        System.out.print(sdf.format(new Date()) + "  ");
        System.out.println(a + b);
    }

    @Test
    public void sub() throws Exception {
        int a = 4;
        int b = 5;
        System.out.print(sdf.format(new Date()) + "  ");
        System.out.println(a - b);
    }

}