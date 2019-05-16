package com.ji.controller;

import com.ji.domain.User;
import com.ji.service.UserService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ClassName: test
 * Description:
 * date: 2019/5/13 15:29
 *
 * @author ji
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/test")
public class test {
    @Autowired
    private UserService userService;
    @RequestMapping("/find")
    public User find(){
        return userService.findUserByName("亚索");
    }
    @RequestMapping("/find1")
    public List<User> find1(){
        return userService.findAll();
    }
    @RequestMapping("excel")
    public void excel(HttpServletResponse response) throws IOException {
        List<User> userList = userService.findAll();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("用户表");
        HSSFRow row = hssfSheet.createRow(0);
        //表头
        String[] heard = {"id","用户名","年龄","密码"};
        for (int i=0;i<heard.length;i++
             ) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString textString = new HSSFRichTextString(heard[i]);
            cell.setCellValue(textString);
        }
        int rowNum = 1;
        if (userList!=null&&userList.size()>0){
            for (User user:userList){
                HSSFRow rowUser = hssfSheet.createRow(rowNum);
                rowUser.createCell(0).setCellValue(user.getId());
                rowUser.createCell(1).setCellValue(user.getName());
                rowUser.createCell(2).setCellValue(user.getAge());
                rowUser.createCell(3).setCellValue(user.getPassword());
                rowNum++;
            };
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + "用户表.xls");
        response.flushBuffer();
        hssfWorkbook.write(response.getOutputStream());
//        response.getWriter().write();
    }
}
