package top.itcat.xuecheng.content.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @className: EditCourseDto <br/>
 * @description: 修改课程模型类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/02/13 <br/>
 * @version: 1.0.0 <br/>
 */
@Data
public class EditCourseDto extends AddCourseDto {

    @NotEmpty(message = "课程id不能为空")
    @ApiModelProperty(value = "课程id", required = true)
    private Long id;

}
