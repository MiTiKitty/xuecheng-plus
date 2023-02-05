package top.itcat.xuecheng.system.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.itcat.xuecheng.system.model.po.Dictionary;
import top.itcat.xuecheng.system.service.DictionaryService;

import java.util.List;

/**
 * @className: DictionaryController <br/>
 * @description: 数据字典接口类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/02/05 <br/>
 * @version: 1.0.0 <br/>
 */
@Api(value = "数据字典接口", tags = "数据字典接口")
@CrossOrigin
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @ApiOperation("查询所有数据字典中的数据")
    @GetMapping("/all")
    public List<Dictionary> queryAll() {
        return dictionaryService.queryAll();
    }

}
