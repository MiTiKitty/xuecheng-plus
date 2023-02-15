package top.itcat.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import groovy.util.logging.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.itcat.xuecheng.base.exception.XueChengPlusException;
import top.itcat.xuecheng.content.mapper.TeachplanMapper;
import top.itcat.xuecheng.content.model.dto.SaveTeachplanDto;
import top.itcat.xuecheng.content.model.dto.TeachplanDto;
import top.itcat.xuecheng.content.model.po.Teachplan;
import top.itcat.xuecheng.content.service.TeachplanMediaService;
import top.itcat.xuecheng.content.service.TeachplanService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程计划 服务实现类
 * </p>
 *
 * @author CatKitty
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TeachplanServiceImpl extends ServiceImpl<TeachplanMapper, Teachplan> implements TeachplanService {

    @Autowired
    private TeachplanMapper teachplanMapper;

    @Autowired
    private TeachplanMediaService teachplanMediaService;

    @Override
    public List<TeachplanDto> queryTreeNodes(Long courseId) {
        return teachplanMapper.selectTreeNodes(courseId);
    }

    @Override
    public void saveOrUpdateTeachplan(SaveTeachplanDto dto) {
        Long id = dto.getId();
        if (id == null) {
            Teachplan teachplan = new Teachplan();
            BeanUtils.copyProperties(dto, teachplan);
            // 改进：当使用原来的计数给order排序值时，会出现删除计划导致再添加计划给予新的计划相同排序值
            // 这在查询和排序中都是相当不友好的行为，会徒增一些不必要的麻烦
            // 故使用查询该节最大排序值来给定新添加的计划排序值，保证有序性
            Integer count = teachplanMapper.selectMaxOrderBySameNode(dto.getCourseId(), dto.getParentid());
            // 改进，需要判断该节有无最大值，若无，则应该置为0
            if (count == null) {
                count = 0;
            }
            teachplan.setOrderby(count + 1);
            int insertResult = teachplanMapper.insert(teachplan);
            if (insertResult <= 0) {
                XueChengPlusException.cast("添加课程计划失败！");
            }
        }else {
            Teachplan teachplan = teachplanMapper.selectById(id);
            BeanUtils.copyProperties(dto,teachplan);
            int updateResult = teachplanMapper.updateById(teachplan);
            if (updateResult <= 0) {
                XueChengPlusException.cast("修改课程计划失败！");
            }
        }
    }

    @Override
    public long removeTeachplanById(Long id) {
        // 检查该级下是否还有小节
        int count = getTeachplanCount(null, id);
        if (count > 0) {
            XueChengPlusException.cast("课程计划信息还有子级信息，无法操作");
        }
        // 删除课程
        teachplanMapper.deleteById(id);
        teachplanMediaService.removeTeachplanMediaByTeachplanId(id);
        return id;
    }

    @Override
    public void move(String type, Long id) {
        int step = 0;
        // 校验type
        if ("moveup".equals(type)) {
            step = -1;
        } else if ("movedown".equals(type)) {
            step = 1;
        } else {
            XueChengPlusException.cast("无效参数！");
        }
        // 检查其是否可以更改序列
        // 获得要移动的课程
        Teachplan teachplan = teachplanMapper.selectById(id);
        LambdaQueryWrapper<Teachplan> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Teachplan::getCourseId, teachplan.getCourseId())
                .eq(Teachplan::getParentid, teachplan.getParentid())
                .eq(Teachplan::getOrderby, teachplan.getOrderby() + step);
        Teachplan otherTeachplan = teachplanMapper.selectOne(wrapper);
        // 若不满足移动的条件，则不做处理
        if (otherTeachplan == null) {
            return;
        }
        // 更新排序号
        teachplanMapper.updateOrderById(id, otherTeachplan.getId());
    }

    @Override
    public void removeTeachplanByCourseId(Long courseId) {
        LambdaQueryWrapper<Teachplan> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Teachplan::getCourseId, courseId);
        List<Teachplan> teachplanList = teachplanMapper.selectList(wrapper);
        teachplanMapper.delete(wrapper);
        List<Long> teachplanIdList = new ArrayList<>(teachplanList.size());
        teachplanList.forEach(item -> {
            teachplanIdList.add(item.getId());
        });
        teachplanMediaService.removeTeachplanMediaByTeachplanIds(teachplanIdList);
    }

    /**
     * 找到同级目录中，小节的数量
     * @param courseId 课程id
     * @param parentId 课程计划父级id
     * @return
     */
    private Integer getTeachplanCount(Long courseId, Long parentId) {
        LambdaQueryWrapper<Teachplan> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Teachplan::getCourseId, courseId)
                .eq(courseId != null, Teachplan::getParentid, parentId);
        return teachplanMapper.selectCount(wrapper);
    }

}
