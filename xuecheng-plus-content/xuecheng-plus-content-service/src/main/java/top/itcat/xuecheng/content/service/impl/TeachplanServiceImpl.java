package top.itcat.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import groovy.util.logging.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.itcat.xuecheng.base.exception.XueChengPlusException;
import top.itcat.xuecheng.content.mapper.TeachplanMapper;
import top.itcat.xuecheng.content.model.dto.SaveTeachplanDto;
import top.itcat.xuecheng.content.model.dto.TeachplanDto;
import top.itcat.xuecheng.content.model.po.Teachplan;
import top.itcat.xuecheng.content.service.TeachplanService;

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
public class TeachplanServiceImpl extends ServiceImpl<TeachplanMapper, Teachplan> implements TeachplanService {

    @Autowired
    private TeachplanMapper teachplanMapper;

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
            int count = getTeachplanCount(dto.getCourseId(), dto.getCourseId());
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

    /**
     * 找到同级目录中，小节的数量
     * @param courseId 课程id
     * @param parentId 课程计划父级id
     * @return
     */
    private int getTeachplanCount(Long courseId, Long parentId) {
        LambdaQueryWrapper<Teachplan> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Teachplan::getCourseId, courseId).eq(Teachplan::getParentid, parentId);
        return teachplanMapper.selectCount(wrapper);
    }
}
