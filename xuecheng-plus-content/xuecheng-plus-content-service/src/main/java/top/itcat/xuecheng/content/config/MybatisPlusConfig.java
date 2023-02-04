package top.itcat.xuecheng.content.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className: MybatisPlusConfig <br/>
 * @description: mybatis配置类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/02/04 <br/>
 * @version: 1.0.0 <br/>
 */
@Configuration
@MapperScan("top.itcat.xuecheng.content.mapper")
public class MybatisPlusConfig {

    /**
     * 定义分页拦截器
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
