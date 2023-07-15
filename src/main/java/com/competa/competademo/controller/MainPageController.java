package com.competa.competademo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

//    @GetMapping("/") // - page Main controller
//    public String home( Model model) {
//        model.addAttribute("title", "Main page");
//        return "home";
//    }

    @GetMapping("/about") // - page About controller
    public String about( Model model) {
        model.addAttribute("title", "About");
        return "about";
    }

}
