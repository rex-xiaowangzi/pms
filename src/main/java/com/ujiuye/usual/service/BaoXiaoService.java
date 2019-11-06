package com.ujiuye.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.usual.bean.Baoxiao;
import com.ujiuye.usual.bean.BaoxiaoExample;
import com.ujiuye.usual.mapper.BaoxiaoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class BaoXiaoService {

    @Resource
    private BaoxiaoMapper baoxiaoMapper;

    public void saveInfo(Baoxiao baoxiao) {
        String prefix = UUID.randomUUID().toString().replaceAll("-", " ");
        String suffix = UUID.randomUUID().toString().replaceAll("-", " ");
        baoxiao.setBxid(prefix+suffix);
        baoxiao.setBxstatus(0);
       baoxiaoMapper.insert(baoxiao);
    }

    public PageInfo<Baoxiao> getMyBaoXiaoList(Integer eid, Integer pageNum, Map<String, Object> parametmap) {
        BaoxiaoExample example=new BaoxiaoExample();
        BaoxiaoExample.Criteria criteria = example.createCriteria();
        criteria.andEmpFkEqualTo(eid);

        Map<String,String> mybatisMap = parseParameterMapToMyBatisMap(parametmap);
        String status = mybatisMap.get("status");
        String keyword = mybatisMap.get("keyword");
        if(status!=null&&status!=" "){
            criteria.andBxstatusEqualTo(Integer.valueOf(status));
        }
        if(keyword!=null&&keyword!=" "){
            criteria.andBxremarkLike(keyword);
        }

        PageHelper.startPage(pageNum,3);
        List<Baoxiao> baoxiaos = baoxiaoMapper.selectByExample(example);
        PageInfo<Baoxiao> page = new PageInfo<Baoxiao>(baoxiaos,5);
        return page;
    }

    private Map<String,String> parseParameterMapToMyBatisMap(Map<String, Object> parametmap) {
        //接收数据
        Map<String,String> resultMap=new HashMap<String, String>();
        Set<Map.Entry<String, Object>> entries = parametmap.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            String value = (String)entry.getValue();
            //如key值包含like，走模糊查询
            if (key.contains("like")){
                key=key.substring(key.indexOf("_")+1);//截取like_后面部分
                value="%"+value+"%";
            }
                resultMap.put(key,value);
        }
                return resultMap;
    }
}
