package com.ji.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ji.domain.Directory;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName: DirectoryMapper
 * Description:
 * date: 2019/5/7 17:16
 *
 * @author ji
 * @version 1.0
 * @since JDK 1.8
 */
public interface DirectoryMapper extends BaseMapper<Directory> {
    @Select("select * from directory where id in (select did from role_directory where rid in (select rid from user_role where uid = #{id}))")
    public List<Directory> findByUserId(String id);
}
