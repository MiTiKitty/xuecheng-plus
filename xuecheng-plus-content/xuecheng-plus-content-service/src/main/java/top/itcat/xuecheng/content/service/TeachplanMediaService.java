package top.itcat.xuecheng.content.service;


import com.baomidou.mybatisplus.extension.service.IService;
import top.itcat.xuecheng.content.model.po.TeachplanMedia;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CatKitty
 * @since 2023-02-01
 */
public interface TeachplanMediaService extends IService<TeachplanMedia> {

    /**
     * 根据课程计划id删除课程计划-视频关系记录
     * @param teachplanId 课程计划id
     * @return  操作影响记录条数
     */
    int removeTeachplanMediaByTeachplanId(Long teachplanId);

    /**
     * 根据课程计划id集合删除课程计划-视频关系记录
     *
     * @param teachplanIdList 课程计划id集合
     */
    void removeTeachplanMediaByTeachplanIds(List<Long> teachplanIdList);
}
