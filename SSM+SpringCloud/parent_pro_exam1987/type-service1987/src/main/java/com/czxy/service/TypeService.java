package com.czxy.service;

import com.czxy.Type;

import java.util.List;

public interface TypeService {
    Type selectTypeByTid(Integer tid);

    List<Type> selectAllType();

}
