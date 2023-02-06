package top.itcat.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import groovy.util.logging.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.itcat.xuecheng.content.mapper.CourseCategoryMapper;
import top.itcat.xuecheng.content.model.dto.CourseCategoryDto;
import top.itcat.xuecheng.content.model.po.CourseCategory;
import top.itcat.xuecheng.content.service.CourseCategoryService;

import java.util.*;

/**
 * <p>
 * 课程分类 服务实现类
 * </p>
 *
 * @author CatKitty
 */
@Slf4j
@Service
public class CourseCategoryServiceImpl extends ServiceImpl<CourseCategoryMapper, CourseCategory> implements CourseCategoryService {

    @Autowired
    private CourseCategoryMapper courseCategoryMapper;

    @Override
    public List<CourseCategoryDto> queryTreeNodes(String rootId) {
        LambdaQueryWrapper<CourseCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseCategory::getIsShow, "1").orderByAsc(CourseCategory::getId);
        List<CourseCategory> list = courseCategoryMapper.selectList(queryWrapper);
        return queryTreeNodes(list, rootId);
    }

    private List<CourseCategoryDto> queryTreeNodes(List<CourseCategory> list, String rootId) {
        List<CourseCategoryDto> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            CourseCategory category = list.get(i);
            if (category.getId().equals(rootId)) {
                CourseCategoryDto root = new CourseCategoryDto();
                BeanUtils.copyProperties(category, root);
                result.add(root);
                queryTreeNodes(root, list);
                break;
            }
        }
        return result;
    }

    private void queryTreeNodes(CourseCategoryDto root, List<CourseCategory> list) {
        for (int i = 0; i < list.size(); i++) {
            CourseCategory category = list.get(i);
            if (category.getParentid().equals(root.getId())) {
                if (root.getChildTreeNodes() == null) {
                    root.setChildTreeNodes(new LinkedList<>());
                }
                CourseCategoryDto item = new CourseCategoryDto();
                BeanUtils.copyProperties(category, item);
                root.getChildTreeNodes().add(item);
            }
        }
        if (root.getChildTreeNodes() != null) {
            for (CourseCategoryDto treeNode : root.getChildTreeNodes()) {
                queryTreeNodes(treeNode, list);
            }
        }
    }

}
