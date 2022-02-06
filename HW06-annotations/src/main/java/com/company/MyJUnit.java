package com.company;

import com.company.annotations.After;
import com.company.annotations.Before;
import com.company.annotations.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public class MyJUnit {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void launch(Class<?> testClass) {

        List<Method> allPublicMethods = Arrays.stream(testClass.getMethods()).filter(
                method -> Modifier.isPublic(method.getModifiers())
                        && method.getParameterCount() == 0)
                .toList();

        List<Method> testMethods = allPublicMethods.stream()
                .filter(method -> method.isAnnotationPresent(Test.class)).toList();

        List<Method> beforeMethods = allPublicMethods.stream()
                .filter(method -> method.isAnnotationPresent(Before.class)).toList();

        List<Method> afterMethods = allPublicMethods.stream()
                .filter(method -> method.isAnnotationPresent(After.class)).toList();

        int failedTests = 0;
        int successTests = 0;
        printIntro();

        try {
            Object testClassInstance = testClass.getDeclaredConstructor().newInstance();
            for (Method beforeMethod : beforeMethods) {
                performBeforeOrAfterMethod(beforeMethod, testClassInstance);
            }
            for (Method testMethod : testMethods) {
                try {
                    performTestMethod(testMethod, testClassInstance);
                    successTests++;
                } catch (InvocationTargetException e) {
                    System.out.println(e.getCause().getMessage());
                    failedTests++;
                }
            }
            for (Method afterMethod : afterMethods) {
                performBeforeOrAfterMethod(afterMethod, testClassInstance);
            }
        } catch (InstantiationException | IllegalAccessException |
                InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        printStatistics(successTests, failedTests);
    }

    private static void performTestMethod(Method method, Object testClass) throws InvocationTargetException {
        try {
            method.invoke(testClass);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void performBeforeOrAfterMethod(Method method, Object testClass) {
        try {
            method.invoke(testClass);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void printIntro() {
        System.out.println("  __  __           _ _   _       _ _   ");
        System.out.println(" |  \\/  |_   _    | | | | |_ __ (_| |_ ");
        System.out.println(" | |\\/| | | | |_  | | | | | '_ \\| | __|");
        System.out.println(" | |  | | |_| | |_| | |_| | | | | | |_ ");
        System.out.println(" |_|  |_|\\__, |\\___/ \\___/|_| |_|_|\\__|");
        System.out.println("         |___/ by kallard");
        System.out.println("_______________________________________");
    }

    private static void printStatistics(int successTests, int failedTests) {
        System.out.println("_______________________________________");
        System.out.println(ANSI_GREEN + "Success tests number: [" + successTests + "]" + ANSI_RESET);
        System.out.println(ANSI_RED + "Failed tests number:  [" + failedTests + "]" + ANSI_RESET);
    }
}
