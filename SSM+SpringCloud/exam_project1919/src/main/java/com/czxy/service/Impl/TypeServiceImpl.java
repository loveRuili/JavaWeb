package com.czxy.service.Impl;

import com.czxy.domain.Type;
import com.czxy.mapper.TypeMapper;
import com.czxy.service.TypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Resource
    public TypeMapper typeMapper;
    @Override
    public List<Type> selectAllType() {
        List<Type> types = typeMapper.selectAll();
        return types;
    }
}
