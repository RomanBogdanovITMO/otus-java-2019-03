package ru.otus;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;


public class MyAop {

    static MyinterfacClass creatMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new Demo());

        return (MyinterfacClass) Proxy.newProxyInstance(MyAop.class.getClassLoader(),
                new Class<?>[]{MyinterfacClass.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {

        private final MyinterfacClass myClass;
        private Set<Method> setMethods;


        DemoInvocationHandler(MyinterfacClass myClass) {
            this.myClass = myClass;

            Class<?> clazz = myClass.getClass();
            setMethods = new HashSet<>();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                Annotation[] annotations = method.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof log) {
                        setMethods.add(method);
                    }
                }
            }
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            for (Method method1 : setMethods) {
                if (method1.getName().equals(method.getName())) {
                    System.out.println("executed method: " + method1.getName() + ", param:" + method1.getParameterCount());
                    System.out.println("____________");
                }
            }
            return method.invoke(myClass, args);
        }

    }
}
