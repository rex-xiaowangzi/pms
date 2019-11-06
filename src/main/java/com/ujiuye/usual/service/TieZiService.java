package com.ujiuye.usual.service;

import com.ujiuye.usual.bean.TieZi;
import com.ujiuye.usual.mapper.TieZiMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service

public class TieZiService {

    @Resource
    private TieZiMapper tieZiMapper;
    public void saveInfo(TieZi tieZi) {
        tieZiMapper.saveInfo(tieZi);

    }
}
