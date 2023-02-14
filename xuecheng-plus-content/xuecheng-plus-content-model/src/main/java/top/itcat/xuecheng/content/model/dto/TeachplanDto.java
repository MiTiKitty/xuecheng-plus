package top.itcat.xuecheng.content.model.dto;

import lombok.Data;
import top.itcat.xuecheng.content.model.po.Teachplan;
import top.itcat.xuecheng.content.model.po.TeachplanMedia;

import java.util.List;

/**
 * @className: TeachplanDto <br/>
 * @description: 课程计划dto类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/02/14 <br/>
 * @version: 1.0.0 <br/>
 */
@Data
public class TeachplanDto extends Teachplan {

    /**
     * 关联的媒资信息
     */
    TeachplanMedia teachplanMedia;

    /**
     * 子目录
     */
    List<TeachplanDto> teachPlanTreeNodes;

}
