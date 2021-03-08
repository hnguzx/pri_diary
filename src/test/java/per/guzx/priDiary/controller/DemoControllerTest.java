package per.guzx.priDiary.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
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

    /**
     * 测试连通性
     * @throws Exception
     */
    @Test
    public void demoControllerTest() throws Exception {
        String json = "{\"author\":\"guzx\",\"title\":\"\",\"url\":\"http://localhost/\"}";
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/demo/common")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(json.getBytes())
                .session(mockHttpSession)
        ).andExpect(MockMvcResultMatchers.status().isOk()) // 结果校验
                .andDo(MockMvcResultHandlers.print()); // 结果处理器
        System.out.println(resultActions);
    }

    @Test
    public void addControllerTest() throws Exception{
//        String json = "";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/demo/query/4")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
//                .param("url","http://localhost/")
//        .content(json.getBytes())
        .session(mockHttpSession))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
        .andReturn();

        // 获取response数据
        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        JSONArray jsonArray = (JSONArray) jsonObject.get("data");

        // 获取data数据
        JSONObject jsonObject_data = null;
        if (jsonArray.length()>0){
            jsonObject_data = (JSONObject) jsonArray.get(0);
        }

        // 使用断言验证
        Assert.assertNotNull(jsonObject_data.get("msgCreateTime"));

    }
}
