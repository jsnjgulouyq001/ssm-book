package com.ssm.service.impl;

import com.ssm.bean.Book;
import com.ssm.bean.BookExample;
import com.ssm.bean.Lend;
import com.ssm.mapper.BookMapper;
import com.ssm.service.BookAdminService;
import com.ssm.service.LendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookAdminServiceImpl implements BookAdminService {

    @Autowired
    private BookMapper bookMapper;

    /**
     * 增加书籍
     * @param book 待增加的书籍
     * @return 返回影响的行数，0表示添加失败，1表示添加成功
     */
    @Override
    public int insertBook(Book book) {
        return bookMapper.insert(book);
    }

    /**
     * 根据bookId删除书籍
     * @param bookId 图书号
     * @return 返回影响的行数，0表示删除失败，1表示删除成功
     */
    @Override
    public int deleteBook(long bookId) {
        BookExample bookExample = new BookExample();
        bookExample.createCriteria().andBookIdEqualTo(bookId);
        return bookMapper.deleteByExample(bookExample);

    }

    /**
     * 修改书籍
     * @param book 书籍信息
     * @return 返回影响的行数，0表示修改失败，1表示修改成功
     */
    @Override
    public int updateBook(Book book) {
        return bookMapper.updateByExample(book, null);
    }

    /**
     * 上面为增删改
     * 下面调用读者系统的查看功能
     * 考虑在查找界面增加增删改的功能
     * 管理员可以调用，但是页面显示需要更多信息
     */

//    private LendService lendService;
//
//    List<Lend> getLendByBookIdAdmin(Long bookId) {
//        return lendService.getLendByBookId(bookId);
//    }
//
//    List<Lend> getLendByReaderIdAdmin(Long readId) {
//        return lendService.getLendByReaderId(readId);
//    }
}
