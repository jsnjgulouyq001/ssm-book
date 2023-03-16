package com.ssm.service.impl;


import com.ssm.bean.Card;
import com.ssm.bean.CardExample;
import com.ssm.mapper.CardMapper;
import com.ssm.service.ReaderCardRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ReaderCardRegisterServiceImpl implements ReaderCardRegisterService {

    @Autowired
    private CardMapper cardMapper;

    /**
     * 登录读者系统
     * @param username 用户名
     * @param password 密码
     * @return 如果匹配，返回登录的用户名和密码
     */
    @Override
    public Card loginReaderByReadIdAndPassword(String username, String password) {
        CardExample cardExample = new CardExample();
        cardExample.createCriteria().andUsernameEqualTo(username)
                .andPasswordEqualTo(password);
        List<Card> list = cardMapper.selectByExample(cardExample);
        if (list != null) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 修改密码
     * @param username 用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return true表示密码修改成功
     */
    @Override
    public Card updatePasswordByUsername(String username, String oldPassword, String newPassword) {
        CardExample cardExample = new CardExample();
        cardExample.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(oldPassword);
        List<Card> list = cardMapper.selectByExample(cardExample);
        if (list == null) {
            return null;
        }
        Card card = list.get(0);
        card.setPassword(newPassword);
        cardMapper.updateByExampleSelective(card, cardExample);
        return card;
    }
}
