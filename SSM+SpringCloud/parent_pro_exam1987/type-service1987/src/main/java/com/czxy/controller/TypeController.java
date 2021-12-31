package com.czxy.controller;

import com.czxy.BaseResult;
import com.czxy.Type;
import com.czxy.service.TypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {
    @Resource
    private TypeService typeServiceImpl;

    @GetMapping("/{tid}")
    public Type selectTypeByTid(@PathVariable("tid") Integer tid){
        Type type = typeServiceImpl.selectTypeByTid(tid);
        return type;
    }
    @GetMapping
    public BaseResult selectAllType(){
        List<Type> types = typeServiceImpl.selectAllType();
        return BaseResult.success(types);
    }
}
