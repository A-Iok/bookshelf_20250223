package com.bookshelf.bookshelf_20250223.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookshelf.bookshelf_20250223.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class StoreController {

    @Autowired
    LoginService loginService;

    @GetMapping("/get")
    public String get() {
        log.info("/get start");

        loginService.getEntity();
        return "index";
    }

}
