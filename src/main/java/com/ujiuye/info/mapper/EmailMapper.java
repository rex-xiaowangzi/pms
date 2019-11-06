//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ujiuye.info.mapper;

import com.ujiuye.info.bean.Email;
import com.ujiuye.usual.bean.EmailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmailMapper {
    int countByExample(EmailExample var1);

    int deleteByExample(EmailExample var1);

    int deleteByPrimaryKey(Integer var1);

    int insert(Email var1);

    int insertSelective(Email var1);

    List<Email> selectByExample(EmailExample var1);

    Email selectByPrimaryKey(Integer var1);

    int updateByExampleSelective(@Param("record") Email var1, @Param("example") EmailExample var2);

    int updateByExample(@Param("record") Email var1, @Param("example") EmailExample var2);

    int updateByPrimaryKeySelective(Email var1);

    int updateByPrimaryKey(Email var1);
}
