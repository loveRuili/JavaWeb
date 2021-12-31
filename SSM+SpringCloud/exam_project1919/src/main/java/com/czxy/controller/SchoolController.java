package com.czxy.controller;

import com.czxy.domain.School;
import com.czxy.service.SchoolService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/school")
@CrossOrigin
public class SchoolController {
    @Resource
    private SchoolService schoolServiceImpl;
    @GetMapping
    public List<School> selectAllSchool(){
        List<School> schools = schoolServiceImpl.selectAllSchool();
        return schools;
    }

    @PostMapping
    public void addSchool(@RequestBody School school){
        System.out.println(school);
        schoolServiceImpl.addSchool(school);
    }

    @GetMapping("/{sid}")
    public School showSchool(@PathVariable("sid") Integer sid){
        System.out.println(sid);
        return schoolServiceImpl.selectSchoolBySid(sid);
    }

    @PutMapping
    public void EditSchool(@RequestBody School school){
        schoolServiceImpl.EditSchool(school);
    }
    @DeleteMapping("/{sid}")
    public void SchoolDel(@PathVariable("sid") Integer sid){
        System.out.println(sid);
        schoolServiceImpl.SchoolDel(sid);
    }
}
