package top.itcat.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.itcat.xuecheng.base.model.PageParams;
import top.itcat.xuecheng.base.model.PageResult;
import top.itcat.xuecheng.content.model.dto.AddCourseDto;
import top.itcat.xuecheng.content.model.dto.CourseBaseInfoDto;
import top.itcat.xuecheng.content.model.dto.EditCourseDto;
import top.itcat.xuecheng.content.model.dto.QueryCourseParamsDto;
import top.itcat.xuecheng.content.model.po.CourseBase;

/**
 * <p>
 * 课程基本信息 服务类
 * </p>
 *
 * @author CatKitty
 * @since 2023-02-01
 */
public interface CourseBaseService extends IService<CourseBase> {

    /**
     * 课程查询
     *
     * @param params               分页参数
     * @param queryCourseParamsDto 查询条件
     * @return PageResult<CourseBase>
     */
    PageResult<CourseBase> queryCourseBaseList(PageParams params, QueryCourseParamsDto queryCourseParamsDto);

    /**
     * 添加课程
     *
     * @param companyId    机构id
     * @param addCourseDto 课程信息
     * @return 课程基本信息
     */
    CourseBaseInfoDto createCourse(Long companyId, AddCourseDto addCourseDto);

    /**
     * 根据课程id查询课程基本信息
     *
     * @param courseId 课程id
     * @return 课程基本信息
     */
    CourseBaseInfoDto getCourseBaseInfoByCourseId(Long courseId);

    /**
     * 修改课程基本信息
     *
     * @param companyId 机构id
     * @param dto       课程信息
     * @return
     */
    CourseBaseInfoDto editCourseBase(Long companyId, EditCourseDto dto);

    /**
     * 根据课程id删除课程及其所有信息
     *
     * @param id 课程id
     */
    void removeCourseById(Long id);
}
