package per.guzx.priDiary;

import org.junit.runner.RunWith;
//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Administrator
 */
@SpringBootApplication
@MapperScan(basePackages = "per.guzx.priDiary.dao", annotationClass = Repository.class)
@EnableScheduling
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriDiaryApplication {

    public static void main(String[] args) {
        /*DocsConfig config = new DocsConfig();
        config.setProjectPath("src"); // 项目根目录
        config.setProjectName("pri_diary"); // 项目名称
        config.setApiVersion("V0.1");       // 声明该API的版本
        config.setDocsPath("src/api"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
        Docs.buildHtmlDocs(config); // 执行生成文档*/
        SpringApplication.run(PriDiaryApplication.class, args);
    }

   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder).
                withUser("user1").password(passwordEncoder.encode("123")).roles("USER").and().
                withUser("user2").password(passwordEncoder.encode("123")).roles("ADMIN").and().
                withUser("user3").password(passwordEncoder.encode("123")).roles("USER");
    }*/

}
