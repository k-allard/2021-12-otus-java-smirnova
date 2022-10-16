package ru.proxy;


public class TestLogging implements TestLoggingInterface {

    @Override
    public void calculation(int param) {
        System.out.println("Actual calculation happened");
    }

    @Override
    public void calculation(int param, int param2) {
        System.out.println("Actual calculation happened");
    }

    @Override
    public void calculation(int param1, int param2, String param3) {
        System.out.println("Actual calculation happened");
    }

    @Override
    public String toString() {
        return "MyClassImpl{}";
    }
}
