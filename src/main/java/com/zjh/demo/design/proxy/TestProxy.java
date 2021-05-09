package com.zjh.demo.design.proxy;

/**
 * @author zhangjiahao
 * @create 2021/04/04 15:48
 */
public class TestProxy {

    public static void main(String[] args) {
        DynamicProxyClass dynamicProxyClass = new DynamicProxyClass();
        IUserController proxy = (IUserController)dynamicProxyClass.createProxy(new UserController());
        System.out.println(proxy.getUserName());
        System.out.println(proxy.getUserPhone());
    }
}
