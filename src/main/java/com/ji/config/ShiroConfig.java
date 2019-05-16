package com.ji.config;

import com.ji.shiro.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: ShiroConfig
 * Description:
 * date: 2019/5/13 11:03
 *
 * @author ji
 * @version 1.0
 * @since JDK 1.8
 */
//shiros配置文件
@Configuration
public class ShiroConfig {
    //shiro过滤器
    @Bean
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        //创建过滤map
        Map<String,String> filtetMap =new HashMap<>();
        filtetMap.put("/admin/*","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filtetMap);
        //设置登陆页面路径
        shiroFilterFactoryBean.setLoginUrl("/admin/login");
        //设置登陆成功页面
        shiroFilterFactoryBean.setSuccessUrl("/admin/index");
        return shiroFilterFactoryBean;
    }
    //核心安全事务管理器
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager ();
        //设置realm
        securityManager.setRealm(userRealm());
        return securityManager;
    }
    //配置Realm
    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }
}
