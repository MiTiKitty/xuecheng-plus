package top.itcat.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import groovy.util.logging.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.itcat.xuecheng.base.model.PageParams;
import top.itcat.xuecheng.base.model.PageResult;
import top.itcat.xuecheng.content.mapper.CourseBaseMapper;
import top.itcat.xuecheng.content.model.dto.QueryCourseParamsDto;
import top.itcat.xuecheng.content.model.po.CourseBase;
import top.itcat.xuecheng.content.service.CourseBaseService;

/**
 * <p>
 * 课程基本信息 服务实现类
 * </p>
 *
 * @author CatKitty
 */
@Slf4j
@Service
public class CourseBaseServiceImpl extends ServiceImpl<CourseBaseMapper, CourseBase> implements CourseBaseService {

    @Autowired
    private CourseBaseMapper courseBaseMapper;

    @Override
    public PageResult<CourseBase> queryCourseBaseList(PageParams params, QueryCourseParamsDto queryCourseParamsDto) {
        // 分页参数
        IPage<CourseBase> page = new Page<>(params.getPageNo(), params.getPageSize());
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        // 根据课程名称模糊查询、等值查询审核状态、等值查询课程发布状态
        queryWrapper
                .like(
                        StringUtils.isNotEmpty(queryCourseParamsDto.getCourseName()),
                        CourseBase::getName,
                        queryCourseParamsDto.getCourseName())
                .eq(
                        StringUtils.isNotEmpty(queryCourseParamsDto.getAuditStatus()),
                        CourseBase::getAuditStatus,
                        queryCourseParamsDto.getAuditStatus())
                .eq(
                        StringUtils.isNotEmpty(queryCourseParamsDto.getPublishStatus()),
                        CourseBase::getStatus,
                        queryCourseParamsDto.getPublishStatus());

        // 分页查询
        courseBaseMapper.selectPage(page, queryWrapper);

        // 返回数据
        return new PageResult<>(page.getRecords(), page.getTotal(), params.getPageNo(), params.getPageSize());
    }
}
