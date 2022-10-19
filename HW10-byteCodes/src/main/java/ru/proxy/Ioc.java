package ru.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Ioc {

    private Ioc() {
    }

    static TestLoggingInterface createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLogging());
        return (TestLoggingInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface myClass;
        private final List<Method> annotatedMethods;

        DemoInvocationHandler(TestLoggingInterface myClass) {
            this.myClass = myClass;
            annotatedMethods = getMethodsAnnotatedWithLog();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (annotatedMethods.contains(method)) {
                System.out.println("invoking method: [" + method + "]");
                System.out.println("params: " + Arrays.toString(args));
            }
            return method.invoke(myClass, args);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "myClass=" + myClass +
                    '}';
        }

        private List<Method> getMethodsAnnotatedWithLog() {
            final List<Method> methods = new ArrayList<>();
            Class<?>[] interfaces = myClass.getClass().getInterfaces();

            for (final Class<?> clazz : interfaces ){
                for (final Method method : clazz.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(Log.class)) {
                        methods.add(method);
                    }
                }
            }
            return methods;
        }
    }
}
