package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.bean.Book;
import com.ssm.service.BookAdminService;
import com.ssm.service.BookService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 图书信息增删改功能
 * 增1-/toAdd
 * 增2-/book---post
 * 删-/book/page/pageNum---delete
 * 改-/book/page/pageNum---put
 * 改1-/
 * 改2/
 * ——————————————————————
 * *查询书籍功能调用 bookController 中的方法
 */

@Controller
public class BookAdminController {

    @Autowired
    private BookAdminService bookAdminService;

    @Autowired
    private BookService bookService;
    /**
     * 添加书籍一
     * @return 跳转到添加页面
     */
    @RequestMapping("/toAddBook")
    public String toAddBook(){
        return "book/book_add";
    }

    /**
     * 添加书籍二
     * @param book 添加的书籍
     * @return 添加成功返回书籍展示页的首页，失败返回添加页，即当前页
     */
    @RequestMapping(value = "/book/page/{pageNum}",method = RequestMethod.POST)
    public String addBook(Book book, @PathVariable String pageNum){
        int i = bookAdminService.insertBook(book);
        if(i==0){
            return "book/book_add";
        }
        return "redirect:/bookAdmin/page/1";
    }

    /**
     * 根据bookId删除书籍
     * @param bookId 图书号
     * @param pageNum 页码
     * @return 失败和成功均停留在当前页
     */
    @RequestMapping(value = "/book/page/{pageNum}",method = RequestMethod.DELETE)
    public String deleteBook(@Param("bookId") Integer bookId, @PathVariable("pageNum") String pageNum){
        bookAdminService.deleteBook(bookId);
        return "redirect:/bookAdmin/page/1";
    }

    /**
     * 根据传入的书籍修改库中书籍一
     * @return 跳转至修改界面(*若管理员的展示界面可以于当前页进行修改，则无需跳转*)
     */
    @RequestMapping("/toUpdateBook")
    public String toUpDate(){
        return "book/book_update";
    }

    /**
     * 根据传入的书籍修改库中书籍二
     * @param book 传入的书籍
     * @param pageNum 页码
     * @return 重新回到首页(考虑回到修改前的功能如何实现)
     */
    @RequestMapping(value = "/book/page/{pageNum}",method = RequestMethod.PUT)
    public String updateBook(Book book,@PathVariable("pageNum") String pageNum){
        bookAdminService.updateBook(book);
        return "redirect:/bookAdmin/page/1";
    }

    //查询

    /**
     * 分页查询书籍信息(图书展示功能)
     * @param model 查询功能的model，用于存储查询获取的book page信息
     * @param pageNum 页码
     * @return 返回图书展示界面
     */
    @RequestMapping(value = "/bookAdmin/page/{pageNum}", method = RequestMethod.GET)
    public String getAllBookByPage(Model model, @PathVariable("pageNum") Integer pageNum) {
        //获取分页信息
        PageInfo<Book> page = bookService.getBookPage(pageNum);
        //将分页数据共享到请求域中
        model.addAttribute("page", page);
        return "book/bookAdmin_list";
    }

    /**
     * 根据条件检索图书系统(模糊查询 like)，并将结果分页展示
     * @param pageNum 当前页码
     * @param keyword 输入的关键字
     * @param model 存储模糊查询的结果，存入共享域对象中
     * @return 返回查询后的图书展示界面
     */
    @RequestMapping(value = "/bookAdminLike/page/{pageNum}",method = RequestMethod.GET)
    public String getBookListByLike(@Param("keyword") String keyword,
                                    @PathVariable Integer pageNum, Model model) {
        PageInfo<Book> bookListByLike = bookService.getBookListByLike(pageNum, keyword);
        model.addAttribute("page", bookListByLike);
        return "redirect:/bookAdmin/page/1";
    }

    /**
     * 根据书的分类查询书籍
     * @param clazzName 分类
     * @param pageNum 页码
     * @param model 模型
     * @return 书籍显示
     */
    @RequestMapping("/getAdminBookListByClazzId/page/{pageNum}")
    public String getBookListByClazzId(@Param("clazzName") String clazzName,
                                       @PathVariable Integer pageNum, Model model){
        PageInfo<Book> page = bookService.getBookListByClazzId(pageNum, clazzName);
        model.addAttribute("page",page);
        return "redirect:/bookAdmin/page/1";
    }
}
