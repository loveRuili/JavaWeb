package com.czxy.service.Impl;

import com.czxy.Vo.TeacherVo;
import com.czxy.domain.School;
import com.czxy.domain.Teacher;
import com.czxy.domain.Type;
import com.czxy.mapper.SchoolMapper;
import com.czxy.mapper.TeacherMapper;
import com.czxy.mapper.TypeMapper;
import com.czxy.service.TeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private SchoolMapper schoolMapper;
    @Resource
    private TypeMapper typeMapper;

    @Override
    public List<Teacher> seachByCondition(TeacherVo teacherVo) {
        //多条件查询
        Example example = new Example(Teacher.class);
        Example.Criteria criteria = example.createCriteria();
        //查询所有学校
        List<School> schools = schoolMapper.selectAll();
        if (StringUtils.isNotBlank(teacherVo.getTeacher_name())){
            criteria.andLike("teacher_name","%"+teacherVo.getTeacher_name()+"%");
        }
        if (StringUtils.isNotBlank(teacherVo.getSchool_name())){
            for (School school : schools) {
                if (school.getSchool_name().equals(teacherVo.getSchool_name())){
                    criteria.andEqualTo("school_id",school.getSid());
                }
            }
        }
        if (teacherVo.getMinSalary()!=null&&teacherVo.getMinSalary()!=0){
            criteria.andGreaterThanOrEqualTo("salary",teacherVo.getMinSalary());
        }
        if (teacherVo.getMaxSalary()!=null&&teacherVo.getMaxSalary()!=0){
            criteria.andLessThanOrEqualTo("salary",teacherVo.getMaxSalary());
        }
        if (teacherVo.getStartHire_date()!=null){
            criteria.andGreaterThanOrEqualTo("hire_date",teacherVo.getStartHire_date());
        }
        if (teacherVo.getEndHire_date()!=null){
            criteria.andLessThanOrEqualTo("hire_date",teacherVo.getEndHire_date());
        }
        //开启分页
        PageHelper.startPage(teacherVo.getPageNum(),teacherVo.getPageSize());
        List<Teacher> teachers = teacherMapper.selectByExample(example);
        for (Teacher teacher : teachers) {
            //1.注入学校信息
            List<School> schoolList = schoolMapper.selectAll();
            for (School school : schoolList) {
                if (teacher.getSchool_id()==school.getSid()){
                    teacher.setSchool(school);
                }
            }
            //2.注入类型信息
            List<Type> types = typeMapper.selectAll();
            for (Type type : types) {
                if (teacher.getType_id()==type.getTypeId()){
                    teacher.setType(type);
                }
            }
        }
        PageInfo<Teacher> pageInfo = new PageInfo<>(teachers);
        System.out.println(pageInfo.getSize());
        return teachers;
    }

    @Override
    public void addTeacher(Teacher teacher) {
        teacherMapper.insert(teacher);
    }

    @Override
    public void deleteTeacherByTid(String tid) {
        int tidInt = Integer.parseInt(tid);
        teacherMapper.deleteByPrimaryKey(tidInt);
    }

    @Override
    public Teacher showTeacherByTid(Integer tid) {
        Teacher teacher = teacherMapper.selectByPrimaryKey(tid);
        return teacher;
    }

    @Override
    public void EditTeacher(Teacher teacher) {
        System.out.println(teacher);
        teacherMapper.updateByPrimaryKey(teacher);
    }

    @Override
    public Integer selectAllTeacher() {
        return teacherMapper.selectAll().size();
    }
}
