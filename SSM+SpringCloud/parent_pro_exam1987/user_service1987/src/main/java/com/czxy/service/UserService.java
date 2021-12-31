package com.czxy.service;

import com.czxy.BaseResult;
import com.czxy.User;

public interface UserService {
    BaseResult login(User user);

    User selectUserByUid(Integer uid);

    BaseResult checkname(String username);
}
