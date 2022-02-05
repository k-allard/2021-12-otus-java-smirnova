package com.company;

import com.company.annotations.After;
import com.company.annotations.Before;
import com.company.annotations.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * To start the application:
 * ./gradlew build
 * java -jar ./HW01-gradle/build/libs/gradleShadowJarArchive-0.1.jar
 *
 */
public class MyJUnit {

    public static void launch(Class<?> testClass) {
//        boolean beforeMethodPerformed = false;
        int failedTests = 0;
        int successTests = 0;

        Method[] methods = testClass.getMethods();
        Map<Boolean, List<Method>> testMethodsMap =
                Arrays.stream(methods).collect(
                        Collectors.partitioningBy(
                                method -> method.getClass().isAnnotationPresent(Test.class)
//                                        && Modifier.isPublic(method.getModifiers())
                        )
                );

        List<Method> notTestMethods = testMethodsMap.get(false);
        List<Method> beforeMethods = notTestMethods.stream()
                .filter(method -> method.getClass().isAnnotationPresent(Before.class)
                        && Modifier.isPublic(method.getModifiers())
                )
                .collect(Collectors.toList());

        List<Method> afterMethods = notTestMethods.stream()
                .filter(method -> method.getClass().isAnnotationPresent(After.class)
                        && Modifier.isPublic(method.getModifiers())
                )
                .collect(Collectors.toList());

        for (Method beforeMethod : beforeMethods) {
            performBeforeOrAfterMethod(beforeMethod, testClass);
        }

        for (Method testMethod : testMethodsMap.get(true)) {
            try {
                performTestMethod(testMethod, testClass);
                successTests++;
            } catch (InvocationTargetException e) {
                System.err.println(e.getCause().getMessage());
                failedTests++;
            }
        }

        for (Method afterMethod : afterMethods) {
            performBeforeOrAfterMethod(afterMethod, testClass);
        }




//        Map<Boolean, List<Method>> testMethodsMap =
//                Arrays.stream(methods).collect(Collectors.partitioningBy(method -> method.getClass().isAnnotationPresent(Test.class)));
//
//        Map<Boolean, List<Method>> afterMethodsMap =
//                Arrays.stream(methods).collect(Collectors.partitioningBy(method -> method.getClass().isAnnotationPresent(After.class)));
//
//        for (Method method : methods) {
//            if (!beforeMethodPerformed && method.isAnnotationPresent(Before.class)) {
//                performBeforeOrAfterMethod(method, testClass);
//                beforeMethodPerformed = true;
//                continue;
//            }
//            if (method.isAnnotationPresent(Test.class)) {
//                try {
//                    performTestMethod(method, testClass);
//                    successTests++;
//                } catch (InvocationTargetException e) {
//                    System.err.println(e.getCause().getMessage());
//                    failedTests++;
//                }
//                continue;
//            }
//            if (method.isAnnotationPresent(After.class)) {
//                performBeforeOrAfterMethod(method, testClass);
//            }
//        }
        System.out.println("_________________________");
        System.out.println("Success tests number: [" + successTests + "]");
        System.out.println("Failed tests number: [" + failedTests + "]");
    }

    private static void performTestMethod(Method method, Class<?> testClass) throws InvocationTargetException {
        try {
            method.invoke(testClass.getDeclaredConstructor().newInstance());
        } catch (IllegalAccessException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private static void performBeforeOrAfterMethod(Method method, Class<?> testClass)  {
        try {
            method.invoke(testClass.getDeclaredConstructor().newInstance());
        } catch (IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
