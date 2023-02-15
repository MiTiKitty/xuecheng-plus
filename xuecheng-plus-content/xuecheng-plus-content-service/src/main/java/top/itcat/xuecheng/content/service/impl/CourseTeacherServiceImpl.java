package top.itcat.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.itcat.xuecheng.base.exception.XueChengPlusException;
import top.itcat.xuecheng.content.mapper.CourseTeacherMapper;
import top.itcat.xuecheng.content.model.po.CourseTeacher;
import top.itcat.xuecheng.content.service.CourseTeacherService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 课程-教师关系表 服务实现类
 * </p>
 *
 * @author CatKitty
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CourseTeacherServiceImpl extends ServiceImpl<CourseTeacherMapper, CourseTeacher> implements CourseTeacherService {

    @Autowired
    private CourseTeacherMapper courseTeacherMapper;

    @Override
    public List<CourseTeacher> list(Long courseId) {
        LambdaQueryWrapper<CourseTeacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CourseTeacher::getCourseId, courseId);
        return courseTeacherMapper.selectList(wrapper);
    }

    @Override
    public CourseTeacher saveOrUpdateTeacher(CourseTeacher courseTeacher) {
        Long id = courseTeacher.getId();
        if (id == null) {
            courseTeacher.setCreateDate(LocalDateTime.now());
            courseTeacherMapper.insert(courseTeacher);
        }else {
            courseTeacherMapper.updateById(courseTeacher);
        }
        return courseTeacher;
    }

    @Override
    public void removeTeacher(Long courseId, Long teacherId) {
        LambdaQueryWrapper<CourseTeacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CourseTeacher::getCourseId, courseId)
                .eq(CourseTeacher::getId, teacherId);
        courseTeacherMapper.delete(wrapper);
    }

    @Override
    public void removeTeacherByCourseId(Long courseId) {
        LambdaQueryWrapper<CourseTeacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CourseTeacher::getCourseId, courseId);
        courseTeacherMapper.delete(wrapper);
    }
}
