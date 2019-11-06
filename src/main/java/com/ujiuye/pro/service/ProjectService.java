package com.ujiuye.pro.service;

import com.ujiuye.cus.bean.Customer;
import com.ujiuye.cus.bean.CustomerExample;
import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.ProjectExample;
import com.ujiuye.pro.mapper.ProjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class ProjectService {

    @Resource
    private ProjectMapper projectMapper;

    public void saveInfo(Project project) {
        projectMapper.insert(project);
    }

    public List<Project> getProjectList() {

        List<Project> projects=projectMapper.selectManagersList();
        return projects;
    }

    public boolean batchDelete(Integer[] ids) {
        List<Integer> idList = Arrays.asList(ids);
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andPidIn(idList);
        int i = projectMapper.deleteByExample(example);
        return ids.length==i;
    }

    public Project detail(Integer pid) {

        return projectMapper.selectByPrimaryKey(pid);
    }

    public Project updateById(Integer pid) {
        Project project = projectMapper.selectByPrimaryKey(pid);
        return project;
    }

    public void editById(Project project) {
        projectMapper.updateByPrimaryKeySelective(project);
    }

    public List<Project> getProjectJsonList() {
        ProjectExample example=new ProjectExample();
        return projectMapper.selectByExample(example);
    }

    public List<Project> getProjectList(Integer pid, String keyword, Integer orderby) {

        List<Project> projects=projectMapper.selectProjectList(pid,keyword,orderby);
        return projects;
    }
}
