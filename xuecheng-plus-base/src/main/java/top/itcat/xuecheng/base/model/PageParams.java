package top.itcat.xuecheng.base.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @className: PageParams <br/>
 * @description: 公共分页参数类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/02/03 <br/>
 * @version: 1.0.0 <br/>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageParams {

    public static final long DEFAULT_PAGE_CURRENT = 1L;

    public static final long DEFAULT_PAGE_SIZE = 10L;

    @ApiModelProperty("当前页码")
    private Long pageNo = DEFAULT_PAGE_CURRENT;

    @ApiModelProperty("每页记录数")
    private Long pageSize = DEFAULT_PAGE_SIZE;

}
