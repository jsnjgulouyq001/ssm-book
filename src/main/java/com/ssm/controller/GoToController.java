package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GoToController {

    @RequestMapping("/goToAdmin")
    public String goToAdmin() {
        return "admin/admin_login";
    }

    @RequestMapping("/goToReader")
    public String goToReader() {
        return "reader/reader_login";
    }

    @RequestMapping("/goToReaderRegister")
    public String goToReaderRegister() {
        return "reader/reader_register";
    }
}
