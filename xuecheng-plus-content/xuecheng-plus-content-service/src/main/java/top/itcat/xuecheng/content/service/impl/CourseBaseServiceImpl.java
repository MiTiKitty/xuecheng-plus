package top.itcat.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import groovy.util.logging.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.itcat.xuecheng.base.exception.XueChengPlusException;
import top.itcat.xuecheng.base.model.PageParams;
import top.itcat.xuecheng.base.model.PageResult;
import top.itcat.xuecheng.content.mapper.CourseBaseMapper;
import top.itcat.xuecheng.content.model.dto.AddCourseDto;
import top.itcat.xuecheng.content.model.dto.CourseBaseInfoDto;
import top.itcat.xuecheng.content.model.dto.EditCourseDto;
import top.itcat.xuecheng.content.model.dto.QueryCourseParamsDto;
import top.itcat.xuecheng.content.model.po.CourseBase;
import top.itcat.xuecheng.content.model.po.CourseCategory;
import top.itcat.xuecheng.content.model.po.CourseMarket;
import top.itcat.xuecheng.content.service.CourseBaseService;
import top.itcat.xuecheng.content.service.CourseCategoryService;
import top.itcat.xuecheng.content.service.CourseMarketService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private CourseMarketService courseMarketService;

    @Autowired
    private CourseCategoryService courseCategoryService;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CourseBaseInfoDto createCourse(Long companyId, AddCourseDto addCourseDto) {
        // 课程基本信息属性拷贝
        CourseBase courseBase = new CourseBase();
        BeanUtils.copyProperties(addCourseDto, courseBase);
        courseBase.setCompanyId(companyId);
        courseBase.setCreateDate(LocalDateTime.now());
        // 调用mapper向数据库中添加课程基本信息
        int saveCourseBaseResult = courseBaseMapper.insert(courseBase);
        if (saveCourseBaseResult <= 0) {
            XueChengPlusException.cast("添加课程基本信息失败！");
        }

        // 课程营销信息属性拷贝
        CourseMarket courseMarket = new CourseMarket();
        BeanUtils.copyProperties(addCourseDto, courseMarket);
        Long courseId = courseBase.getId();
        if (courseId == null) {
            XueChengPlusException.cast("courseId不存在");
        }
        courseMarket.setId(courseId);
        // 调用service服务添加课程营销信息
        int saveCourseMarketResult = saveCourseMarker(courseMarket);
        if (saveCourseMarketResult <= 0) {
            XueChengPlusException.cast("添加课程营销信息失败！");
        }
        return getCourseBaseInfoByCourseId(courseId);
    }

    @Override
    public CourseBaseInfoDto getCourseBaseInfoByCourseId(Long courseId) {
        // 先查询课程基本信息
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if (courseBase == null) {
            XueChengPlusException.cast("找不到课程基本信息！");
        }
        // 查询课程营销信息
        CourseMarket courseMarket = courseMarketService.getById(courseId);
        if (courseMarket == null) {
            XueChengPlusException.cast("找不到课程营销信息！");
        }

        // 填充响应结果
        CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
        BeanUtils.copyProperties(courseBase, courseBaseInfoDto);
        BeanUtils.copyProperties(courseMarket, courseBaseInfoDto);

        // 找到所属分类名字
        ArrayList<String> list = new ArrayList<>(2);
        list.add(courseBase.getMt());
        list.add(courseBase.getSt());
        List<CourseCategory> categoryList = courseCategoryService.listByIds(list);
        for (CourseCategory category : categoryList) {
            if (category.getId().equals(courseBase.getMt())) {
                courseBaseInfoDto.setMtName(category.getName());
            } else if (category.getId().equals(courseBase.getSt())) {
                courseBaseInfoDto.setStName(category.getName());
            }
        }
        return courseBaseInfoDto;
    }

    @Override
    public CourseBaseInfoDto editCourseBase(Long companyId, EditCourseDto dto) {
        // 校验课程是否存在
        CourseBase courseBase = courseBaseMapper.selectById(dto.getId());
        if (courseBase == null) {
            XueChengPlusException.cast("该课程不存在");
        }

        // 封装参数
        BeanUtils.copyProperties(dto, courseBase);
        // 设置修改时间
        courseBase.setCreateDate(LocalDateTime.now());
        // 调用修改
        int updateCourseBaseResult = courseBaseMapper.updateById(courseBase);

        // 封装课程营销信息
        CourseMarket courseMarket = new CourseMarket();
        BeanUtils.copyProperties(dto, courseMarket);

        // 请求数据库，对营销表，有则更新，无则修改
        int saveOrUpdateResult = saveCourseMarker(courseMarket);

        if (updateCourseBaseResult <= 0 || saveOrUpdateResult <= 0) {
            XueChengPlusException.cast("修改课程基本信息失败");
        }

        // 查询课程基本信息
        return getCourseBaseInfoByCourseId(dto.getId());
    }

    /**
     * 抽取对课程营销信息的保存
     *
     * @param courseMarket 课程营销信息
     * @return int
     */
    private int saveCourseMarker(CourseMarket courseMarket) {
        if (StringUtils.isBlank(courseMarket.getCharge())) {
            XueChengPlusException.cast("收费规则未选取");
        }
        // 如果课程收费标准是收费，而价格为空，抛出异常
        // 可优化，避免魔法值，将改代码值放置配置文件中，通过读取配置文件得到改值
        if ("201001".equals(courseMarket.getCharge())) {
            if (courseMarket.getPrice() == null || courseMarket.getPrice() <= 0) {
                XueChengPlusException.cast("课程为收费但价格为空！");
            }
        }
        boolean saveOrUpdateResult = courseMarketService.saveOrUpdate(courseMarket);
        return saveOrUpdateResult ? 1 : 0;
    }

}
