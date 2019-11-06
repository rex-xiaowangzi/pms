package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Attachment;
import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.mapper.AttachmentMapper;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AttachmentService {

    @Resource
    private AttachmentMapper attachmentMapper;
    public void saveInfo(Attachment atta) {
        attachmentMapper.insert(atta);
    }

    public List<Attachment> getAttachList() {
        List<Attachment> attachments=attachmentMapper.selectAttachment();
        return attachments;
    }
}
