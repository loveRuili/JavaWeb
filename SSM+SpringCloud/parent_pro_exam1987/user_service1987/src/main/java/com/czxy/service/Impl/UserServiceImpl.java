package com.czxy.service.Impl;

import com.czxy.BaseResult;
import com.czxy.JWTUtil;
import com.czxy.User;
import com.czxy.mapper.UserMapper;
import com.czxy.service.UserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public BaseResult login(User user) {
        System.out.println(user);
        //条件查询
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",user.getUsername());
        criteria.andEqualTo("password",user.getPassword());
        User one = userMapper.selectOneByExample(example);
        if (one!=null){
            //登录成功
            System.out.println(one.getUid());
            String token = JWTUtil.createToken(one.getUid(), "user", 30);
            return BaseResult.success(token);
        }
        return BaseResult.fail();
    }

    @Override
    public User selectUserByUid(Integer uid) {
        User user = userMapper.selectByPrimaryKey(uid);
        return user;
    }

    @Override
    public BaseResult checkname(String username) {
        //条件查询
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",username);
        User user = userMapper.selectOneByExample(example);
        if (user!=null){
            return BaseResult.success("cg");
        }
        return BaseResult.fail();
    }
}
