package per.guzx.priDiary.tools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import per.guzx.priDiary.tool.AddressUtil;

import javax.annotation.Resource;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/3/5 17:40
 * @describe
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressUtilTest {

    @Test
    public void test(){
        System.out.println("123");
    }

    @Resource
    private AddressUtil addressUtil;

    @Test
    public void getInnerIp(){
        String ip = addressUtil.getInnerIp();
        System.out.println(ip);
    }
}
