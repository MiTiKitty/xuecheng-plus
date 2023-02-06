package top.itcat.xuecheng.content.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.itcat.xuecheng.content.model.dto.CourseCategoryDto;
import top.itcat.xuecheng.content.service.CourseCategoryService;

import java.util.List;

/**
 * @className: CourseCategoryController <br/>
 * @description: 课程分类相关接口 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/02/04 <br/>
 * @version: 1.0.0 <br/>
 */
@Api(value = "课程分类相关接口", tags = "课程分类")
@CrossOrigin
@RestController
@RequestMapping("/course-category")
public class CourseCategoryController {

    @Autowired
    private CourseCategoryService courseCategoryService;

    @ApiOperation("课程分类查询接口")
    @GetMapping("/tree-nodes")
    public List<CourseCategoryDto> queryTreeNodes() {
        return courseCategoryService.queryTreeNodes("1");
    }

}
