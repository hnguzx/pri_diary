package per.guzx.priDiary.service;

import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;
import per.guzx.priDiary.pojo.PdDiary;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public interface PdDiaryService {

    /**
     * 新增日记
     *
     * @param diary
     * @return
     */
    int insertDiary(PdDiary diary, MultipartFile detailPhoto);

    /**
     * 更新日记
     *
     * @param diary
     * @return
     */
    int updateDiary(PdDiary diary, MultipartFile detailPhoto);

    /**
     * 删除日记
     *
     * @param diaryId
     * @return
     */
    int deleteDiary(int diaryId, int userId);

    /**
     * 获取指定用户的指定日记
     *
     * @param userId
     * @param diaryId
     * @return
     */
    PdDiary findDiaryById(int userId, int diaryId);

    /**
     * 获取日记其它信息（除了创建时间，更新时间，封面图片，主要内容）
     *
     * @param userId
     * @param diaryId
     * @return
     */
    PdDiary findDiaryOtherById(int userId, int diaryId);

    /**
     * 获取指定用户的所有日记
     *
     * @param userId
     * @return
     */
    List<PdDiary> findDiaryAll(int userId);

    /**
     * 根据关键字分页查询日记
     * @param userId
     * @param global
     * @param start
     * @param size
     * @return
     */
    PageInfo<List<PdDiary>> findDiaryByGlobal(int userId, String global, int start, int size);

    /**
     * 获取我的日记相关统计数字
     * @param userId
     * @return
     */
    Map<String,Object> getDiaryNumber(int userId);

    /**
     * 获取日记图片信息
     * @param userId
     * @return
     */
    Map<String,Object> getImageInfo(int userId);

    /**
     * 获取日记地址信息
     * @param userId
     * @return
     */
    Map<String,Object> getAddressInfo(int userId);

    /**
     * 获取日记相关标签信息
     * @param userId
     * @return
     */
    Map<String ,Object> getDiaryLabelInfo(int userId);
}
