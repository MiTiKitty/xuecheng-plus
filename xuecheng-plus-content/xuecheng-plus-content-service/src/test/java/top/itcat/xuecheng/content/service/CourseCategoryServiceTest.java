package top.itcat.xuecheng.content.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.itcat.xuecheng.content.model.dto.CourseCategoryDto;

import java.util.List;

/**
 * @className: CourseCategoryServiceTest <br/>
 * @description: CourseCategoryService测试类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/02/04 <br/>
 * @version: 1.0.0 <br/>
 */
@SpringBootTest
public class CourseCategoryServiceTest {

    @Autowired
    private CourseCategoryService courseCategoryService;

    @Test
    public void testQueryTreeNodes() {
        List<CourseCategoryDto> treeNodes = courseCategoryService.queryTreeNodes();
        System.out.println(treeNodes);
    }

}
