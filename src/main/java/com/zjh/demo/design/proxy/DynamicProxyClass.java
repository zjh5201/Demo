package com.zjh.demo.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理类，基于接口代理
 * 
 * @author zhangjiahao
 * @create 2021/04/04 15:28
 */
public class DynamicProxyClass {

    public Object createProxy(Object proxiedObject) {
        // 获取类的所有接口
        Class<?>[] interfaces = proxiedObject.getClass().getInterfaces();
        DynamicProxyHandler dynamicProxyHandler = new DynamicProxyHandler(proxiedObject);
        return Proxy.newProxyInstance(proxiedObject.getClass().getClassLoader(), interfaces, dynamicProxyHandler);

    }

    private class DynamicProxyHandler implements InvocationHandler {
        private Object proxiedObject;

        public DynamicProxyHandler(Object proxiedObject) {
            this.proxiedObject = proxiedObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("doBefore");
            String name = proxiedObject.getClass().getName() + ":" + method.getName();
            Object result = method.invoke(proxiedObject, args);
            System.out.println(name);
            System.out.println("deAfter");
            return result;
        }
    }

}
