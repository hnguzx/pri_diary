package per.guzx.pri_diary.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("diaryDetail")
public class PdDiaryDetail implements Serializable, Cloneable {
    /**
     * 详情对应的日记的id
     */
    private Integer diaryId;
    /**
     * 日志id
     */
    private Integer detailId;
    /**
     * 日志详情
     */
    private String detailContent;
    /**
     * 日志封面
     */
    private String detailPhoto;

    public Integer getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Integer diaryId) {
        this.diaryId = diaryId;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
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
}