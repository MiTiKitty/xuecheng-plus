package top.itcat.xuecheng.content.service;


import com.baomidou.mybatisplus.extension.service.IService;
import top.itcat.xuecheng.content.model.dto.SaveTeachplanDto;
import top.itcat.xuecheng.content.model.dto.TeachplanDto;
import top.itcat.xuecheng.content.model.po.Teachplan;

import java.util.List;

/**
 * <p>
 * 课程计划 服务类
 * </p>
 *
 * @author CatKitty
 * @since 2023-02-01
 */
public interface TeachplanService extends IService<Teachplan> {

    /**
     * 查询某课程的课程计划，组成树形结构
     * @param courseId 课程id
     * @return List<TeachplanDto>
     */
    List<TeachplanDto> queryTreeNodes(Long courseId);

    /**
     * 创建或修改课程计划
     * @param dto 课程计划模型类
     */
    void saveOrUpdateTeachplan(SaveTeachplanDto dto);

    /**
     * 根据课程计划id删除课程计划及其相关信息
     * @param id 课程计划id
     * @return 课程计划id
     */
    long removeTeachplanById(Long id);

    /**
     * 课程计划排序，moveup上移，movedown下移
     * @param type
     * @param id
     */
    void move(String type, Long id);

    /**
     * 根据课程id删除所有课程计划
     * @param courseId 课程id
     */
    void removeTeachplanByCourseId(Long courseId);
}
