package top.itcat.xuecheng.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 找出同级别下的最大order排序值
     * @param courseId 课程id
     * @param parentId 课程计划父id
     * @return
     */
    Integer selectMaxOrderBySameNode(@Param("courseId") Long courseId, @Param("parentId") Long parentId);

    /**
     * 更新课程计划的排序号
     * @param id        第一个进行交换order的课程计划id
     * @param orderId   第二个进行交换order的课程计划id
     * @return
     */
    int updateOrderById(@Param("id") Long id,
                        @Param("orderId") Long orderId);

}
