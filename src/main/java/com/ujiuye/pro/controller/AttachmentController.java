package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Attachment;
import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/attach")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping(value = "saveInfo",method = RequestMethod.POST)
    public String saveInfo(Attachment atta, MultipartFile attachment, HttpSession session) throws IOException {
        ServletContext servletContext = session.getServletContext();
        String path = servletContext.getRealPath("/upload");
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String originalFilename = attachment.getOriginalFilename();

        String realName = UUID.randomUUID().toString().replaceAll("-", " ") + originalFilename;
        try {
            attachment.transferTo(new File(path + "/" + realName));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
            atta.setPath(path+"/"+realName);
        attachmentService.saveInfo(atta);
        return "redirect:/project-file.jsp";

    }

    //下载
    @RequestMapping(value = "download",method = RequestMethod.GET)
    public String downLoad(HttpServletRequest request){

        String servletPath = request.getServletPath();
        String fileName ="";
        return null;
    }

    //查询所有项目
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getAttachList(){
        List<Attachment> list=attachmentService.getAttachList();
        ModelAndView mv= new ModelAndView("project-file");
        mv.addObject("list",list);
        return mv;
    }



}
