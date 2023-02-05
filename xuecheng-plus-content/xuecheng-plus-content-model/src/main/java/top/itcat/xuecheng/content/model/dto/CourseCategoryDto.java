package top.itcat.xuecheng.content.model.dto;

import lombok.Data;
import top.itcat.xuecheng.content.model.po.CourseCategory;

import java.util.List;

/**
 * @className: CourseCategoryDto <br/>
 * @description:  CourseCategoryDto <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/02/04 <br/>
 * @version: 1.0.0 <br/>
 */
@Data
public class CourseCategoryDto extends CourseCategory {

    private List<CourseCategoryDto> childTreeNodes;

}
