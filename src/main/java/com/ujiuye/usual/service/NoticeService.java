package com.ujiuye.usual.service;

import com.ujiuye.usual.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NoticeService {
    @Resource
    private NoticeMapper noticeMapper;

}
