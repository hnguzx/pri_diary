package per.guzx.priDiary.dao;

import org.springframework.stereotype.Repository;
import per.guzx.priDiary.pojo.PdDiary;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Repository
public interface PdDiaryDao extends Mapper<PdDiary> {

    PdDiary findDiaryById(int userId, int diaryId);

    /**
     * 获取日记其它信息（除了创建时间，更新时间，封面图片，主要内容）
     *
     * @param userId
     * @param diaryId
     * @return
     */
    PdDiary findDiaryOtherById(int userId, int diaryId);

    List<PdDiary> findDiaryByGlobal(int userId, String global, int start, int size);

    List<PdDiary> findDiaryByUserId(int userId);

    /**
     * 获取指定用户的日记数量和总字数以及记录的天数
     *
     * @param userId
     * @return
     */
    Map<String, Object> getDiaryCountAndDiaryDay(int userId);

    /**
     * 获取指定用户一周内的日记数量
     *
     * @param userId
     * @param beginDate
     * @param endDate
     * @return
     */
    int getWeekDiaryCount(int userId, String beginDate, String endDate);

    /**
     * 获取指定用户的所有日记标签
     *
     * @param userId
     * @return
     */
    List<Map<String, String>> getDiaryOther(int userId);

    /**
     * 获取有定位的日记数量
     *
     * @return
     */
    int getDiaryAndAddressCount(int userId);

    /**
     * 获取天气标签相关数据
     * @param userId
     * @return
     */
    List<Map<String, String>> getWeatherTimes(int userId);

    /**
     * 获取心情标签相关数据
     * @param userId
     * @return
     */
    List<Map<String, String>> getMoodTimes(int userId);

    /**
     * 获取事件标签相关数据
     * @param userId
     * @return
     */
    List<Map<String, String>> getEventTimes(int userId);

    /**
     * 获取地址相关数据
     * @param userId
     * @return
     */
    List<Map<String, String>> getDiaryAddressInfo(int userId);

    /**
     * 获取图片相关数据
     * @param userId
     * @return
     */
    List<Map<String, String>> getDiaryImgInfo(int userId);
}
