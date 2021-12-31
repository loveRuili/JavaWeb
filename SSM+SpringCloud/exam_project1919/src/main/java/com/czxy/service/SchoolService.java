package com.czxy.service;

import com.czxy.domain.School;

import java.util.List;

public interface SchoolService {
    List<School> selectAllSchool();

    void addSchool(School school);

    School selectSchoolBySid(Integer sid);

    void EditSchool(School school);

    void SchoolDel(Integer sid);
}
