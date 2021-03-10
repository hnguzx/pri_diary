package per.guzx.priDiary.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.type.Alias;
import per.guzx.priDiary.enumeration.EventEnum;
import per.guzx.priDiary.enumeration.MoodEnum;
import per.guzx.priDiary.enumeration.WeatherEnum;
import per.guzx.priDiary.tool.Groups;
import per.guzx.priDiary.valid.ListValue;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Administrator
 */
@Table(name = "pd_blog")
@Alias("diary")
@ApiModel(description = "日记详情")
public class PdDiary implements Serializable {

    /**
     * 日记唯一标识
     */
    @Null(message = "新增时不需要指定id", groups = Groups.Add.class)
    @NotNull(message = "更新时必须指定id", groups = Groups.Update.class)
    @Id
    @Column(name = "diary_id")
    @ApiModelProperty(value = "日记ID")
    private Integer diaryId;

    /**
     * 日记所属用户id
     */
    @Column(name = "user_id")
    @NotNull(message = "用户ID不能为空！",groups = {Groups.Add.class,Groups.Update.class})
    @ApiModelProperty(value = "日记所属用户id")
    private Integer userId;

    /**
     * 日记封面
     */
    @Column(name = "diary_photo")
    @ApiModelProperty(value = "日记封面图片地址")
    private String diaryPhoto;

    /**
     * 日记标题
     */
    @Column(name = "diary_title")
    @NotBlank(message = "日记标题不能为空！",groups = {Groups.Add.class,Groups.Update.class})
    @Size(min = 1, max = 100, message = "标题长度不能大于100")
    @ApiModelProperty(value = "日记标题")
    private String diaryTitle;

    /**
     * 当天天气
     */
    @Column(name = "diary_weather")
    @NotNull(message = "天气不能为空！",groups = {Groups.Add.class})
    @ApiModelProperty(value = "当天天气")
    private WeatherEnum diaryWeather;

    /**
     * 当天心情
     */
    @Column(name = "diary_mood")
    @NotNull(message = "心情不能为空！",groups = {Groups.Add.class})
    @ApiModelProperty(value = "当天心情")
    private MoodEnum diaryMood;

    /**
     * 当天主要事件
     */
    @Column(name = "diary_event")
    @NotNull(message = "事件不能为空！",groups = {Groups.Add.class})
    @ApiModelProperty(value = "当天主要事件")
    private EventEnum diaryEvent;

    /**
     * 日记详情
     */
    @Column(name = "diary_content")
    @NotBlank(message = "日记详情不能为空！",groups = {Groups.Add.class})
    @ApiModelProperty(value = "日记详情")
    private String diaryContent;

    /**
     * 当天日记所在位置
     */
    @Column(name = "diary_location")
    @ApiModelProperty(value = "当天日记所在位置")
    private String diaryLocation;

    /**
     * 日记记录经度
     */
    @Column(name = "diary_longitude")
    @ApiModelProperty(value = "日记记录经度")
    private String diaryLongitude;

    /**
     * 日记记录纬度
     */
    @Column(name = "diary_latitude")
    @ApiModelProperty(value = "日记记录纬度")
    private String diaryLatitude;

    /**
     * 日记创建时间
     */
    @Column(name = "diary_create_time")
    @ApiModelProperty(value = "日记创建时间")
    private String diaryCreateTime;

    /**
     * 日记创建日期
     */
    @Column(name = "diary_create_day")
    @ApiModelProperty(value = "日记创建日期")
    private String diaryCreateDay;

    /**
     * 日记最后更新时间
     */
    @Column(name = "diary_update_time")
    @ApiModelProperty(value = "日记最后更新时间")
    private String diaryUpdateTime;

    /**
     * 获取日记唯一标识
     *
     * @return diary_id - 日记唯一标识
     */
    public Integer getDiaryId() {
        return diaryId;
    }

    /**
     * 设置日记唯一标识
     *
     * @param diaryId 日记唯一标识
     */
    public void setDiaryId(Integer diaryId) {
        this.diaryId = diaryId;
    }

    /**
     * 获取日记所属用户id
     *
     * @return user_id - 日记所属用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置日记所属用户id
     *
     * @param userId 日记所属用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取日记封面
     *
     * @return diary_photo - 日记封面
     */
    public String getDiaryPhoto() {
        return diaryPhoto;
    }

    /**
     * 设置日记封面
     *
     * @param diaryPhoto 日记封面
     */
    public void setDiaryPhoto(String diaryPhoto) {
        this.diaryPhoto = diaryPhoto;
    }

    /**
     * 获取日记标题
     *
     * @return diary_title - 日记标题
     */
    public String getDiaryTitle() {
        return diaryTitle;
    }

    /**
     * 设置日记标题
     *
     * @param diaryTitle 日记标题
     */
    public void setDiaryTitle(String diaryTitle) {
        this.diaryTitle = diaryTitle;
    }

    /**
     * 获取当天天气
     *
     * @return diary_weather - 当天天气
     */
    public WeatherEnum getDiaryWeather() {
        return diaryWeather;
    }

    /**
     * 设置当天天气
     *
     * @param diaryWeather 当天天气
     */
    public void setDiaryWeather(WeatherEnum diaryWeather) {
        this.diaryWeather = diaryWeather;
    }

    /**
     * 获取当天心情
     *
     * @return diary_mood - 当天心情
     */
    public MoodEnum getDiaryMood() {
        return diaryMood;
    }

    /**
     * 设置当天心情
     *
     * @param diaryMood 当天心情
     */
    public void setDiaryMood(MoodEnum diaryMood) {
        this.diaryMood = diaryMood;
    }


    /**
     * 获取当天主要事件
     *
     * @return diary_event - 当天主要事件
     */
    public EventEnum getDiaryEvent() {
        return diaryEvent;
    }

    /**
     * 设置当天主要事件
     *
     * @param diaryEvent 当天主要事件
     */
    public void setDiaryEvent(EventEnum diaryEvent) {
        this.diaryEvent = diaryEvent;
    }

    /**
     * 获取日记详情
     *
     * @return diary_content - 日记详情
     */
    public String getDiaryContent() {
        return diaryContent;
    }

    /**
     * 设置日记详情
     *
     * @param diaryContent 日记详情
     */
    public void setDiaryContent(String diaryContent) {
        this.diaryContent = diaryContent;
    }

    /**
     * 获取当天日记所在位置
     *
     * @return diary_location - 当天日记所在位置
     */
    public String getDiaryLocation() {
        return diaryLocation;
    }

    /**
     * 设置当天日记所在位置
     *
     * @param diaryLocation 当天日记所在位置
     */
    public void setDiaryLocation(String diaryLocation) {
        this.diaryLocation = diaryLocation;
    }

    /**
     * 获取日记记录经度
     *
     * @return diary_longitude - 日记记录经度
     */
    public String getDiaryLongitude() {
        return diaryLongitude;
    }

    /**
     * 设置日记记录经度
     *
     * @param diaryLongitude 日记记录经度
     */
    public void setDiaryLongitude(String diaryLongitude) {
        this.diaryLongitude = diaryLongitude;
    }

    /**
     * 获取日记记录纬度
     *
     * @return diary_latitude - 日记记录纬度
     */
    public String getDiaryLatitude() {
        return diaryLatitude;
    }

    /**
     * 设置日记记录纬度
     *
     * @param diaryLatitude 日记记录纬度
     */
    public void setDiaryLatitude(String diaryLatitude) {
        this.diaryLatitude = diaryLatitude;
    }

    /**
     * 获取日记创建时间
     *
     * @return diary_create_time - 日记创建时间
     */
    public String getDiaryCreateTime() {
        return diaryCreateTime;
    }

    /**
     * 设置日记创建时间
     *
     * @param diaryCreateTime 日记创建时间
     */
    public void setDiaryCreateTime(String diaryCreateTime) {
        this.diaryCreateTime = diaryCreateTime;
    }

    /**
     * 获取日记创建日期
     *
     * @return diary_create_day - 日记创建日期
     */
    public String getDiaryCreateDay() {
        return diaryCreateDay;
    }

    /**
     * 设置日记创建日期
     *
     * @param diaryCreateDay 日记创建日期
     */
    public void setDiaryCreateDay(String diaryCreateDay) {
        this.diaryCreateDay = diaryCreateDay;
    }

    /**
     * 获取日记最后更新时间
     *
     * @return diary_update_time - 日记最后更新时间
     */
    public String getDiaryUpdateTime() {
        return diaryUpdateTime;
    }

    /**
     * 设置日记最后更新时间
     *
     * @param diaryUpdateTime 日记最后更新时间
     */
    public void setDiaryUpdateTime(String diaryUpdateTime) {
        this.diaryUpdateTime = diaryUpdateTime;
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
            return diary.getDiaryId().equals(this.getDiaryId()) &&
                    diary.getUserId().equals(this.getUserId()) &&
                    diary.getDiaryTitle().equals(this.getDiaryTitle()) &&
                    diary.getDiaryEvent().equals(this.getDiaryEvent()) &&
                    diary.getDiaryMood().equals(this.getDiaryMood()) &&
                    diary.getDiaryWeather().equals(this.getDiaryWeather()) &&
                    diary.getDiaryContent().equals(this.getDiaryContent()) &&
                    diary.getDiaryPhoto().equals(this.getDiaryPhoto()) &&
                    diary.getDiaryLocation().equals(this.getDiaryLocation());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}