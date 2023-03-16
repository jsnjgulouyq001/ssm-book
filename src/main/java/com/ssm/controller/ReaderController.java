package com.ssm.controller;

import com.ssm.bean.Reader;
import com.ssm.service.ReaderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 读者控制系统
 */
@Controller
public class ReaderController {

    @Autowired
    private ReaderService readerService;

    /**
     * 根据readerId查看个人信息(即查看读者证信息)
     * @param readerId 读者证号
     * @param model 将读者信息添加到model中
     * @return 跳转到reader界面(即读者证界面)
     */
    @RequestMapping(value = "/getReaderById",method = RequestMethod.GET)
    public String getReaderById(@Param("readerId") Long readerId, Model model){
        Reader reader = readerService.getReaderById(readerId);
        model.addAttribute("reader", reader);
        return "reader/reader";
    }

    /**
     * 更改读者证信息一
     * @return 跳转至读者证修改页面
     */
    @RequestMapping("/updateReaderOne")
    public String updateReaderOne(){
        return "reader/reader_update";
    }

    /**
     * 更改读者证信息二
     * @param reader
     * @return
     */
    @RequestMapping("/updateReaderTwo")
    public String updateReaderTwo(Reader reader){
        Integer result = readerService.updateReader(reader);
        /*控制台输出功能，仅作演示*/
        System.out.println(result);
        return "redirect:/reader/reader";
    }
}
