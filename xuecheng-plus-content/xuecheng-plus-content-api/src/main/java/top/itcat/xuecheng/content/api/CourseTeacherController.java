package top.itcat.xuecheng.content.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.itcat.xuecheng.content.model.po.CourseTeacher;
import top.itcat.xuecheng.content.service.CourseTeacherService;

import java.util.List;

/**
 * @className: CourseTeacherController <br/>
 * @description: 教师管理接口 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/02/15 <br/>
 * @version: 1.0.0 <br/>
 */
@Api(value = "课程教师管理模块", tags = "课程教师管理模块")
@CrossOrigin
@RestController
@RequestMapping("/courseTeacher")
public class CourseTeacherController {

    @Autowired
    private CourseTeacherService courseTeacherService;

    @ApiOperation("根据课程id查找该课程下的所有教师信息")
    @GetMapping("/list/{courseId}")
    public List<CourseTeacher> list(@PathVariable("courseId") Long courseId) {
        return courseTeacherService.list(courseId);
    }

    @ApiOperation("保存或修改教师信息")
    @PostMapping
    public CourseTeacher saveOrUpdateTeacher(@RequestBody CourseTeacher courseTeacher) {
        return courseTeacherService.saveOrUpdateTeacher(courseTeacher);
    }

    @ApiOperation("根据课程id和教师id删除教师")
    @DeleteMapping("/{courseId}/{id}")
    public void removeTeacherById(@PathVariable("courseId") Long courseId,
                                  @PathVariable("id") Long id) {
        courseTeacherService.removeTeacher(courseId, id);
    }

}
