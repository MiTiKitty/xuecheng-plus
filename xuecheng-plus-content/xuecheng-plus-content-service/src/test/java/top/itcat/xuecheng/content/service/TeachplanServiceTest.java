package top.itcat.xuecheng.content.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @className: TeachplanServiceTest <br/>
 * @description: TeachplanService测试类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/02/15 <br/>
 * @version: 1.0.0 <br/>
 */
@SpringBootTest
public class TeachplanServiceTest {

    @Autowired
    private TeachplanService teachplanService;

    @Test
    public void testMoveup() {
        long t1 = System.currentTimeMillis();
        teachplanService.move("moveup", 268L);
        long t2 = System.currentTimeMillis();
        System.out.println("总耗时(ms)  -----> " + (t2 - t1));
    }

    @Test
    public void testMovedown() {
        long t1 = System.currentTimeMillis();
        teachplanService.move("movedown", 268L);
        long t2 = System.currentTimeMillis();
        System.out.println("总耗时(ms)  -----> " + (t2 - t1));
    }

}
