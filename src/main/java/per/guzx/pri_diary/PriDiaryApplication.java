package per.guzx.pri_diary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@SpringBootApplication
@MapperScan(basePackages = "per.guzx.pri_diary.dao", annotationClass = Repository.class)
@EnableScheduling
public class PriDiaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(PriDiaryApplication.class, args);
    }

}
