package com.bookshelf.bookshelf_20250223.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
public class ViewController {

    @GetMapping("top")
    public String top() {
        log.info("/top start");

        return "top";
    }

}
