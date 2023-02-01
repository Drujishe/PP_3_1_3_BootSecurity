package ru.drujishe.boot_security.service;


import org.springframework.stereotype.Service;
import ru.drujishe.boot_security.model.MyUser;

import java.util.List;

@Service
public interface UserService {
    void add(MyUser myUser);

    void update(long id, MyUser updatedMyUser);

    void delete(long id);

    List<MyUser> getAll();

    MyUser getUserById(long id);

    MyUser findByUsername(String username);
}
