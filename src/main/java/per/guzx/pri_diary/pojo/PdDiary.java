package per.guzx.pri_diary.pojo;

import org.apache.ibatis.type.Alias;
import per.guzx.pri_diary.enumeration.EventEnum;
import per.guzx.pri_diary.enumeration.MoodEnum;
import per.guzx.pri_diary.enumeration.WeathEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotNull(message = "日记标题不能为空！")
    @Size(min = 1, max = 100, message = "标题长度不能大于100")
    private String diaryTitle;
    /**
     * 当天天气
     */
    @NotNull
    @Size(min = 1)
    private WeathEnum diaryWeather;
    /**
     * 当天心情
     */
    @NotNull
    @Size(min = 1)
    private MoodEnum diaryMood;
    /**
     * 当天主要事件
     */
    @NotNull
    @Size(min = 1)
    private EventEnum diaryEvent;
    /**
     * 当天日记所在位置
     */
    private String diaryLocation;
    /**
     * 日志详情
     */
    private String detailContent;
    /**
     * 日志封面
     */
    private String detailPhoto;
    /**
     * 日记创建时间
     */
    private String diaryCreateTime;
    /**
     * 日记最后更新时间
     */
    private String diaryUpdateTime;

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

    public WeathEnum getDiaryWeather() {
        return diaryWeather;
    }

    public void setDiaryWeather(WeathEnum diaryWeather) {
        this.diaryWeather = diaryWeather;
    }

    public MoodEnum getDiaryMood() {
        return diaryMood;
    }

    public void setDiaryMood(MoodEnum diaryMood) {
        this.diaryMood = diaryMood;
    }

    public EventEnum getDiaryEvent() {
        return diaryEvent;
    }

    public void setDiaryEvent(EventEnum diaryEvent) {
        this.diaryEvent = diaryEvent;
    }

    public String getDiaryLocation() {
        return this.diaryLocation;
    }

    public void setDiaryLocation(String diaryLocation) {
        this.diaryLocation = diaryLocation;
    }

    public String getDiaryCreateTime() {
        return diaryCreateTime;
    }

    public void setDiaryCreateTime(String diaryCreateTime) {
        this.diaryCreateTime = diaryCreateTime;
    }

    public String getDiaryUpdateTime() {
        return diaryUpdateTime;
    }

    public void setDiaryUpdateTime(String diaryUpdateTime) {
        this.diaryUpdateTime = diaryUpdateTime;
    }

    public String getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent;
    }

    public String getDetailPhoto() {
        return detailPhoto;
    }

    public void setDetailPhoto(String detailPhoto) {
        this.detailPhoto = detailPhoto;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof PdDiary) {
            PdDiary diary = (PdDiary) obj;
            if (diary.getDiaryId() == this.getDiaryId() &&
                    diary.getUserId() == this.getUserId() &&
                    diary.getDiaryTitle().equals(this.getDiaryTitle()) &&
                    diary.getDiaryEvent().equals(this.getDiaryEvent()) &&
                    diary.getDiaryMood().equals(this.getDiaryMood()) &&
                    diary.getDiaryWeather().equals(this.getDiaryWeather()) &&
                    diary.getDetailContent().equals(this.getDetailContent()) &&
                    diary.getDetailPhoto().equals(this.getDetailPhoto()) &&
                    diary.getDiaryLocation().equals(this.getDiaryLocation())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}