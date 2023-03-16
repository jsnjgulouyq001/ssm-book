package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.bean.Book;
import com.ssm.service.BookService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 图书馆控制系统：
 * <p>
 * 增-跳转/toAdd/get
 * 增-/book/post
 * 删-/book/delete
 * 改-/book/put
 * <p>
 * 读者系统仅包括查询
 * 查-/book/get
 * 查询某一本
 * <p>
 * *借出、归还
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 在当前页面显示的图书列表中借书
     * @param pageNum 当前页码
     * @param bookId  图书号
     * @return 跳转至借阅成功或借阅失败的页面
     */
    @ResponseBody
    @RequestMapping(value = "/lendBook/page/{pageNum}", method = RequestMethod.POST)
    public String lendBook(@PathVariable("pageNum") Integer pageNum, @Param("bookId") Long bookId) {
        Integer result = bookService.lendOut(bookId);
        if (result == 0) {
            //如果借书失败，返回失败页面，或者提示已全部借出
            //ResponseBody
            return "借阅失败，请刷新重试";
        }
        return "借阅成功！";
    }

    /**
     * 在当前页面显示的图书列表中还书
     * 还书的功能应该再读者的借阅书籍的展示中点击归还
     * (考虑方便可以再当前界面点击还书)
     */
    @RequestMapping("/returnBook/page/{pageNum}")
    public String returnBook(@PathVariable("pageNum") Integer pageNum, @Param("bookId") Long bookId) {
        bookService.returnIn(bookId);
        return "redirect:/book/bookReader_list";
    }

    /**
     * 根据bookId查看具体的一本书的信息
     * @param bookId 图书号
     * @param model  存储返回的book书籍
     * @return 具体的书本信息
     */
//    @RequestMapping(value = "/getOneBookByBookId", method = RequestMethod.GET)


    /**
     * 分页查询书籍信息(图书展示功能)
     * @param model 查询功能的model，用于存储查询获取的book page信息
     * @param pageNum 页码
     * @return 返回图书展示界面
     */
    @RequestMapping(value = "/bookReader/page/{pageNum}", method = RequestMethod.GET)
    public String getAllBookByPage(Model model, @PathVariable("pageNum") Integer pageNum) {
        //获取分页信息
        PageInfo<Book> page = bookService.getBookPage(pageNum);
        //将分页数据共享到请求域中
        model.addAttribute("page", page);
        return "book/bookReader_list";
    }

    /**
     * 根据条件检索图书系统(模糊查询 like)，并将结果分页展示
     * @param pageNum 当前页码
     * @param search 输入搜索的关键字
     * @param model 存储模糊查询的结果，存入共享域对象中
     * @return 返回查询后的图书展示界面
     */
    @RequestMapping(value = "/bookReaderLike/page/{pageNum}",method = RequestMethod.GET)
    public String getBookListByLike(@Param("search") String search,
                                    @PathVariable("pageNum") Integer pageNum, Model model) {
        PageInfo<Book> page = bookService.getBookListByLike(pageNum, search);
        model.addAttribute("page", page);
        return "book/bookReaderLike_list";
    }

    /**
     * 根据书的分类查询书籍
     * @param clazzName 分类
     * @param pageNum 页码
     * @param model 模型
     * @return 书籍显示
     */
    @RequestMapping("/getReaderBookListByClazzId/page/{pageNum}")
    public String getBookListByClazzId(@Param("clazzName") String clazzName,
                                       @PathVariable Integer pageNum, Model model){
        PageInfo<Book> page = bookService.getBookListByClazzId(pageNum, clazzName);
        model.addAttribute("page",page);
        return "book/bookReader_list";
    }
}
