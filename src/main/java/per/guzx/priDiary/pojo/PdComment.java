package per.guzx.priDiary.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.URL;
import per.guzx.priDiary.tool.Groups;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.math.BigDecimal;


/**
 * @author Administrator
 */
@Table(name = "pd_blog")
@Alias("comment")
@ApiModel(description = "评论详细信息")
public class PdComment {
    /**
     * 评论ID
     */
    @Null(message = "新增时不需要指定id", groups = Groups.Add.class)
    @NotNull(message = "更新时必须指定id", groups = Groups.Update.class)
    @Id
    @Column(name = "comment_id")
    @ApiModelProperty(value = "评论ID")
    private Integer commentId;

    /**
     * 评论所属博客ID
     */
    @NotNull(message = "所属博客id不能为空",groups = {Groups.Add.class,Groups.Update.class})
    @Column(name = "blog_id")
    @ApiModelProperty(value = "评论所属博客ID")
    private Integer blogId;

    /**
     * 评论人ID
     */
    @NotNull(message = "评论人id不能为空",groups = {Groups.Add.class,Groups.Update.class})
    @Column(name = "comment_user_id")
    @ApiModelProperty(value = "评论人ID")
    private Integer commentUserId;

    /**
     * 博客所属人ID
     */
    @NotNull(message = "博客所属人id不能为空",groups = {Groups.Add.class,Groups.Update.class})
    @Column(name = "blog_owner_id")
    @ApiModelProperty(value = "博客所属人ID")
    private Integer blogOwnerId;

    /**
     * 评论时间
     */
    @Column(name = "comment_create_time")
    @ApiModelProperty(value = "评论时间")
    private String commentCreateTime;

    /**
     * 被评论者ID
     */
    @NotNull(message = "被评论者id不能为空",groups = {Groups.Add.class,Groups.Update.class})
    @Column(name = "commented_user_id")
    @ApiModelProperty(value = "被评论者ID")
    private Integer commentedUserId;

    /**
     * 评论内容
     */
    @NotBlank(message = "评论内容不能为空",groups = {Groups.Add.class})
    @Column(name = "comment_context")
    @ApiModelProperty(value = "评论内容")
    private String commentContext;

    /**
     * 获取评论ID
     *
     * @return comment_id - 评论ID
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * 设置评论ID
     *
     * @param commentId 评论ID
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    /**
     * 获取评论所属博客ID
     *
     * @return blog_id - 评论所属博客ID
     */
    public Integer getBlogId() {
        return blogId;
    }

    /**
     * 设置评论所属博客ID
     *
     * @param blogId 评论所属博客ID
     */
    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    /**
     * 获取评论人ID
     *
     * @return comment_user_id - 评论人ID
     */
    public Integer getCommentUserId() {
        return commentUserId;
    }

    /**
     * 设置评论人ID
     *
     * @param commentUserId 评论人ID
     */
    public void setCommentUserId(Integer commentUserId) {
        this.commentUserId = commentUserId;
    }

    /**
     * 获取博客所属人ID
     *
     * @return blog_owner_id - 博客所属人ID
     */
    public Integer getBlogOwnerId() {
        return blogOwnerId;
    }

    /**
     * 设置博客所属人ID
     *
     * @param blogOwnerId 博客所属人ID
     */
    public void setBlogOwnerId(Integer blogOwnerId) {
        this.blogOwnerId = blogOwnerId;
    }

    /**
     * 获取评论时间
     *
     * @return comment_create_time - 评论时间
     */
    public String getCommentCreateTime() {
        return commentCreateTime;
    }

    /**
     * 设置评论时间
     *
     * @param commentCreateTime 评论时间
     */
    public void setCommentCreateTime(String commentCreateTime) {
        this.commentCreateTime = commentCreateTime;
    }

    /**
     * 获取被评论者ID
     *
     * @return commented_user_id - 被评论者ID
     */
    public Integer getCommentedUserId() {
        return commentedUserId;
    }

    /**
     * 设置被评论者ID
     *
     * @param commentedUserId 被评论者ID
     */
    public void setCommentedUserId(Integer commentedUserId) {
        this.commentedUserId = commentedUserId;
    }

    /**
     * 获取评论内容
     *
     * @return comment_context - 评论内容
     */
    public String getCommentContext() {
        return commentContext;
    }

    /**
     * 设置评论内容
     *
     * @param commentContext 评论内容
     */
    public void setCommentContext(String commentContext) {
        this.commentContext = commentContext;
    }
}