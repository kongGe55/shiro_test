package com.ji.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ji.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import javax.annotation.Resources;
import java.util.List;

/**
 * ClassName: UserMapper
 * Description:
 * date: 2019/4/30 16:44
 *
 * @author ji
 * @version 1.0
 * @since JDK 1.8
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where id = #{id}")
    public User findUserById(String id);
    @Select("select password,name,id from user where name = #{name}")
    public User findUserByName(String name);
    @Insert("insert into user (id,name,password) values (#{id},#{name},#{password})")
    int insertUser(User user);
    List<User> findAll();
}
