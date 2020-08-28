package per.guzx.pri_diary.dao;

import org.springframework.stereotype.Repository;
import per.guzx.pri_diary.pojo.PdDiary;

import java.util.List;

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
}
