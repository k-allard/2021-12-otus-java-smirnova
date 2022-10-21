package ru.proxy;

public class ProxyDemo {
    public static void main(String[] args) {
        TestLoggingInterface myClass = Ioc.createMyClass();
        myClass.calculation(5);
        myClass.calculation(4, 8);
        myClass.calculation(1, 7);
        myClass.calculation(1, 5, "Note");
    }
}
