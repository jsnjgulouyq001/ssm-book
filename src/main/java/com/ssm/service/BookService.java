package com.ssm.service;


import com.github.pagehelper.PageInfo;
import com.ssm.bean.Book;

import java.util.List;

/**
 * 图书借阅系统
 * 功能包括：
 * 借出(多线程；书不可被重复借出，即每本书正在被借出时无法被其他读者借出)
 * 归还(根据读者，书籍，匹配到一个存在借出日期但尚未归还的借阅记录，并记录当前的归还时间)(日志)
 * 图书信息的展示(分页展示)
 * 根据条件检索图书系统(例如 模糊查询 like)
 * 根据条件检索图书系统(例如分类clazzName)
 * 注：修改书籍信息需要管理员权限，放于BookAdminService服务中
 */
public interface BookService {

    /**
     * 根据bookId判断目前书籍数量是否为空
     * @param bookId 图书号
     * @return true表示有剩余，false表示为数量为0
     */
    Integer getBookNum(Long bookId);

    /**
     * 根据bookId借出图书
     * @param bookId 图书号
     * @return 0表示借书失败，1表示借书成功
     */
    Integer lendOut(Long bookId);

    /**
     * 根据bookId归还图书
     * @param bookId 图书号
     * @return 返回影响的行数，0表示归还失败，1表示归还成功
     */
    Integer returnIn(Long bookId);

    /**
     * 根据bookId查看具体的一本书的信息
     * @param bookId 图书号
     * @return 具体的书本信息
     */
    Book getOneBookByBookId(Long bookId);

    /**
     * 图书信息的展示(分页)
     * @param pageNum 当前页码数
     * @return 返回分页展示的图书信息结果
     */
    PageInfo<Book> getBookPage(Integer pageNum);

    /**
     * 根据条件检索图书系统(模糊查询 like)，并将结果分页展示
     * @param pageNum 当前页码
     * @param keyword 输入的关键字
     * @return 分页展示的图书数据
     */
    PageInfo<Book> getBookListByLike(Integer pageNum,String keyword);

    /**
     * 根据条件检索图书系统(clazzName)
     * 先获取clazzId
     * @param pageNum 当前页码
     * @param clazzName 分类名
     * @return 分页展示的图书数据
     */
    PageInfo<Book> getBookListByClazzId(Integer pageNum,String clazzName);

}
