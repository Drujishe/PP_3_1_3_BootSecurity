package ru.drujishe.boot_security.model;


import com.sun.istack.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "table_users",
        uniqueConstraints = {@UniqueConstraint(columnNames = "username")})      // уникальный логин
public class Person implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    private int age;
    @Column(unique = true)
    private String username;
    private String password;

    @ManyToMany(cascade = {CascadeType.MERGE})        // заменил ALL на MERGE -> в ролях не появляются дубликаты
    @JoinTable(
            name = "table_users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles;


    public void setId(long id) {
        this.id = id;
    }

    public Person() {
    }

    public Person(long id, String name, String surname, int age, Set<Role> roles, String username, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.roles = roles;
        this.username = username;
        this.password = password;
    }

    public Person(String name, String surname, int age, Set<Role> roles, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.roles = roles;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public static UserDetails fromMyUser(Person person) {
        return new User(
                person.getUsername(),
                person.getPassword(),
                person.isEnabled(),
                person.isAccountNonExpired(),
                person.isCredentialsNonExpired(),
                person.isAccountNonLocked(),
                person.getAuthorities()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);        // другие параметры не используются, потому что логин уникальный
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return this.hashCode() == obj.hashCode();
    }
}