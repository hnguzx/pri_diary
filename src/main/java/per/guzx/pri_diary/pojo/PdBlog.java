package per.guzx.pri_diary.pojo;

import org.apache.ibatis.type.Alias;

import javax.persistence.*;

@Alias("blog")
@Table(name = "pd_blog")
public class PdBlog {
    /**
     * 博客ID
     */
    @Id
    @Column(name = "blog_id")
    private Integer blogId;

    /**
     * 博客所属用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 博客类型（一句话/小故事）
     */
    @Column(name = "blog_type")
    private String blogType;

    /**
     * 博客图片
     */
    @Column(name = "blog_image")
    private String blogImage;

    /**
     * 博客具体内容
     */
    @Column(name = "blog_context")
    private String blogContext;

    /**
     * 博客标签
     */
    @Column(name = "blog_label")
    private String blogLabel;

    /**
     * 博客创建时间
     */
    @Column(name = "blog_create_time")
    private String blogCreateTime;

    /**
     * 博客更新时间
     */
    @Column(name = "blog_update_time")
    private String blogUpdateTime;

    /**
     * 获取博客ID
     *
     * @return blog_id - 博客ID
     */
    public Integer getBlogId() {
        return blogId;
    }

    /**
     * 设置博客ID
     *
     * @param blogId 博客ID
     */
    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    /**
     * 获取博客所属用户ID
     *
     * @return user_id - 博客所属用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置博客所属用户ID
     *
     * @param userId 博客所属用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取博客类型（一句话/小故事）
     *
     * @return blog_type - 博客类型（一句话/小故事）
     */
    public String getBlogType() {
        return blogType;
    }

    /**
     * 设置博客类型（一句话/小故事）
     *
     * @param blogType 博客类型（一句话/小故事）
     */
    public void setBlogType(String blogType) {
        this.blogType = blogType;
    }

    /**
     * 获取博客图片
     *
     * @return blog_image - 博客图片
     */
    public String getBlogImage() {
        return blogImage;
    }

    /**
     * 设置博客图片
     *
     * @param blogImage 博客图片
     */
    public void setBlogImage(String blogImage) {
        this.blogImage = blogImage;
    }

    /**
     * 获取博客具体内容
     *
     * @return blog_context - 博客具体内容
     */
    public String getBlogContext() {
        return blogContext;
    }

    /**
     * 设置博客具体内容
     *
     * @param blogContext 博客具体内容
     */
    public void setBlogContext(String blogContext) {
        this.blogContext = blogContext;
    }

    /**
     * 获取博客标签
     *
     * @return blog_label - 博客标签
     */
    public String getBlogLabel() {
        return blogLabel;
    }

    /**
     * 设置博客标签
     *
     * @param blogLabel 博客标签
     */
    public void setBlogLabel(String blogLabel) {
        this.blogLabel = blogLabel;
    }

    /**
     * 获取博客创建时间
     *
     * @return blog_create_time - 博客创建时间
     */
    public String getBlogCreateTime() {
        return blogCreateTime;
    }

    /**
     * 设置博客创建时间
     *
     * @param blogCreateTime 博客创建时间
     */
    public void setBlogCreateTime(String blogCreateTime) {
        this.blogCreateTime = blogCreateTime;
    }

    /**
     * 获取博客更新时间
     *
     * @return blog_update_time - 博客更新时间
     */
    public String getBlogUpdateTime() {
        return blogUpdateTime;
    }

    /**
     * 设置博客更新时间
     *
     * @param blogUpdateTime 博客更新时间
     */
    public void setBlogUpdateTime(String blogUpdateTime) {
        this.blogUpdateTime = blogUpdateTime;
    }
}