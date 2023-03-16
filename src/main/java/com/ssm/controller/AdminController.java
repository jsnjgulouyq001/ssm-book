package com.ssm.controller;

import com.ssm.bean.Admin;
import com.ssm.service.AdminService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 管理员功能控制系统
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 登录管理员系统
     * @param username 用户名
     * @param password 密码
     * @param model model用于存放用户登录信息
     * @return 正确跳转至管理员功能首页，错误跳转回首页(*错误应该保持在当前页，但页面中提示消息用户名或密码错误*)
     */
    @RequestMapping(value = "/loginAdmin",method = RequestMethod.POST)
    public String loginAdmin(@Param("username") String username,@Param("password")String password, Model model) {
        Admin adminResult = adminService.loginAdmin(username,password);
        if (adminResult!=null) {
            model.addAttribute("admin", adminResult);
            return "admin/admin_index";
        }
        return "index";
    }

    /**
     * 更改管理员信息一
     * @return 跳转至修改界面
     */
    @RequestMapping(value = "/updateAdminOne")
    public String toUpdatePasswordByUsername(){
        return "admin/admin_update";
    }

    /**
     * 更改管理员信息二
     * @param username 用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 重定向至管理员功能首页
     */
    @RequestMapping("/updateAdminTwo")
    public String updatePasswordByUsername(@Param("username") String username,
        @Param("oldPassword") String oldPassword,@Param("newPassword") String newPassword){
        boolean result = adminService.updatePasswordByUsername(username, oldPassword, newPassword);
        if(result){
            return "redirect:/admin/admin_index";
        }
        return "admin/updateAdmin";
    }

    /**
     * 管理员退出登陆(HttpServletRequest请求域中去除管理员的信息)
     * @param request 请求域中的数据
     * @return 回到首页
     */
    @RequestMapping("/adminLogOut")
    public String AdminLogOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "index";
    }

}
