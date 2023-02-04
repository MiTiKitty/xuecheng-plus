package top.itcat.xuecheng.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @className: PageResult <br/>
 * @description: 公用分页查询结果类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/02/03 <br/>
 * @version: 1.0.0 <br/>
 */
@Data
@AllArgsConstructor
@ToString
public class PageResult<T> implements Serializable {

    private List<T> items;

    private long counts;

    private long page;

    private long pageSize;

}
