package ru.otus;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;


public class MyAop {


    static Calculator creatMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new DemoCalculatorImpl());

        return (Calculator) Proxy.newProxyInstance(MyAop.class.getClassLoader(),
                new Class<?>[]{Calculator.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {

        private final Calculator myClass;
        private Set<String> setMethods;


        DemoInvocationHandler(final Calculator myClass) {
            this.myClass = myClass;
            Class<?> clazz = myClass.getClass();
            setMethods = new HashSet<>();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                Annotation[] annotations = method.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof log) {
                        setMethods.add(method.getName());
                    }
                }
            }
        }

        public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {

            if (setMethods.contains(method.getName())) {
                System.out.println("executed method: " + method.getName() + ", param:" + method.getParameterCount());
            }

            return method.invoke(myClass, args);
        }

    }
}
