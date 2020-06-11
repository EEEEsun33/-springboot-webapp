package com.zys.order_meal.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DruilConfig {

    //绑定datasource
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    //后台监控功能，相当于web.xml
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        //后台管理的账号密码配置
        HashMap<String,String> initParameters = new HashMap<>();
        initParameters.put("loginUsername","zhangyushan");//key固定为loginUsername
        initParameters.put("loginPassword","123456");//key固定为loginPassword

        //允许谁可以访问
        initParameters.put("allow","");//空白代表都可以访问

        bean.setInitParameters(initParameters);//设置初始化参数
        return bean;
    }

}
