package com.ji.controller;

import com.alibaba.druid.sql.visitor.functions.If;
import com.ji.domain.Directory;
import com.ji.domain.User;
import com.ji.mapper.DirectoryMapper;
import com.ji.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.lang.model.element.NestingKind;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Stream;

/**
 * ClassName: AdminController
 * Description:
 * date: 2019/5/6 13:59
 *
 * @author ji
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
public class AdminController {
    @Autowired
    private DirectoryMapper directoryMapper;
    @Autowired
    private UserService userService;
    @RequestMapping("admin/login")
    public String Login(){
        return "admin/login";
    }
/*    @RequestMapping("/admin/index")
    public String index(HttpServletRequest request, ModelMap map) {
        String id = (String) request.getSession().getAttribute("id");
        User user = userService.findUserById(id);
        List<Directory> directoryList = directoryMapper.findByUserId(id);
        Set<Directory> directorySet = new HashSet<Directory> ();
        directorySet.addAll(directoryList);
        //先取出顶级节点
        List<Directory> topList = new ArrayList<>();
        Iterator<Directory> iterator = directorySet.iterator();
        while (iterator.hasNext()){
            Directory directory = iterator.next();
            if (isEmpty(directory.getPid())){
                topList.add(directory);
                iterator.remove();
            }
        }
        dealDirectorySetToTree(directorySet,topList);
        map.addAttribute("user", user);
        map.addAttribute("list",topList);
        return "admin/index";
    }*/
    @ResponseBody
    @RequestMapping("/admin/test")
    public List<Directory> test(HttpServletRequest request) {
        String id = (String) request.getSession().getAttribute("id");
        User user = userService.findUserById(id);
        List<Directory> directoryList = directoryMapper.findByUserId(id);
        Set<Directory> directorySet = new HashSet<Directory> ();
        directorySet.addAll(directoryList);
        //先取出顶级节点
        List<Directory> topList = new ArrayList<>();
        Iterator<Directory> iterator = directorySet.iterator();
        while (iterator.hasNext()){
            Directory directory = iterator.next();
            if (isEmpty(directory.getPid())){
                topList.add(directory);
                iterator.remove();
            }
        }
        dealDirectorySetToTree(directorySet,topList);
        return topList;
    }
    //迭代
    private void dealDirectorySetToTree(Set<Directory> directorySet, List<Directory> nodeList) {
        for (Directory parent:nodeList){
            List<Directory> childList=new ArrayList<>();
            Iterator<Directory> iterator = directorySet.iterator();
            while (iterator.hasNext()){
                Directory child = iterator.next();
                if (child.getPid().equals(parent.getId())){
                    childList.add(child);
                    iterator.remove();
                }
            }
            parent.setChildList(childList);
            if (directorySet==null||directorySet.size()<1){
                return;
            }
            dealDirectorySetToTree(directorySet,childList);
        }
    }

    private boolean isEmpty(String pid) {
        return (pid==null||"".equals(pid)||"".equals(pid))?true:false;
    }
}
