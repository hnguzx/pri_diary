package per.guzx.priDiary.pojo;

import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


@Alias("praise")
@Table(name = "pd_blog")
public class PdPraise {
    /**
     * 点赞ID
     */
    @Id
    @Column(name = "praise_id")
    private Integer praiseId;

    /**
     * 点赞用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 所属博客ID
     */
    @Column(name = "blog_id")
    private Integer blogId;

    /**
     * 所属评论ID
     */
    @Column(name = "comment_id")
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