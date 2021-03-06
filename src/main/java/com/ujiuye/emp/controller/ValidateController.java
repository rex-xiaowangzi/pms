package com.ujiuye.emp.controller;

import com.ujiuye.common.ValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/code")
public class ValidateController {

    @RequestMapping(value="/getCode")
    public void getCode(@RequestParam(value = "time") String time, HttpServletRequest request, HttpServletResponse response) {
        ValidateCode code = new ValidateCode(80, 30, 4, 30, 25, "validateCode");
        code.getCode(request, response);

    }
}
