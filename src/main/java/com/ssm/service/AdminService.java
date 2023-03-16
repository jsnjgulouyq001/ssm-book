package com.ssm.service;

import com.ssm.bean.Admin;

/**
 * 管理员系统功能：
 * -管理员自身系统
 * 登录
 * 修改密码
 * -对各种信息的增删改查，包括如下
 * 1、书籍 book
 * 2、读者 reader
 * 3、读书证 card
 * 4、借阅记录 lend
 * 5、书籍分类 clazz
 */
public interface AdminService {

    /**
     * 根据传入的用户的用户名和密码查找
     * @param username 用户名
     * @param password 密码
     * @return 成功则返回登录成功的账号，失败返回null
     */
    Admin loginAdmin(String username, String password);

    /**
     * 根据用户名修改密码
     * @param username 用户名
     * @param oldPassword 新密码
     * @param newPassword 旧密码
     * @return true表示密码修改成功，false表示密码修改失败
     */
    boolean updatePasswordByUsername(String username, String oldPassword, String newPassword);

    /**
     * 展示所有读者信息，其中页面显示中包含对读者信息的修改功能
     * @param pageNum 当前页
     */
    void showAllReader(Integer pageNum);
}
