package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.bean.Book;
import com.ssm.bean.BookExample;
import com.ssm.bean.ClazzExample;
import com.ssm.mapper.BookMapper;
import com.ssm.mapper.ClazzMapper;
import com.ssm.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private ClazzMapper clazzMapper;

    /**
     * 根据bookId查询当前书籍的库存数量
     * @param bookId 图书号
     * @return true表示有剩余，false表示为数量为0
     */
    @Override
    public Integer getBookNum(Long bookId) {
        BookExample bookExample = new BookExample();
        bookExample.createCriteria().andBookIdEqualTo(bookId);
        Integer number = bookMapper.selectByExample(bookExample).get(0).getNumber();
        return number;
    }

    /**
     * 根据bookId借出图书
     * @param bookId 图书号
     * @return 0表示借书失败，1表示借书成功
     */
    @Override
    public Integer lendOut(Long bookId) {
        Integer bookNum = getBookNum(bookId);
        //如果数量为0
        if (bookNum==0){
            //throw new RuntimeException("该书已被借光，请过段时间再来借书");
            return 0;
        }
        //如果不为空，即可以借出，使得借出数量减一
        //注：是否需要将借出日期修改为当前日期，且置空归还日期
        //修改后多本书籍信息如何储存，借出日期和归还日期在读者系统仍需页面展示
        BookExample bookExample = new BookExample();
        Book book = getOneBookByBookId(bookId);
        book.setNumber(bookNum+1);
        bookExample.createCriteria().andBookIdEqualTo(bookId);
        //修改，并返回修改的行数
        int i = bookMapper.updateByExampleSelective(book, bookExample);
        return i;
    }

    /**
     * 根据bookId归还图书
     * @param bookId 图书号
     * @return 返回影响的行数，0表示归还失败，1表示归还成功
     */
    @Override
    public Integer returnIn(Long bookId) {
        BookExample bookExample = new BookExample();
        Book book = getOneBookByBookId(bookId);
        bookExample.createCriteria().andBookIdEqualTo(bookId);
        book.setNumber(book.getNumber() + 1);
        int i = bookMapper.updateByExampleSelective(book, bookExample);
        return i;
    }

    /**
     * 根据bookId查看具体的一本书的信息
     * @param bookId 图书号
     * @return 具体的书本信息
     */
    @Override
    public Book getOneBookByBookId(Long bookId) {
        BookExample bookExample = new BookExample();
        bookExample.createCriteria().andBookIdEqualTo(bookId);
        Book book = bookMapper.selectByExample(bookExample).get(0);
        return book;
    }

    /**
     * 图书信息的展示(分页)
     * @param pageNum 当前页码数
     * @return 返回分页展示的图书信息结果
     */
    @Override
    public PageInfo<Book> getBookPage(Integer pageNum) {
        //开启分页功能
        PageHelper.startPage(pageNum, 3);

        List<Book> list = bookMapper.selectByExample(null);
        //获取分页相关数据
        PageInfo<Book> pageInfo = new PageInfo<>(list, 5);
        return pageInfo;
    }

    /**
     * 根据条件检索图书系统(模糊查询 like)，并将结果分页展示
     * @param pageNum 当前页码
     * @param keyword 输入的关键字
     * @return 分页展示的图书数据
     */
    @Override
    public PageInfo<Book> getBookListByLike(Integer pageNum, String keyword) {
        BookExample bookExample = new BookExample();
        PageHelper.startPage(pageNum, 4);
        bookExample.createCriteria().andNameLike("%"+keyword+"%");
        List<Book> list = bookMapper.selectByExample(bookExample);
        PageInfo<Book> pageInfo = new PageInfo<>(list, 5);
        return pageInfo;
    }

    /**
     * 根据条件检索图书系统(clazzName)
     * 先获取clazzId
     * @param pageNum 当前页码
     * @param clazzName 分类名
     * @return 分页展示的图书数据
     */
    @Override
    public PageInfo<Book> getBookListByClazzId(Integer pageNum, String clazzName) {
        BookExample bookExample = new BookExample();
        ClazzExample clazzExample = new ClazzExample();
        PageHelper.startPage(pageNum, 4);
        //先通过clazzName获取对应的clazzId，默认每个clazzId对应一个唯一的一个clazzName
        clazzExample.createCriteria().andClazzNameEqualTo(clazzName);
        Integer clazzId = clazzMapper.selectByExample(clazzExample).get(0).getClazzId();
        //通过获取到的clazzId查询符合条件的书籍
        bookExample.createCriteria().andClazzIdEqualTo(clazzId);
        List<Book> list = bookMapper.selectByExample(bookExample);
        PageInfo<Book> pageInfo = new PageInfo<>(list, 5);
        return pageInfo;
    }
}
