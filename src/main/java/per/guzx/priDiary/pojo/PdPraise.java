package per.guzx.priDiary.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.type.Alias;
import per.guzx.priDiary.tool.Groups;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;


/**
 * @author Administrator
 */
@Table(name = "pd_praise")
@Alias("praise")
@ApiModel(description = "评论详细信息")
public class PdPraise implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 点赞ID
     */
    @Null(message = "新增时不需要指定id", groups = Groups.Add.class)
    @NotNull(message = "更新时必须指定id", groups = Groups.Update.class)
    @Id
    @Column(name = "praise_id")
    @ApiModelProperty(value = "点赞ID")
    private Integer praiseId;

    /**
     * 点赞用户ID
     */
    @NotNull(message = "点赞用户id不能为空", groups = Groups.Add.class)
    @Column(name = "user_id")
    @ApiModelProperty(value = "点赞用户ID")
    private Integer userId;

    /**
     * 所属博客ID
     */
    @Column(name = "blog_id")
    @ApiModelProperty(value = "所属博客ID")
    private Integer blogId;

    /**
     * 所属评论ID
     */
    @Column(name = "comment_id")
    @ApiModelProperty(value = "所属评论ID")
    private Integer commentId;

    /**
     * 获取点赞ID
     *
     * @return praise_id - 点赞ID
     */
    public Integer getPraiseId() {
        return praiseId;
    }

    /**
     * 设置点赞ID
     *
     * @param praiseId 点赞ID
     */
    public void setPraiseId(Integer praiseId) {
        this.praiseId = praiseId;
    }

    /**
     * 获取点赞用户ID
     *
     * @return user_id - 点赞用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置点赞用户ID
     *
     * @param userId 点赞用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取所属博客ID
     *
     * @return blog_id - 所属博客ID
     */
    public Integer getBlogId() {
        return blogId;
    }

    /**
     * 设置所属博客ID
     *
     * @param blogId 所属博客ID
     */
    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    /**
     * 获取所属评论ID
     *
     * @return comment_id - 所属评论ID
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * 设置所属评论ID
     *
     * @param commentId 所属评论ID
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }
}