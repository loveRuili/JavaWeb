package com.czxy.service;

import com.czxy.Vo.TeacherVo;
import com.czxy.domain.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> seachByCondition(TeacherVo teacherVo);

    void addTeacher(Teacher teacher);

    void deleteTeacherByTid(String tid);

    Teacher showTeacherByTid(Integer tid);

    void EditTeacher(Teacher teacher);

    Integer selectAllTeacher();
}
