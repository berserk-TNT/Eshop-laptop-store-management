package com.cg.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class HomeController {

    @GetMapping("/users")
    public ModelAndView showListUser(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/user-list");
        return modelAndView;
    }

    @GetMapping("/products")
    public ModelAndView showListProduct(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/product/product-list");
        return modelAndView;
    }

    @GetMapping("/*")
    public String showErrorOneSlash() {
        return "redirect:/error";
    }

    @GetMapping("/error")
    public ModelAndView showErrorPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/error/error-page");
        return modelAndView;
    }
}
