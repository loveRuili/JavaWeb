package com.czxy.service.Impl;

import com.czxy.Type;
import com.czxy.mapper.TypeMapper;
import com.czxy.service.TypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Resource
    private TypeMapper typeMapper;
    @Override
    public Type selectTypeByTid(Integer tid) {
        Type type = typeMapper.selectByPrimaryKey(tid);
        return type;
    }

    @Override
    public List<Type> selectAllType() {
        List<Type> types = typeMapper.selectAll();
        return types;
    }
}
