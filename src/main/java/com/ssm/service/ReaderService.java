package com.ssm.service;

import com.ssm.bean.Reader;

/**
 * 读者服务系统
 * 功能包括：
 * ---调用BookService方法
 * 借阅 图书
 * 归还 图书(*)
 * 查看全部图书信息
 * 根据条件检索图书信息
 * ---调用ReaderService方法//
 * 查看个人信息(即读者证号)
 * 修改个人信息(即读者证号)
 * ---调用ReaderCardRegisterService方法
 * 登录、注册读者系统
 * ---调用LendService方法
 * 查看借阅情况(根据读者的id或者username查看)
 */
public interface ReaderService {

    /**
     * 根据readerId查看个人信息(即查看读者证信息)
     * @param readerId 读者证号
     * @return 返回读者证上的读者信息
     */
    Reader getReaderById(Long readerId);

    /**
     * 修改个人信息
     * @param reader 读者
     * @return 修改个人信息影响的行数
     */
    Integer updateReader(Reader reader);

}
