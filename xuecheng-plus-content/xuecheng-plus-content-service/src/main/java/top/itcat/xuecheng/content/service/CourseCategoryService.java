package top.itcat.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.itcat.xuecheng.content.model.dto.CourseCategoryDto;
import top.itcat.xuecheng.content.model.po.CourseCategory;

import java.util.List;

/**
 * <p>
 * 课程分类 服务类
 * </p>
 *
 * @author CatKitty
 * @since 2023-02-04
 */
public interface CourseCategoryService extends IService<CourseCategory> {

    /**
     * 查询所有课程分类
     * @return
     */
    List<CourseCategoryDto> queryTreeNodes();
}
