package com.company;

import com.company.annotations.After;
import com.company.annotations.Before;
import com.company.annotations.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

/**
 *
 * To start the application:
 * ./gradlew build
 * java -jar ./HW01-gradle/build/libs/gradleShadowJarArchive-0.1.jar
 *
 */
public class MyJUnit {

    public static void launch(Class<?> testClass) {
        int failedTests = 0;
        int successTests = 0;

        List<Method> allPublicMethods = Arrays.stream(testClass.getMethods())
                .filter(method -> Modifier.isPublic(method.getModifiers())).toList();

        List<Method> testMethods = allPublicMethods.stream()
                .filter(method -> method.isAnnotationPresent(Test.class)).toList();

        List<Method> beforeMethods = allPublicMethods.stream()
                .filter(method -> method.isAnnotationPresent(Before.class)).toList();

        List<Method> afterMethods = allPublicMethods.stream()
                .filter(method -> method.isAnnotationPresent(After.class)).toList();

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


        System.out.println("_________________________");
        System.out.println("Success tests number: [" + successTests + "]");
        System.out.println("Failed tests number: [" + failedTests + "]");
    }

    private static void performTestMethod(Method method, Object testClass) throws InvocationTargetException {
        try {
            method.invoke(testClass);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void performBeforeOrAfterMethod(Method method, Object testClass)  {
        try {
            method.invoke(testClass);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
