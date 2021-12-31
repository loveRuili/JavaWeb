package com.czxy.controller;

import com.czxy.domain.Type;
import com.czxy.service.TypeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/type")
@CrossOrigin
public class TypeController {
    @Resource
    public TypeService typeServiceImpl;
    @GetMapping
    public List<Type> selectAllType(){
        return typeServiceImpl.selectAllType();
    }
}
