package top.itcat.xuecheng.content.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.itcat.xuecheng.content.model.dto.SaveTeachplanDto;
import top.itcat.xuecheng.content.model.dto.TeachplanDto;
import top.itcat.xuecheng.content.service.TeachplanService;

import java.util.List;

/**
 * <p>
 * 课程计划 前端控制器
 * </p>
 *
 * @author CatKitty
 */
@Api(value = "课程计划管理相关的接口", tags = "课程计划管理相关的接口")
@CrossOrigin
@RestController
@RequestMapping("/teachplan")
public class TeachplanController {

    @Autowired
    private TeachplanService  teachplanService;

    @ApiOperation("查询某课程的课程计划，组成树形结构")
    @GetMapping("/{courseId}/tree-nodes")
    public List<TeachplanDto> queryTreeNodes(@PathVariable("courseId") Long courseId) {
        return teachplanService.queryTreeNodes(courseId);
    }

    @ApiOperation("创建或修改课程计划")
    @PostMapping
    public void saveOrUpdateTeachplan(@RequestBody SaveTeachplanDto dto) {
        teachplanService.saveOrUpdateTeachplan(dto);
    }

    @ApiOperation("根据课程计划id删除课程计划")
    @DeleteMapping("/{id}")
    public long removeTeachplan(@PathVariable("id") Long id){
        return teachplanService.removeTeachplanById(id);
    }

    @ApiOperation("课程计划排序，moveup上移，movedown下移")
    @PostMapping("/{type}/{id}")
    public void move(@PathVariable("type") String type, @PathVariable("id") Long id) {
        teachplanService.move(type, id);
    }

}
