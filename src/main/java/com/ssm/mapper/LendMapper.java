package com.ssm.mapper;

import com.ssm.bean.Lend;
import com.ssm.bean.LendExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LendMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_lend
     *
     * @mbggenerated Fri Aug 12 14:39:37 CST 2022
     */
    int countByExample(LendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_lend
     *
     * @mbggenerated Fri Aug 12 14:39:37 CST 2022
     */
    int deleteByExample(LendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_lend
     *
     * @mbggenerated Fri Aug 12 14:39:37 CST 2022
     */
    int deleteByPrimaryKey(Long serialNum);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_lend
     *
     * @mbggenerated Fri Aug 12 14:39:37 CST 2022
     */
    int insert(Lend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_lend
     *
     * @mbggenerated Fri Aug 12 14:39:37 CST 2022
     */
    int insertSelective(Lend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_lend
     *
     * @mbggenerated Fri Aug 12 14:39:37 CST 2022
     */
    List<Lend> selectByExample(LendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_lend
     *
     * @mbggenerated Fri Aug 12 14:39:37 CST 2022
     */
    Lend selectByPrimaryKey(Long serialNum);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_lend
     *
     * @mbggenerated Fri Aug 12 14:39:37 CST 2022
     */
    int updateByExampleSelective(@Param("record") Lend record, @Param("example") LendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_lend
     *
     * @mbggenerated Fri Aug 12 14:39:37 CST 2022
     */
    int updateByExample(@Param("record") Lend record, @Param("example") LendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_lend
     *
     * @mbggenerated Fri Aug 12 14:39:37 CST 2022
     */
    int updateByPrimaryKeySelective(Lend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_lend
     *
     * @mbggenerated Fri Aug 12 14:39:37 CST 2022
     */
    int updateByPrimaryKey(Lend record);
}