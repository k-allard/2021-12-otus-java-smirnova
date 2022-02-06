package com.company;

import com.company.annotations.After;
import com.company.annotations.Before;
import com.company.annotations.Test;

public class SecondTestClass {
    @Before
    public void init() {
        System.out.println("@Before init() method called");
    }

    @Test
    public void failureTest1() {
        throw new RuntimeException("Exception from failureTest1()");
    }

    @Test
    public void failureTest2() {
        throw new RuntimeException("Exception from failureTest2()");
    }

    @Test
    public void successTest1() {
        System.out.println("Done successTest1()");
    }

    @After
    public void cleanUp() {
        System.out.println("@After cleanUp() method called");
    }

    @Test
    public void successTest2() {
        System.out.println("Done successTest2()");
    }

    @Test
    private void ignoredPrivateTest() {
        System.out.println("Done ignoredPrivateTest()");
    }

    @Test
    public void ignoredTestWithArgs(String string) {
        System.out.println("Done ignoredTestWithArgs()");
    }

    @Test
    public void failureTest3() {
        throw new RuntimeException("Exception from failureTest3()");
    }

    @Before
    public void init2() {
        System.out.println("@Before init2() method called");
    }
}

