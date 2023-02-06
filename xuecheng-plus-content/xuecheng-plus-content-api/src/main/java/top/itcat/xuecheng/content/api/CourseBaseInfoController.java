package top.itcat.xuecheng.content.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.itcat.xuecheng.base.model.PageParams;
import top.itcat.xuecheng.base.model.PageResult;
import top.itcat.xuecheng.content.model.dto.AddCourseDto;
import top.itcat.xuecheng.content.model.dto.CourseBaseInfoDto;
import top.itcat.xuecheng.content.model.dto.QueryCourseParamsDto;
import top.itcat.xuecheng.content.model.po.CourseBase;
import top.itcat.xuecheng.content.service.CourseBaseService;

/**
 * @className: CourseBaseInfoController <br/>
 * @description: 课程信息编辑接口 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/02/03 <br/>
 * @version: 1.0.0 <br/>
 */
@Api(value = "课程信息编辑接口", tags = "课程基本信息")
@CrossOrigin
@RestController
@RequestMapping("/course")
public class CourseBaseInfoController {

    @Autowired
    private CourseBaseService courseBaseService;

    @ApiOperation("课程查询接口")
    @PostMapping("/list")
    public PageResult<CourseBase> list(PageParams params,
                                       @RequestBody QueryCourseParamsDto queryCourseParamsDto) {
        // 调用service获取数据并返回
        return courseBaseService.queryCourseBaseList(params, queryCourseParamsDto);
    }

    @PostMapping
    public CourseBaseInfoDto createCourse(@RequestBody AddCourseDto addCourseDto) {
        // TODO 获取当前机构id
        Long companyId = 1L;

        // 调用service，添加课程
        return courseBaseService.createCourse(companyId, addCourseDto);
    }

}
