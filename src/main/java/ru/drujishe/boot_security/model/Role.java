package ru.drujishe.boot_security.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "table_roles")
public class Role implements GrantedAuthority {
    private String roleName;
    @Id

    private long roleId;

    public Role() {
    }

    @ManyToMany(mappedBy = "roles")
    Set<MyUser> users = new HashSet<>();

    public Set<MyUser> getUsers() {
        return users;
    }

    public void setUsers(Set<MyUser> users) {
        this.users = users;
    }

    public Role(long roleId, String roleName) {
        this.roleName = roleName;
        this.roleId = roleId;
    }

    public Role(String roleName, long roleId, Set<MyUser> users) {
        this.roleName = roleName;
        this.roleId = roleId;
        this.users = users;
    }

    public Role(long id, String roleName, long roleId) {
        this.roleName = roleName;
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                '}';
    }

    public static List<Role> getAllRoles() {
        return Arrays.asList(
                new Role(0, "ROLE_ADMIN"),
                new Role(1, "ROLE_USER")
//                new Role(2, "ROLE_TEST")
        );
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
