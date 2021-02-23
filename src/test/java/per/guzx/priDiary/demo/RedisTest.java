package per.guzx.priDiary.demo;

import org.springframework.data.redis.core.RedisTemplate;

public class RedisTest {

    public static void main(String[] args) {
        RedisTemplate template = new RedisTemplate();
        template.opsForValue().set("key","value",100);

    }
}
