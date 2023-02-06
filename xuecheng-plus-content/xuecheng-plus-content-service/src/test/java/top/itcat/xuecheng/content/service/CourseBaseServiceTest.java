package top.itcat.xuecheng.content.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.itcat.xuecheng.base.model.PageParams;
import top.itcat.xuecheng.base.model.PageResult;
import top.itcat.xuecheng.content.model.dto.QueryCourseParamsDto;
import top.itcat.xuecheng.content.model.po.CourseBase;

/**
 * @className: CourseBaseServiceTest <br/>
 * @description: CourseBaseService测试类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/02/04 <br/>
 * @version: 1.0.0 <br/>
 */
@SpringBootTest
public class CourseBaseServiceTest {

    @Autowired
    private CourseBaseService courseBaseService;

    @Test
    public void testQueryCourseBaseList() {
        PageResult<CourseBase> result = courseBaseService.queryCourseBaseList(new PageParams(), new QueryCourseParamsDto());
        System.out.println(result);
    }

    @Test
    public void testCreateCourse() {
        // TODO CreateCourse方法待测试
    }

}
