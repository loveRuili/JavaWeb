package com.czxy.service.Impl;

import com.czxy.domain.School;
import com.czxy.mapper.SchoolMapper;
import com.czxy.service.SchoolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Resource
    private SchoolMapper schoolMapper;
    @Override
    public List<School> selectAllSchool() {
        List<School> schools = schoolMapper.selectAll();
        return schools;
    }

    @Override
    public void addSchool(School school) {
        schoolMapper.insert(school);
    }

    @Override
    public School selectSchoolBySid(Integer sid) {
        School school = schoolMapper.selectByPrimaryKey(sid);
        return school;
    }

    @Override
    public void EditSchool(School school) {
        System.out.println(school);
        schoolMapper.updateByPrimaryKey(school);
    }

    @Override
    public void SchoolDel(Integer sid) {
        schoolMapper.deleteByPrimaryKey(sid);
    }
}
