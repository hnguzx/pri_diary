package per.guzx.priDiary.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import per.guzx.priDiary.pojo.PdUser;

import javax.annotation.Resource;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/3/8 10:19
 * @describe
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class DemoControllerTest {

    @Resource
    private WebApplicationContext context;

    private MockMvc mockMvc;
    private MockHttpSession mockHttpSession;

    @Before
    public void setupMocMvc() {
        // 初始化mockMvc对象
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        mockHttpSession = new MockHttpSession();
        //如果拦截器那边会判断用户是否登录，这里注入一个用户
//        PdUser user = new PdUser();
//        mockHttpSession.setAttribute("user",user);
    }

    @Test
    public void controllerTest() throws Exception {
        String json = "{\"author\":\"guzx\",\"title\":\"\",\"url\":\"http://localhost/\"}";
        mockMvc.perform(MockMvcRequestBuilders.get("/demo/common")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(json.getBytes())
                .session(mockHttpSession)
        ).andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("author").value("guzx"))
                .andDo(MockMvcResultHandlers.print());
    }
}
