package org.jiang.core.role.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* <p>
*实体
*</p>
*
* @author Li.Linhua
* @since 2019-07-29
*/
    @TableName("sys_role")
    public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

        /**
        * 角色代码
        */
    @TableField("role_code")
    private String roleCode;

        /**
        * 角色名称
        */
    @TableField("role_name")
    private String roleName;

        /**
        * 创建时间
        */
    @TableField("create_time")
    private LocalDateTime createTime;

        /**
        * 更新时间
        */
    @TableField("update_time")
    private LocalDateTime updateTime;

        /**
        * 逻辑删除
        */
    @TableField("status")
    private Integer status;

    public String getId() {
    return id;
    }

    public void setId(String id) {
this.id = id;
}

    public String getRoleCode() {
    return roleCode;
    }

    public void setRoleCode(String roleCode) {
this.roleCode = roleCode;
}

    public String getRoleName() {
    return roleName;
    }

    public void setRoleName(String roleName) {
this.roleName = roleName;
}

    public LocalDateTime getCreateTime() {
    return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
this.createTime = createTime;
}

    public LocalDateTime getUpdateTime() {
    return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
this.updateTime = updateTime;
}

    public Integer getStatus() {
    return status;
    }

    public void setStatus(Integer status) {
this.status = status;
}


    @Override
    public String toString() {
    return "Role{" +
            "id=" + id +
            ", roleCode=" + roleCode +
            ", roleName=" + roleName +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", status=" + status +
    "}";
    }
}
