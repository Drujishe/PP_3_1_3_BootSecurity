package ru.drujishe.boot_security.model;


import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "table_users")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    private int age;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "table_users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeAddress(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }

    public void setId(long id) {
        this.id = id;
    }

    public MyUser() {
    }

    public MyUser(long id, String name, String surname, int age, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "MyUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", roles=" + roles +
                '}';
    }
}
