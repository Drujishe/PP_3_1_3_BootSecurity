package ru.drujishe.boot_security.dao;


import ru.drujishe.boot_security.model.MyUser;

import java.util.List;

public interface UserDao {

    void add(MyUser myUser);

    void update(long id, MyUser updatedMyUser);

    void delete(long id);

    List<MyUser> getAll();

    MyUser getUserById(long id);

    MyUser findByUsername(String username);
}
