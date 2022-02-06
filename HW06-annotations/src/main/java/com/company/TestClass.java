package com.company;

import com.company.annotations.After;
import com.company.annotations.Before;
import com.company.annotations.Test;

public class TestClass {
    @Before
    public void init() {
        System.out.println("@Before init() method called");
    }

    @Test
    public void successTest1() {
        System.out.println("Done successTest1()");
    }

    @Test
    public void failureTest() {
        throw new RuntimeException("Exception from failureTest()");
    }

    @Test
    public void successTest2() {
        System.out.println("Done successTest2()");
    }

    @After
    public void cleanUp() {
        System.out.println("@After cleanUp() method called");
    }
}
