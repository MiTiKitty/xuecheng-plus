package top.itcat.xuecheng.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.itcat.xuecheng.system.model.po.Dictionary;

import java.util.List;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author itcast
 * @since 2023-02-05
 */
public interface DictionaryService extends IService<Dictionary> {

    /**
     * 查询所有数据字典中的数据
     * @return List<Dictionary>
     */
    List<Dictionary> queryAll();
}
