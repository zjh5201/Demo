package com.zjh.demo.design.proxy;

/**
 * @author zhangjiahao
 * @create 2021/04/04 15:30
 */
public class UserController implements IUserController {


    @Override
    public String getUserName() {
        return "张家豪";
    }

    @Override
    public String getUserPhone() {
        return "110";
    }
}
