package com.czxy.controller;

import com.czxy.Vo.TeacherVo;
import com.czxy.domain.Teacher;
import com.czxy.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherController {
    @Resource
    private TeacherService teacherServiceImpl;

    @GetMapping
    public Integer selectAllTeacher(){
        return teacherServiceImpl.selectAllTeacher();
    }

    //多条件查询
    @PostMapping
    public List<Teacher> seachByCondition(@RequestBody TeacherVo teacherVo){
        System.out.println(teacherVo);
        return teacherServiceImpl.seachByCondition(teacherVo);
    }

    //添加老师
    @PostMapping("/addTeacher")
    public void addTeacher(@RequestBody Teacher teacher){
        System.out.println(teacher);
        teacherServiceImpl.addTeacher(teacher);
    }
    //批量删除
    @DeleteMapping("/{ids}")
    public void batchDel(@PathVariable("ids") String ids){
        String[] split = ids.split(",");
        for (String tid : split) {
            teacherServiceImpl.deleteTeacherByTid(tid);
        }
    }
    @GetMapping("/{tid}")
    public Teacher showTeacherByTid(@PathVariable("tid") Integer tid){
        return teacherServiceImpl.showTeacherByTid(tid);
    }

    //修改老师
    @PutMapping
    public void EditTeacher(@RequestBody Teacher teacher){
        teacherServiceImpl.EditTeacher(teacher);
    }
}
