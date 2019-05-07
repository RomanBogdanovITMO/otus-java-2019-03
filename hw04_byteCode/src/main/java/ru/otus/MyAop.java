package ru.otus;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;


public class MyAop {

    static MyinterfacClass creatMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new Demo());

        return (MyinterfacClass) Proxy.newProxyInstance(MyAop.class.getClassLoader(),
                new Class<?>[]{MyinterfacClass.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {

        private final MyinterfacClass myClass;
        private int parametMethod;
        private String nameMethod;


        DemoInvocationHandler(MyinterfacClass myClass) {
            this.myClass = myClass;

            Class<?> clazz = myClass.getClass();
            Method[] methods = clazz.getMethods();
            Parameter[] parameters = null;
            for (Method method1 : methods) {

                Annotation[] annotations = method1.getDeclaredAnnotations();

                for (Annotation annotation : annotations) {
                    if (annotation instanceof log) {

                        parameters = method1.getParameters();

                        for (Parameter parameter : parameters) {

                            nameMethod = method1.getName();
                            parametMethod = parameter.getModifiers();

                        }
                    }
                }
            }


        }


        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            System.out.println("executed method: " + nameMethod + ", param:" + parametMethod);
            System.out.println("____________");

            return method.invoke(myClass, args);
        }

    }
}
