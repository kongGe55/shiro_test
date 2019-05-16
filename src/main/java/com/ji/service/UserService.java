package com.ji.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ji.domain.User;
import com.ji.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * ClassName: UserService
 * Description:
 * date: 2019/4/30 16:46
 *
 * @author ji
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class UserService{
    @Autowired
    private UserMapper userMapper;
    public User findUserById(String id){
        User user = userMapper.findUserById(id);
        return user;
    }
    public User findUserByName(String name){
        return userMapper.findUserByName(name);
    }
    public List<User> findAll(){
//        return userMapper.findAll();
        return userMapper.selectByMap(null);
    }

    public boolean register(User eq) {
        eq.setId(randomId());
        int i = userMapper.insertUser(eq);
        if (i==1){
            return true;
        }
        return false;
    }
    String randomId(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmddhhssmm");
        String s = null;
        while (true){
            s = simpleDateFormat.format(new Date())+"ID";
            User user = userMapper.findUserById(s);
            if (user==null){
                break;
            }
        }
        return s;
    }
}
