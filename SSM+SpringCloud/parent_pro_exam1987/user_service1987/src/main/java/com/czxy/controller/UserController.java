package com.czxy.controller;

import com.czxy.BaseResult;
import com.czxy.CodeUtils;
import com.czxy.User;
import com.czxy.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userServiceImpl;
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping
    public BaseResult login(@RequestBody User user){
        if (user.getUsername()!=null&&user.getUsername()!=""&&user.getPassword()!=null&&user.getPassword()!=""){
            BaseResult login = userServiceImpl.login(user);
            return login;
        }
        return BaseResult.fail();
    }
    @GetMapping("/{uid}")
    public User selectUserByUid(@PathVariable("uid") Integer uid){
        User user = userServiceImpl.selectUserByUid(uid);
        return user;
    }
    @GetMapping("/check/{username}")
    public BaseResult checkname(@PathVariable("username") String username){
        BaseResult checkname = userServiceImpl.checkname(username);

        return checkname;
    }
    @GetMapping("/senCode/{phoneCode}")
    public BaseResult senCode(@PathVariable("phoneCode") String phoneCode){
        System.out.println(phoneCode);
        //1.生成验证码
        String code = CodeUtils.Code();

        //2.存储到消息队列中
        String queueName = "itcast.code";
        rabbitTemplate.convertAndSend(queueName,code);

        //3.存储到Redis中,有效时间为1分钟
        redisTemplate.opsForValue().set(phoneCode,code,1, TimeUnit.MINUTES);
        return BaseResult.success("成功");
    }

    @PostMapping("/register")
    public BaseResult register(@RequestBody User user){
        System.out.println(user);
        //1.在redis中获取验证码
        String code = (String) redisTemplate.opsForValue().get(user.getMobile());
        System.out.println(code);
        //2.比较验证码是否一致
        if (user.getCode().equals(code)){
            //2.1删除redis中的验证码
            redisTemplate.delete(user.getMobile());
            //2.2比较密码
            if (user.getPassword().equals(user.getRepassword())){
                return BaseResult.success("注册成功！");
            }
        }
            return BaseResult.fail();
    }
}
