package com.ujiuye.compare.controller;

import com.ujiuye.compare.service.BenchMarkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bench")
public class BenchMarkingController {
    @Autowired
    private BenchMarkingService benchMarkingService;

    public String saveInfo(){

        
        return "";
    }

}
