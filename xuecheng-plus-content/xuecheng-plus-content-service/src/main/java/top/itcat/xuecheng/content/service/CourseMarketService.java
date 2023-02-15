package top.itcat.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.itcat.xuecheng.content.model.po.CourseMarket;

/**
 * <p>
 * 课程营销信息 服务类
 * </p>
 *
 * @author CatKitty
 * @since 2023-02-01
 */
public interface CourseMarketService extends IService<CourseMarket> {

    /**
     * 根据课程id删除该课程的所有营销信息
     * @param courseId 课程id
     */
    void removeCourseMarketByCourseId(Long courseId);
}
