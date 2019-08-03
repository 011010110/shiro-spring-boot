package org.jiang.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Li.Linhua
 * @description Mybatis配置类
 * @Date 2019/7/15
 */
@Configuration
@EnableTransactionManagement
@MapperScan("org.jiang.core.*.dao.mapper")
public class MybatisConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
