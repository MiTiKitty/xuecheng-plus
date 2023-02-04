package top.itcat.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.itcat.xuecheng.base.model.PageParams;
import top.itcat.xuecheng.base.model.PageResult;
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
     * @param params 分页参数
     * @param queryCourseParamsDto 查询条件
     * @return PageResult<CourseBase>
     */
    PageResult<CourseBase> queryCourseBaseList(PageParams params, QueryCourseParamsDto queryCourseParamsDto);

}
