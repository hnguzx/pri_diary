package per.guzx.priDiary.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.type.Alias;
import per.guzx.priDiary.tool.Groups;
import per.guzx.priDiary.valid.ListValue;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;


/**
 * @author Administrator
 */
@Table(name = "pd_blog")
@Alias("blog")
@ApiModel(description = "博客详细信息")
public class PdBlog {
    /**
     * 博客ID
     */
    @Null(message = "新增时不需要指定id", groups = Groups.Add.class)
    @NotNull(message = "更新时必须指定id", groups = Groups.Update.class)
    @Id
    @Column(name = "blog_id")
    @ApiModelProperty(value = "博客id")
    private Integer blogId;

    /**
     * 博客所属用户ID
     */
    @NotNull(message = "创建用户不能为空",groups = {Groups.Add.class})
    @Column(name = "user_id")
    @ApiModelProperty(value = "博客所属用户ID")
    private Integer userId;

    /**
     * 博客类型（一句话/小故事）
     */
    @ListValue(value = {1,2},message = "博客类型不正确",groups = {Groups.Add.class})
    @Column(name = "blog_type")
    @ApiModelProperty(value = "博客类型（一句话/小故事）")
    private Integer blogType;

    /**
     * 博客图片地址
     */
    @Column(name = "blog_image")
    @ApiModelProperty(value = "博客图片地址")
    private String blogImage;

    /**
     * 博客具体内容
     */
    @NotBlank(message = "博客内容不能为空",groups = {Groups.Add.class})
    @Column(name = "blog_context")
    @ApiModelProperty(value = "博客具体内容")
    private String blogContext;

    /**
     * 博客标签
     */
    @Column(name = "blog_label")
    @ApiModelProperty(value = "博客标签")
    private Integer blogLabel;

    /**
     * 博客创建时间
     */
    @Column(name = "blog_create_time")
    @ApiModelProperty(value = "博客创建时间")
    private String blogCreateTime;

    /**
     * 博客更新时间
     */
    @Column(name = "blog_update_time")
    @ApiModelProperty(value = "博客更新时间")
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
    public Integer getBlogType() {
        return blogType;
    }

    /**
     * 设置博客类型（一句话/小故事）
     *
     * @param blogType 博客类型（一句话/小故事）
     */
    public void setBlogType(Integer blogType) {
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
    public Integer getBlogLabel() {
        return blogLabel;
    }

    /**
     * 设置博客标签
     *
     * @param blogLabel 博客标签
     */
    public void setBlogLabel(Integer blogLabel) {
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