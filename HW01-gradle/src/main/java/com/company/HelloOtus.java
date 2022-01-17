package com.company;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 *
 * To start the application:
 * ./gradlew build
 * java -jar ./HW01-gradle/build/libs/gradleShadowJarArchive-0.1.jar
 *
 * To unzip the jar:
 * unzip -l HW01-gradle.jar
 * unzip -l gradleShadowJarArchive-0.1.jar
 *
 */
public class HelloOtus {
    public static void main(String[] args) {
        String key = "hello-key";
        Multimap<String, String> map = ArrayListMultimap.create();

        map.put(key, "firstValue");
        map.put(key, "secondValue");

        System.out.println(map);
    }
}
