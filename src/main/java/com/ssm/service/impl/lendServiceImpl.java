package com.ssm.service.impl;

import com.ssm.bean.Lend;
import com.ssm.bean.LendExample;
import com.ssm.mapper.LendMapper;
import com.ssm.service.LendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class lendServiceImpl implements LendService {

    @Autowired
    private LendMapper lendMapper;

    /**
     * 根据bookId查询某本书的借阅情况
     * @param bookId 图书号
     * @return 书本的借阅信息
     */
    @Override
    public List<Lend> getLendByBookId(Long bookId) {
        LendExample lendExample=new LendExample();
        lendExample.createCriteria().andBookIdEqualTo(bookId);
        List<Lend> list = lendMapper.selectByExample(lendExample);
        return list;
    }

    /**
     * 根据readId查询某本书的借阅情况
     * @param readId 读者名
     * @return 读者的借阅信息
     */
    @Override
    public List<Lend> getLendByReaderId(Long readId) {
        LendExample lendExample=new LendExample();
        lendExample.createCriteria().andReadIdEqualTo(readId);
        List<Lend> list = lendMapper.selectByExample(lendExample);
        return list;
    }

    /**
     * 判断是否已归还(根据lend表中的流水号serialNum)
     * @param serialNum lend数据库中的流水号
     * @return true表示以归还
     */
    @Override
    public boolean isBack(Long serialNum) {
        LendExample lendExample=new LendExample();
        //先通过流水号查询到具体的某一条记录
        lendExample.createCriteria().andSerialNumEqualTo(serialNum);
        List<Lend> lends = lendMapper.selectByExample(lendExample);
        if (lends==null){
            throw new RuntimeException("流水号不存在");
        }
        Lend lend = lends.get(0);
        return lend.getBackDate() != null;
    }
}
