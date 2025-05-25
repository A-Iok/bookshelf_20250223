package com.bookshelf.bookshelf_20250223.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("")
public class ViewController {

    @GetMapping("/top")
    public String top(@RequestParam String id, Model model) {
        log.info("/top start");
        model.addAttribute("id", id);

        return "top";
    }

    @GetMapping("/bookshelf")
    public String bookshelf(@RequestParam String id, @RequestParam String bookshelfId, Model model) {
        log.info("/bookshelf start");
        model.addAttribute("id", id);
        model.addAttribute("bookshelfId", bookshelfId);

        return "bookshelf";
    }

}
