package com.mercury.SpringBootRestDemo.bean;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @Column
    private Integer roleId;

    @Column
    private String roleName;

    @Column
    private float discount;

    public Role() {
    }

    public Role(Integer roleId, String roleName, float discount) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.discount = discount;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", discount=" + discount +
                '}';
    }
}
