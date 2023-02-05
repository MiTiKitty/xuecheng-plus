package top.itcat.xuecheng.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.itcat.xuecheng.system.mapper.DictionaryMapper;
import top.itcat.xuecheng.system.model.po.Dictionary;
import top.itcat.xuecheng.system.service.DictionaryService;

import java.util.List;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author itcast
 */
@Slf4j
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Override
    public List<Dictionary> queryAll() {
        return dictionaryMapper.selectList(null);
    }
}
