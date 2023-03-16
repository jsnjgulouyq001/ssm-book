package com.ssm.service;

import com.ssm.bean.Lend;

import java.util.List;

/**
 * 查看借阅情况(流水号，书名，读者名，借出日期，归还日期)
 */
public interface LendService {

    /**
     * 根据bookId查询某本书的借阅情况
     * @param bookId 图书号
     * @return 书本的借阅信息
     */
    List<Lend> getLendByBookId(Long bookId);

    /**
     * 根据readId查询某本书的借阅情况
     * @param readId 读者名
     * @return 读者的借阅信息
     */
    List<Lend> getLendByReaderId(Long readId);

    /**
     * 判断是否已归还(根据lend表中的流水号serialNum)
     * @param serialNum lend数据库中的流水号
     * @return true表示以归还
     */
    boolean isBack(Long serialNum);
}
