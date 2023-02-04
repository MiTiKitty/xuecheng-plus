package top.itcat.xuecheng.content.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.itcat.xuecheng.content.model.po.CourseBase;

/**
 * @className: CourseBaseMapperTest <br/>
 * @description: CourseBaseMapper测试类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/02/04 <br/>
 * @version: 1.0.0 <br/>
 */
@SpringBootTest
public class CourseBaseMapperTest {

    @Autowired
    private CourseBaseMapper courseBaseMapper;

    @Test
    public void testCourseBaseMapper() {
        CourseBase courseBase = courseBaseMapper.selectById(22);
        Assertions.assertNotNull(courseBase);
    }

}
