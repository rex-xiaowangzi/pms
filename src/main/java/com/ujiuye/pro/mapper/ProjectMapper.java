package com.ujiuye.pro.mapper;

import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.ProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectMapper {
    int countByExample(ProjectExample example);

    int deleteByExample(ProjectExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(Project record);

    int insertSelective(Project record);

    List<Project> selectByExample(ProjectExample example);

    Project selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);


    List<Project> selectManagersList();

    List<Project> selectProjectList(@Param("pid") Integer pid,@Param("keyword") String keyword,@Param("orderby") Integer orderby);
}