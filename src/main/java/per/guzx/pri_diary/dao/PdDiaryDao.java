package per.guzx.pri_diary.dao;

import org.springframework.stereotype.Repository;
import per.guzx.pri_diary.pojo.PdDiary;

import java.util.List;
import java.util.Map;

@Repository
public interface PdDiaryDao {
    /**
     * 新增日记
     *
     * @param diary
     * @return
     */
    int insertDiary(PdDiary diary);

    /**
     * 更新日记
     *
     * @param diary
     * @return
     */
    int updateDiary(PdDiary diary);

    /**
     * 删除指定用户的指定日记
     *
     * @param userId
     * @param diaryId
     * @return
     */
    int deleteDiary(int userId, int diaryId);

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
     * 获取用户的所有日记
     *
     * @return
     */
    List<PdDiary> findDiaryAll(int userId);

    /**
     * 根据关键字分页查询日记
     *
     * @param userId
     * @param global
     * @param start
     * @param size
     * @return
     */
    List<PdDiary> findDiaryByGlobal(int userId, String global, int start, int size);

    /**
     * 获取指定用户的所有日记
     *
     * @return
     */
    List<PdDiary> findDiaryByUserId(int userId);

    /**
     * 获取指定用户的日记数量和总字数以及记录的天数
     *
     * @param userId
     * @return
     */
    Map<String, Integer> getDiaryCountAndDiaryDay(int userId);

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
    int getDiaryAndAddress(int userId);

    List<Map<String, String>> getWeatherTimes(int userId);

    List<Map<String, String>> getMoodTimes(int userId);

    List<Map<String, String>> getEventTimes(int userId);
}
