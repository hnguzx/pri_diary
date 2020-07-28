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
     * @param userId
     * @param diaryId
     * @return
     */
    int deleteDiary(int userId, int diaryId);

    /**
     * 获取指定用户的指定日记
     * @param userId
     * @param diary
     * @return
     */
    PdDiary findDiaryById(int userId,int diary);

    /**
     * 获取用户的所有日记
     *
     * @return
     */
    List<PdDiary> findDiaryAll(int userId);

    /**
     * 根据关键字查询日记
     *
     * @param userId
     * @param global
     * @return
     */
    List<PdDiary> findDiaryByGlobal(int userId, String global);
}
