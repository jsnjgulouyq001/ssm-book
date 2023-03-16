package com.ssm.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.bean.Admin;
import com.ssm.bean.AdminExample;
import com.ssm.bean.Reader;
import com.ssm.mapper.AdminMapper;
import com.ssm.mapper.ReaderMapper;
import com.ssm.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 图书管理员系统
 * 用于登录和修改密码
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 根据传入的用户的用户名和密码查找
     * @param username 用户名
     * @param password 密码
     * @return 成功则返回登录成功的账号，失败返回null
     */
    @Override
    public Admin loginAdmin(String username, String password) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andUsernameEqualTo(username)
                .andPasswordEqualTo(password);
        List<Admin> list = adminMapper.selectByExample(adminExample);
        if (list == null) {
            return null;
        }
        Admin admin = list.get(0);
        System.out.println(admin);
        return admin;
    }

    /**
     * 根据用户名修改密码
     * @param username 用户名
     * @param oldPassword 新密码
     * @param newPassword 旧密码
     * @return true表示密码修改成功，false表示密码修改失败
     */
    @Override
    public boolean updatePasswordByUsername(String username, String oldPassword, String newPassword) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(oldPassword);
        //查询库中用户名和密码相同的数据
        List<Admin> list = adminMapper.selectByExample(adminExample);
//看看判断条件        list.isEmpty();
        if(list==null){
            return false;
        }
        Admin admin = list.get(0);
        adminMapper.updateByExampleSelective(admin, adminExample);
        return true;
    }

    @Autowired
    private ReaderMapper readerMapper;

    /**
     * 展示所有读者信息，其中页面显示中包含对读者信息的修改功能
     * @param pageNum 当前页
     */
    @Override
    public void showAllReader(Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Reader> list = readerMapper.selectByExample(null);
        PageInfo<Reader> pageInfo = new PageInfo<>(list, 5);
    }

}
