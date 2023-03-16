package com.ssm.service;


import com.ssm.bean.Card;

/**
 * 读者登录服务
 * 功能包括：
 * 登录
 * 修改密码
 */
public interface ReaderCardRegisterService {

    /**
     * 登录读者系统
     * @param username 用户名
     * @param password 密码
     * @return true表示登录成功
     */
     Card loginReaderByReadIdAndPassword(String username, String password);

    /**
     * 修改密码
     * @param username    用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return true表示密码修改成功
     */
    Card updatePasswordByUsername(String username, String oldPassword, String newPassword);

}
