package com.ssm.controller;

import com.ssm.service.LendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 
 */
@Controller
public class LendController {

    @Autowired
    private LendService lendService;


}
