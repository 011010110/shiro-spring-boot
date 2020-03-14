package org.jiang.core.user.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
* <p>
*系统用户实体
*</p>
*
* @author Li.Linhua
* @since 2019-07-15
*/
    @TableName("sys_user")
    public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

        /**
        * 用户名
        */
    @TableField("user_name")
    @NotBlank(message = "用户名不能为空！")
    private String userName;

        /**
        * 密码
        */
    @TableField("password")
    @NotBlank(message = "密码不能为空！")
    private String password;

        /**
        * 盐值
        */
    @TableField("salt")
    private String salt;

        /**
        * 电话号码
        */
    @TableField("phone")
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$",message = "手机号码不正确!")
    private String phone;

        /**
        * 邮箱
        */
    @TableField("email")
    @Email(message = "邮箱错误！")
    private String email;

    public String getId() {
    return id;
    }

    public void setId(String id) {
this.id = id;
}

    public String getUserName() {
    return userName;
    }

    public void setUserName(String userName) {
this.userName = userName;
}

    public String getPassword() {
    return password;
    }

    public void setPassword(String password) {
this.password = password;
}

    public String getSalt() {
    return salt;
    }

    public void setSalt(String salt) {
this.salt = salt;
}

    public String getPhone() {
    return phone;
    }

    public void setPhone(String phone) {
this.phone = phone;
}

    public String getEmail() {
    return email;
    }

    public void setEmail(String email) {
this.email = email;
}


    @Override
    public String toString() {
    return "User{" +
            "id=" + id +
            ", userName=" + userName +
            ", password=" + password +
            ", salt=" + salt +
            ", phone=" + phone +
            ", email=" + email +
    "}";
    }
}
