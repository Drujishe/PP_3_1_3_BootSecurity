package ru.drujishe.boot_security.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "table_roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String roleName;
    private long roleId;

    @ManyToMany(mappedBy = "roles")
    private List<MyUser> users = new ArrayList<>();

    public Role() {
    }

    public Role(long roleId, String roleName) {
        this.roleName = roleName;
        this.roleId = roleId;
    }

    public Role(long id, String roleName, long roleId, List<MyUser> users) {
        this.id = id;
        this.roleName = roleName;
        this.roleId = roleId;
        this.users = users;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<MyUser> getUsers() {
        return users;
    }

    public void setUsers(List<MyUser> users) {
        this.users = users;
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
                new Role(0, "ADMIN"),
                new Role(1, "USER"),
                new Role(2, "TEST")
        );
    }
}
