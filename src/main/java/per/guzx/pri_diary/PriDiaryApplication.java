package per.guzx.pri_diary;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
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
        DocsConfig config = new DocsConfig();
        config.setProjectPath("src"); // 项目根目录
        config.setProjectName("pri_diary"); // 项目名称
        config.setApiVersion("V0.1");       // 声明该API的版本
        config.setDocsPath("src/main/resources/api"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
        Docs.buildHtmlDocs(config); // 执行生成文档
        SpringApplication.run(PriDiaryApplication.class, args);
    }

}
