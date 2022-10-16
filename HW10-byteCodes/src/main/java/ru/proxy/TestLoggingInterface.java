package ru.proxy;

public interface TestLoggingInterface {

    @Log
    void calculation(int param);

    void calculation(int param, int param2);

    @Log
    void calculation(int param1, int param2, String param3);
}
