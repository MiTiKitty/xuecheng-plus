package top.itcat.xuecheng.content.service;


import com.baomidou.mybatisplus.extension.service.IService;
import top.itcat.xuecheng.content.model.po.CourseTeacher;

import java.util.List;

/**
 * <p>
 * 课程-教师关系表 服务类
 * </p>
 *
 * @author CatKitty
 * @since 2023-02-01
 */
public interface CourseTeacherService extends IService<CourseTeacher> {

    /**
     * 根据课程id查找该课程下的所有教师
     *
     * @param courseId 课程id
     * @return List<CourseTeacher>
     */
    List<CourseTeacher> list(Long courseId);

    /**
     * 保存或修改教师信息
     *
     * @param courseTeacher 教师模型
     * @return CourseTeacher
     */
    CourseTeacher saveOrUpdateTeacher(CourseTeacher courseTeacher);

    /**
     * 根据课程id和教师id删除教师
     *
     * @param courseId 课程id
     * @param teacherId 教师id
     */
    void removeTeacher(Long courseId, Long teacherId);

    /**
     * 根据课程id删除教师信息
     *
     * @param courseId 课程id
     */
    void removeTeacherByCourseId(Long courseId);
}
