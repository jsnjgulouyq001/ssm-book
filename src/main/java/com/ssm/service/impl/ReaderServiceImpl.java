package com.ssm.service.impl;

import com.ssm.bean.Reader;
import com.ssm.bean.ReaderExample;
import com.ssm.mapper.ReaderMapper;
import com.ssm.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReaderServiceImpl implements ReaderService {

    @Autowired
    private ReaderMapper readerMapper;

    /**
     * 根据readerId查看个人信息(即查看读者证信息)
     * @param readerId 读者证号
     * @return 返回读者证上的读者信息
     */
    @Override
    public Reader getReaderById(Long readerId) {
        ReaderExample readerExample = new ReaderExample();
        readerExample.createCriteria().andReadIdEqualTo(readerId);
        List<Reader> list = readerMapper.selectByExample(readerExample);
        if (list == null) {
            throw new RuntimeException("未找到对应id的书籍");
        }
        return list.get(1);
    }

    /**
     * 修改个人信息
     * @param reader 读者
     * @return 修改个人信息影响的行数
     */
    @Override
    public Integer updateReader(Reader reader) {
        return readerMapper.updateByExampleSelective(reader, null);
    }
}
