package top.itcat.xuecheng.content.model.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @className: QueryCourseParamsDto <br/>
 * @description: 查询课程参数Dto类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/02/03 <br/>
 * @version: 1.0.0 <br/>
 */
@Data
@ToString
public class QueryCourseParamsDto {

    private String auditStatus;

    private String courseName;

    private String publishStatus;

}
