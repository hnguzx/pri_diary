package per.guzx.pri_diary.service;

import per.guzx.pri_diary.pojo.PdDiary;

import java.util.List;

public interface PdDiaryService {

    /**
     * 新增日记
     *
     * @param diary
     * @return
     */
    public PdDiary insertDiary(PdDiary diary);

    /**
     * 更新日记
     *
     * @param diary
     * @return
     */
    public int updateDiary(PdDiary diary);

    /**
     * 删除日记
     *
     * @param diaryId
     * @return
     */
    public int deleteDiary(int diaryId, int userId);

    /**
     * 获取指定用户的指定日记
     * @param userId
     * @param diaryId
     * @return
     */
    public PdDiary findDiaryById(int userId, int diaryId);

    /**
     * 获取指定用户的所有日记
     *
     * @param userId
     * @return
     */
    public List<PdDiary> findDiaryAll(int userId);

    /**
     * 根据关键字查找日记
     *
     * @param userId
     * @param global
     * @return
     */
    public List<PdDiary> findDiaryByGlobal(int userId, String global);
}
