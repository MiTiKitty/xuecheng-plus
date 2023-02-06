package top.itcat.xuecheng.content;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @className: CourseApiApplication <br/>
 * @description: 课程启动类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/02/03 <br/>
 * @version: 1.0.0 <br/>
 */
@SpringBootApplication
@EnableSwagger2Doc
public class CourseApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseApiApplication.class, args);
    }

}
