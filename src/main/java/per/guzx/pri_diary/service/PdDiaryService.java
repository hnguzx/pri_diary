package per.guzx.pri_diary.service;

import per.guzx.pri_diary.pojo.PdDiary;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.List;
import java.util.Map;

public interface PdDiaryService {

    /**
     * 新增日记
     *
     * @param diary
     * @return
     */
    public int insertDiary(HttpServletRequest request, PdDiary diary, Part detailPhoto);

    /**
     * 更新日记
     *
     * @param diary
     * @return
     */
    public int updateDiary(HttpServletRequest request, PdDiary diary, Part detailPhoto);

    /**
     * 删除日记
     *
     * @param diaryId
     * @return
     */
    public int deleteDiary(int diaryId, int userId);

    /**
     * 获取指定用户的指定日记
     *
     * @param userId
     * @param diaryId
     * @return
     */
    public Map<String, Object> findDiaryById(HttpServletResponse response, int userId, int diaryId);

    /**
     * 获取日记其它信息（除了创建时间，更新时间，封面图片，主要内容）
     *
     * @param userId
     * @param diaryId
     * @return
     */
    public PdDiary findDiaryOtherById(int userId, int diaryId);

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
