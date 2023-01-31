package ru.drujishe.boot_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.drujishe.boot_security.dao.UserDao;
import ru.drujishe.boot_security.model.MyUser;

import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(MyUser myUser) {
        userDao.add(myUser);
    }

    @Override
    public void update(int id, MyUser updatedMyUser) {
        userDao.update(id, updatedMyUser);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public List<MyUser> getAll() {
        return userDao.getAll();
    }

    @Override
    public MyUser getUserById(int id) {
        return userDao.getUserById(id);
    }
}
