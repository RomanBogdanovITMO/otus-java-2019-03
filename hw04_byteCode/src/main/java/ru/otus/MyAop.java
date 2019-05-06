package ru.otus;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.logging.Logger;


public class MyAop {

    static MyinterfacClass creatMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new Demo());

        return (MyinterfacClass) Proxy.newProxyInstance(MyAop.class.getClassLoader(),
                new Class<?>[]{MyinterfacClass.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {


        private final MyinterfacClass myClass;

       DemoInvocationHandler(MyinterfacClass myClass) {
            this.myClass = myClass;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Class<?>clazz = method.getDeclaringClass();
            Method[] methods = clazz.getMethods();
            Parameter[] parameters = null;
            for (Method method1 : methods) {
                Annotation[] annotations = method1.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof log) {
                        parameters = method1.getParameters();
                        for (Parameter parameter : parameters) {
                            String nameMethod = method1.getName();
                            int parametMethod = parameter.getModifiers();
                            System.out.println("executed method: " + nameMethod + ", param:" + parametMethod);
                            System.out.println("____________");
                        }
                    }
                }
            }
            return method.invoke(myClass, args);
        }

    }
}
