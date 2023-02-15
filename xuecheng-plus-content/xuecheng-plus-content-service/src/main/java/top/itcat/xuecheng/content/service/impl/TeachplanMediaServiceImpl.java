package top.itcat.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.itcat.xuecheng.base.exception.XueChengPlusException;
import top.itcat.xuecheng.content.mapper.TeachplanMediaMapper;
import top.itcat.xuecheng.content.model.po.TeachplanMedia;
import top.itcat.xuecheng.content.service.TeachplanMediaService;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CatKitty
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TeachplanMediaServiceImpl extends ServiceImpl<TeachplanMediaMapper, TeachplanMedia> implements TeachplanMediaService {

    @Autowired
    private TeachplanMediaMapper teachplanMediaMapper;

    @Override
    public int removeTeachplanMediaByTeachplanId(Long teachplanId) {
        LambdaQueryWrapper<TeachplanMedia> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TeachplanMedia::getTeachplanId, teachplanId);
        return teachplanMediaMapper.delete(wrapper);
    }

    @Override
    public void removeTeachplanMediaByTeachplanIds(List<Long> teachplanIdList) {
        LambdaQueryWrapper<TeachplanMedia> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(TeachplanMedia::getTeachplanId, teachplanIdList);
        teachplanMediaMapper.delete(wrapper);
    }
}
