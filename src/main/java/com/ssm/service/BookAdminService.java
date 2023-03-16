package com.ssm.service;

import com.ssm.bean.Book;

/**
 * 管理员图书系统
 * 功能包括：
 * 对图书的信息的增删该(注：查询功能在bookService中已实现，考虑是否需要实现)
 * 修改图书的分类
 */
public interface BookAdminService {

    /**
     * 增加书籍
     * @param book 待增加的书籍
     * @return 返回影响的行数，0表示添加失败，1表示添加成功
     */
    int insertBook(Book book);

    /**
     * 根据bookId删除书籍
     * @param bookId 图书号
     * @return 返回影响的行数，0表示删除失败，1表示删除成功
     */
    int deleteBook(long bookId);

    /**
     * 修改书籍
     * @param book 书籍信息
     * @return 返回影响的行数，0表示修改失败，1表示修改成功
     */
    int updateBook(Book book);

}
