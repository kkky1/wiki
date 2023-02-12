package com.example.wiki.controller;

import com.example.wiki.service.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestConller {

    @Resource
    private TestServiceImpl testServic;

    @GetMapping("/test")
    public void get(){
        testServic.selectAll();
    }
}
