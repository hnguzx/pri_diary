package per.guzx.pri_diary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@MapperScan(basePackages = "per.guzx.pri_diary.dao", annotationClass = Repository.class)
public class PriDiaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(PriDiaryApplication.class, args);
    }

}
