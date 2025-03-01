package com.bookshelf.bookshelf_20250223.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {

    @GetMapping("/get")
    public String get() {
        return "index";
    }

}
