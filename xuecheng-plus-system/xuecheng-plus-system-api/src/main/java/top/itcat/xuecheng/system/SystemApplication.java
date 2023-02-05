package top.itcat.xuecheng.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @className: SystemApplication <br/>
 * @description: 系统管理启动类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/02/05 <br/>
 * @version: 1.0.0 <br/>
 */
@EnableSwagger2
@CrossOrigin
@SpringBootApplication
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

}
