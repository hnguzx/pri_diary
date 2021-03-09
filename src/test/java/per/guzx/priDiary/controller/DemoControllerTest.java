package per.guzx.priDiary.controller;

import com.alibaba.fastjson.JSON;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.results.ResultMatchers;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import per.guzx.priDiary.pojo.PdBlog;
import per.guzx.priDiary.pojo.PdUser;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Principal;
import java.util.Objects;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/3/8 10:19
 * @describe
 */
@SpringBootTest
@RunWith(SpringRunner.class)
//@Transactional
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
     *
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
    public void uploadFile() throws Exception {
        // 请求地址
        String reqUrl = "/diary/uploadImg";
        // 文件地址
        String fileUrl = "F:\\Files\\123.png";
        // 真实文件
        File file = new File(fileUrl);
        InputStream inputStream = new FileInputStream(fileUrl);
        // 模拟文件
        MockMultipartFile mockMultipartFile = new MockMultipartFile("diaryPhoto", "123.png", MediaType.IMAGE_PNG_VALUE, inputStream);
        // 构建请求
        MockMultipartHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.multipart(reqUrl).file(mockMultipartFile);
        // 发送请求并对请求结果进行处理
        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addControllerTest() throws Exception {
        // 构建实体
        PdBlog blog = new PdBlog();
        blog.setUserId(2);
        blog.setBlogImage("imageUrl");
        blog.setBlogLabel(1);
        blog.setBlogType(2);
        blog.setBlogContext("你好");
        String jsonString = JSON.toJSONString(blog);

        // 请求地址
        String reqUrl = "/blog/add";
        // 通过RequestBuilder构建请求，配置请求头，session，请求参数等
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(reqUrl);

        MvcResult result = setCommonParam(requestBuilder, jsonString);

        // 获取response数据
        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        long code = (Integer) jsonObject.get("code");

        // 使用断言验证
        Assert.assertEquals(200, code);
    }

    @Test
    public void queryOneControllerTest() throws Exception {
        // 请求地址
        String reqUrl = "/blog/10000";
        // 通过RequestBuilder构建请求，配置请求头，session，请求参数等
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(reqUrl);

        MvcResult result = setCommonParam(requestBuilder);

        // 获取response数据
        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        JSONObject data = (JSONObject) jsonObject.get("data");
        long code = (Integer) jsonObject.get("code");
        // 使用断言验证
        Assert.assertEquals(200, code);
        Assert.assertNotNull(data.get("blogContext"));
    }

    @Test
    public void queryManyControllerTest() throws Exception {
        // 请求地址
        String reqUrl = "/blog/list";
        // 通过RequestBuilder构建请求，配置请求头，session，请求参数等
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(reqUrl);
        requestBuilder.param("page", "1");
        requestBuilder.param("size", "2");

        MvcResult result = setCommonParam(requestBuilder);

        // 获取response数据
        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        // 获取data
        JSONObject data = (JSONObject) jsonObject.get("data");
        // 获取list
        JSONArray jsonArray = (JSONArray) data.get("list");
        JSONObject jsonObject_data = null;
        if (jsonArray.length() > 0) {
            jsonObject_data = (JSONObject) jsonArray.get(0);
        }

        long code = (Integer) jsonObject.get("code");
        // 使用断言验证
        Assert.assertEquals(200, code);
        Assert.assertNotNull(jsonObject_data.get("blogContext"));
    }

    @Test
    public void updateControllerTest() throws Exception {
        // 构建实体
        PdBlog blog = new PdBlog();
        blog.setBlogId(2);
        blog.setUserId(2);
        blog.setBlogImage("imageUrl");
        blog.setBlogLabel(1);
        blog.setBlogType(2);
        blog.setBlogContext("你好");
        String jsonString = JSON.toJSONString(blog);

        // 请求地址
        String reqUrl = "/blog/update";
        // 通过RequestBuilder构建请求，配置请求头，session，请求参数等
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put(reqUrl);

        MvcResult result = setCommonParam(requestBuilder, jsonString);

        // 获取response数据
        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        long code = (Integer) jsonObject.get("code");

        // 使用断言验证
        Assert.assertEquals(200, code);
    }

    @Test
    public void deleteControllerTest() throws Exception {

        // 请求地址
        String reqUrl = "/blog/4";
        // 通过RequestBuilder构建请求，配置请求头，session，请求参数等
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete(reqUrl);

        MvcResult result = setCommonParam(requestBuilder);

        // 获取response数据
        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        long code = (Integer) jsonObject.get("code");
        // 使用断言验证
        Assert.assertEquals(200, code);
    }

    public MvcResult setCommonParam(MockHttpServletRequestBuilder requestBuilder) throws Exception {
        return this.setCommonParam(requestBuilder, null);
    }

    public MvcResult setCommonParam(MockHttpServletRequestBuilder requestBuilder, String content) throws Exception {
        requestBuilder.accept(MediaType.APPLICATION_JSON);
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        requestBuilder.session(mockHttpSession);
        if (!Objects.isNull(content)) {
            requestBuilder.content(content);
        }
        // 请求结果
        ResultActions resultActions = mockMvc.perform(requestBuilder);
        // 对请求结果进行异常捕获
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        // 对返回结果进行处理
        resultActions.andDo(MockMvcResultHandlers.print());
        // 获取放回结果
        MvcResult result = resultActions.andReturn();
        return result;
    }
}
