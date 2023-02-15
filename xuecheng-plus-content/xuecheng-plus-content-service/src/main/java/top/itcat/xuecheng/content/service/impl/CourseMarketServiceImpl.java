package top.itcat.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.itcat.xuecheng.base.exception.XueChengPlusException;
import top.itcat.xuecheng.content.mapper.CourseMarketMapper;
import top.itcat.xuecheng.content.model.po.CourseMarket;
import top.itcat.xuecheng.content.service.CourseMarketService;

/**
 * <p>
 * 课程营销信息 服务实现类
 * </p>
 *
 * @author CatKitty
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CourseMarketServiceImpl extends ServiceImpl<CourseMarketMapper, CourseMarket> implements CourseMarketService {

    @Autowired
    private CourseMarketMapper courseMarketMapper;

    @Override
    public void removeCourseMarketByCourseId(Long courseId) {
        LambdaQueryWrapper<CourseMarket> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CourseMarket::getId, courseId);
        int deleteResult = courseMarketMapper.delete(wrapper);
        if (deleteResult <= 0) {
            XueChengPlusException.cast("删除课程营销信息失败！");
        }

    }
}
