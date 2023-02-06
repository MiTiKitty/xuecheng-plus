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
import top.itcat.xuecheng.base.model.PageParams;
import top.itcat.xuecheng.base.model.PageResult;
import top.itcat.xuecheng.content.mapper.CourseBaseMapper;
import top.itcat.xuecheng.content.model.dto.AddCourseDto;
import top.itcat.xuecheng.content.model.dto.CourseBaseInfoDto;
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
        // TODO 参数合法性校验

        // 如果课程收费标准是收费，而价格为空，抛出异常
        // 可优化，避免魔法值，将改代码值放置配置文件中，通过读取配置文件得到改值
        if ("201001".equals(addCourseDto.getCharge())) {
            if (addCourseDto.getPrice() == null || addCourseDto.getPrice().floatValue() <= 0) {
                throw new RuntimeException("课程为收费但价格为空！");
            }
        }

        // 课程基本信息属性拷贝
        CourseBase courseBase = new CourseBase();
        BeanUtils.copyProperties(addCourseDto, courseBase);
        courseBase.setCompanyId(companyId);
        courseBase.setCreateDate(LocalDateTime.now());
        // 调用mapper向数据库中添加课程基本信息
        int saveCourseBaseResult = courseBaseMapper.insert(courseBase);
        if (saveCourseBaseResult <= 0) {
            throw new RuntimeException("添加课程基本信息失败！");
        }

        // 课程营销信息属性拷贝
        CourseMarket courseMarket = new CourseMarket();
        BeanUtils.copyProperties(addCourseDto, courseMarket);
        Long courseId = courseBase.getId();
        if (courseId == null) {
            throw new RuntimeException("courseId不存在");
        }
        courseMarket.setId(courseId);
        // 调用service服务添加课程营销信息
        boolean saveCourseMarketResult = courseMarketService.save(courseMarket);
        if (!saveCourseMarketResult) {
            throw new RuntimeException("添加课程营销信息失败！");
        }
        return getCourseBaseInfoResult(courseId);
    }

    /**
     * 组装CourseBaseInfoDTO
     * @param courseId 课程id
     * @return CourseBaseInfoDto
     */
    private CourseBaseInfoDto getCourseBaseInfoResult(Long courseId) {
        // 先查询课程基本信息
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if (courseBase == null) {
            throw new RuntimeException("找不到课程基本信息！");
        }
        // 查询课程营销信息
        CourseMarket courseMarket = courseMarketService.getById(courseId);
        if (courseMarket == null) {
            throw new RuntimeException("找不到课程营销信息！");
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
            }else if (category.getId().equals(courseBase.getSt())) {
                courseBaseInfoDto.setStName(category.getName());
            }
        }
        return courseBaseInfoDto;
    }

}
