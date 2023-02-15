package top.itcat.xuecheng.content.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @className: TeachplanMapperTest <br/>
 * @description: TeachplanMapper测试类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/02/15 <br/>
 * @version: 1.0.0 <br/>
 */
@SpringBootTest
public class TeachplanMapperTest {

    @Autowired
    private TeachplanMapper teachplanMapper;

    @Test
    public void testSelectMaxOrderBySameNode() {
        Integer order = teachplanMapper.selectMaxOrderBySameNode(117L, 0L);
        System.out.println("The max order is -----> " + order);
    }

    @Test
    public void testUpdateOrderById() {
        int result = teachplanMapper.updateOrderById(277L, 278L);
        System.out.println("result is -----> " + result);
    }

}
