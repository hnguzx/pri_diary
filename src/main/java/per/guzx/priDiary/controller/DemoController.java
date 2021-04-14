package per.guzx.priDiary.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import per.guzx.priDiary.dao.PdMessageDao;
import per.guzx.priDiary.pojo.ApiResp;
import per.guzx.priDiary.pojo.PdDiary;
import per.guzx.priDiary.pojo.PdMessage;
import per.guzx.priDiary.utils.CommonUtil;
import per.guzx.priDiary.utils.DateUtil;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试连通性接口
 *
 * @author Administrator
 */
@RestController
@Slf4j
@RequestMapping("/demo")
@Api(tags = "测试增删改查")
@SuppressWarnings("unchecked")
public class DemoController {

    @Resource
    private PdMessageDao messageDao;

    @Resource
    private DateUtil dateUtil;

    @Resource
    private CommonUtil commonUtil;

    @PostMapping("/add/{insertMethod}")
    public ApiResp add(@PathVariable("insertMethod") Integer insertMethod) {
        int result;
        PdMessage message = new PdMessage();
        message.setMsgSender(1);
        message.setMsgReceiver(2);
        message.setMsgIsReaded(false);
        message.setMsgContent("测试自增");
        message.setMsgCreateTime(dateUtil.getTimeStamp());
        switch (insertMethod) {
            case 1:
                result = messageDao.insert(message);
                break;
            case 2:
                result = messageDao.insertSelective(message);
                break;
            default:
                result = 0;
                break;
        }
        return ApiResp.retOk(result);
    }

    @DeleteMapping("/delete/{deleteMethod}")
    public ApiResp delete(@PathVariable("deleteMethod") int deleteMethod) {
        int result;
        Example example = new Example(PdMessage.class);
        PdMessage message = new PdMessage();
//        message.setMsgId(5);
        switch (deleteMethod) {
            case 1:
                result = messageDao.delete(message);
                break;
            case 2:
                result = messageDao.deleteByPrimaryKey(6);
                break;
            case 3:
                Example.Criteria criteria = example.createCriteria();
                criteria.orLike("msgContent", "%消%");
                result = messageDao.deleteByExample(example);
                break;
            default:
                result = 0;
                break;
        }
        return ApiResp.retOk(result);
    }

    @PostMapping("/update/{updateMethod}")
    public ApiResp update(@PathVariable("updateMethod") int updateMethod) {
        int result;
        Example example = new Example(PdMessage.class);
        PdMessage message = new PdMessage();
//        message.setMsgId(10);
        message.setMsgIsReaded(true);
        message.setMsgSender(111);
        message.setMsgContent("你好啊");
        switch (updateMethod) {
            case 1:
                result = messageDao.updateByPrimaryKeySelective(message);
                break;
            case 2:
                result = messageDao.updateByPrimaryKey(message);
                break;
            case 3:
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("msgId", "10");
                criteria.andLike("msgContent", "%nihao%");
                result = messageDao.updateByExample(message, example);
                break;
            case 4:
                Example.Criteria criteria2 = example.createCriteria();
                criteria2.andEqualTo("msgId", "10");
                criteria2.andLike("msgContent", "%nihao%");
                result = messageDao.updateByExampleSelective(message, example);
                break;
            default:
                result = 0;
                break;
        }
        return ApiResp.retOk(result);
    }

    @GetMapping("/query/{queryMethod}")
    public ApiResp query(@PathVariable("queryMethod") int queryMethod) {
        List results = new ArrayList<PdMessage>();
        PdMessage result = new PdMessage();
        PageInfo pageInfo = null;

        PdMessage message = new PdMessage();
//        message.setMsgSender(1);
        message.setMsgReceiver(2);
        Example example = new Example(PdMessage.class);

        switch (queryMethod) {
            case 1:
                result = messageDao.selectOne(message);
                break;
            case 2:
                result = messageDao.selectByPrimaryKey(10018);
                break;
            case 3:
                Example.Criteria criteria = example.createCriteria();
                criteria.orLike("msgContent", "%好%");
                result = messageDao.selectOneByExample(example);
                break;
            case 4:
                results = messageDao.select(message);
                break;
            case 5:
                PageHelper.startPage(1, 1);
                results = messageDao.selectAll();
                pageInfo = new PageInfo(results);
                break;
            case 6:
                PageHelper.startPage(1, 1);
                example = new Example(PdMessage.class);
                Example.Criteria criteria2 = example.createCriteria();
                criteria2.orLike("msgContent", "%好%");
                results = messageDao.selectByExample(example);
                pageInfo = new PageInfo(results);
                break;
            case 7:
                example = new Example(PdMessage.class);
                Example.Criteria criteria3 = example.createCriteria();
                criteria3.orLike("msgContent", "%好%");
                // offset从0开始
                RowBounds rowBounds = new RowBounds(1, 1);
                results = messageDao.selectByExampleAndRowBounds(example, rowBounds);
                break;
            case 8:
                RowBounds rowBounds2 = new RowBounds(0, 2);
                results = messageDao.selectByRowBounds(message, rowBounds2);
                break;
            default:
                break;
        }
//        return ApiResp.retOk(result);
        return ApiResp.retOk(results);
//        return ApiResp.retOk(pageInfo);
    }

    @PostMapping(value = "/upload", headers = "content-type=multipart/form-data")
    public ApiResp uploadFileAndJson(
            @RequestPart(value = "diary") PdDiary diary,
            @RequestParam(value = "randomString") String randomString,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        System.out.println(randomString);
        System.out.println(diary.getDiaryContent());
        System.out.println(file.getOriginalFilename());
        return ApiResp.retOk();
    }


    @GetMapping("/common")
    public String common() {
        return "common";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
