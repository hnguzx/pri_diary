package per.guzx.pri_diary.pojo;

import org.apache.ibatis.type.Alias;


@Alias("praise")
public class PdPraise {
    /**
     * 点赞ID
     */
    private Integer praiseId;

    /**
     * 点赞用户ID
     */
    private Integer userId;

    /**
     * 所属博客ID
     */
    private Integer blogId;

    /**
     * 所属评论ID
     */
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