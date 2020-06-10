package bwcx.blogs.admin.user.consumer.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class AdminUser {
    /**
     * 主键自增
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 1:表示启用，0：禁用
     */
    private Integer status;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 1:男，2:女
     */
    private Integer sex;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 获取主键自增
     *
     * @return id - 主键自增
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键自增
     *
     * @param id 主键自增
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取1:表示启用，0：禁用
     *
     * @return status - 1:表示启用，0：禁用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置1:表示启用，0：禁用
     *
     * @param status 1:表示启用，0：禁用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取手机号
     *
     * @return phone_number - 手机号
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置手机号
     *
     * @param phoneNumber 手机号
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 获取1:男，2:女
     *
     * @return sex - 1:男，2:女
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置1:男，2:女
     *
     * @param sex 1:男，2:女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}