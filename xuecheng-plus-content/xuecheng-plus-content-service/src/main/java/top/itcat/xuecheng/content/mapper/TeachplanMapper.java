package top.itcat.xuecheng.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.itcat.xuecheng.content.model.dto.TeachplanDto;
import top.itcat.xuecheng.content.model.po.Teachplan;

import java.util.List;

/**
 * <p>
 * 课程计划 Mapper 接口
 * </p>
 *
 * @author CatKitty
 */
public interface TeachplanMapper extends BaseMapper<Teachplan> {

    /**
     * 查询某课程的课程计划，组成树形结构
     * @param courseId 课程id
     * @return List<TeachplanDto>
     */
    List<TeachplanDto> selectTreeNodes(Long courseId);

}
