package com.ssm.controller;

import com.ssm.bean.Card;
import com.ssm.service.ReaderCardRegisterService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 读者功能控制系统
 */
@Controller
public class ReaderCardRegisterController {

    @Autowired
    public ReaderCardRegisterService readerCardRegisterService;

    /**
     * 登录读者系统
     * @param username 用户名
     * @param password 密码
     * @param model model用于存放用户登录信息
     * @return 正确跳转至读者功能首页，错误跳转回首页(*错误应该保持在当前页，但页面中提示消息用户名或密码错误*)
     */
    @RequestMapping(value = "/loginReader", method = RequestMethod.POST)
    public String loginReader(@Param("username") String username, @Param("password") String password, Model model) {
        Card card = readerCardRegisterService.loginReaderByReadIdAndPassword(username, password);
        if (card != null) {
            model.addAttribute("card", card);
            return "reader/reader_index";
        }
        return "index";
    }

    /**
     * 更改读者信息一
     * @return 跳转至修改界面
     */
    @RequestMapping(value = "/UpdateReaderByUsernameOne")
    public String UpdateReaderByUsernameOne() {
        return "reader/reader_update";
    }

    /**
     * 更改读者信息二(即修改用户名和密码)
     * @param username 用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 重定向至读者功能首页
     */
    @RequestMapping(value = "/updateReaderByUsernameTwo", method = RequestMethod.POST)
    public String updateReaderByUsernameTwo( Model model, @Param("username") String username,
                                            @Param("oldPassword") String oldPassword, @Param("newPassword") String newPassword) {
        Card card = readerCardRegisterService.updatePasswordByUsername(username, oldPassword, newPassword);
        if (card != null) {
            System.out.println("修改成功");
            model.addAttribute("card", card);
            return "success";
        }
        return "false";
    }

    /**
     * 读者退出登陆(HttpServletRequest请求域中去除管理员的信息)
     * @param request 请求域中的数据
     * @return 回到首页
     */
    @RequestMapping("/readerLogOut")
    public String AdminLogOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "index";
    }

}
