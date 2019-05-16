package com.ji.controller;

import com.ji.common.ResultData;
import com.ji.domain.Directory;
import com.ji.domain.User;
import com.ji.mapper.DirectoryMapper;
import com.ji.service.UserService;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.subject.support.DelegatingSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ClassName: UserController
 * Description:
 * date: 2019/4/30 16:24
 *
 * @author ji
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("admin/user")
public class UserController {
    @Autowired
    private DirectoryMapper directoryMapper;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultData Login(User eq, HttpServletRequest request){
        User user1 = userService.findUserByName(eq.getName());
        if (eq.getPassword()!=null&&eq.getPassword().equals(user1.getPassword())){
            request.getSession().setAttribute("id",user1.getId());
            return new ResultData(0,null,null);
        }
        return new ResultData(1,null,"用户名或密码错误");
    }
    @RequestMapping("/register")
    public ResultData register(User eq){
        boolean b = userService.register(eq);
        if (b){
            return  new ResultData(0,null,null);
        }
        return new ResultData(1,null,null);
    }
    @RequestMapping("/role")
    public ResultData role(String id){
        List<Directory> directoryList = directoryMapper.findByUserId(id);
        return new ResultData(0,directoryList,null);
    }
}
