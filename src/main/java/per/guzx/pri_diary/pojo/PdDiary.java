package per.guzx.pri_diary.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

@Alias("diary")
public class PdDiary implements Serializable, Cloneable {
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 日记唯一性标识
     */
    private Integer diaryId;
    /**
     * 日记标题
     */
    private String diaryTitle;
    /**
     * 当天天气
     */
    private String diaryWeather;
    /**
     * 当天心情
     */
    private String diaryMood;
    /**
     * 当天主要事件
     */
    private String diaryEvent;
    /**
     * 当天日记所在位置
     */
    private String diaryLocation;
    /**
     * 日记创建时间
     */
    private Date diaryCreateTime;
    /**
     * 日记最后更新时间
     */
    private Date diaryUpdateTime;

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDiaryId() {
        return this.diaryId;
    }

    public void setDiaryId(Integer diaryId) {
        this.diaryId = diaryId;
    }

    public String getDiaryTitle() {
        return this.diaryTitle;
    }

    public void setDiaryTitle(String diaryTitle) {
        this.diaryTitle = diaryTitle;
    }

    public String getDiaryWeather() {
        return this.diaryWeather;
    }

    public void setDiaryWeather(String diaryWeather) {
        this.diaryWeather = diaryWeather;
    }

    public String getDiaryMood() {
        return this.diaryMood;
    }

    public void setDiaryMood(String diaryMood) {
        this.diaryMood = diaryMood;
    }

    public String getDiaryEvent() {
        return this.diaryEvent;
    }

    public void setDiaryEvent(String diaryEvent) {
        this.diaryEvent = diaryEvent;
    }

    public String getDiaryLocation() {
        return this.diaryLocation;
    }

    public void setDiaryLocation(String diaryLocation) {
        this.diaryLocation = diaryLocation;
    }

    public Date getDiaryCreateTime() {
        return this.diaryCreateTime;
    }

    public void setDiaryCreateTime(Date diaryCreateTime) {
        this.diaryCreateTime = diaryCreateTime;
    }

    public Date getDiaryUpdateTime() {
        return this.diaryUpdateTime;
    }

    public void setDiaryUpdateTime(Date diaryUpdateTime) {
        this.diaryUpdateTime = diaryUpdateTime;
    }
}