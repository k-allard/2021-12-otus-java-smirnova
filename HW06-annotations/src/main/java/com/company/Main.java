package com.company;

/**
 *
 * To start the application:
 * ./gradlew build
 * java -jar ./HW06-annotations/build/libs/gradleShadowJarArchive-0.1.jar
 *
 */
public class Main {
    public static void main(String[] args) {
        MyJUnit.launch(SecondTestClass.class);
    }
}
