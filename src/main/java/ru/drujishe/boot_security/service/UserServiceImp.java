package ru.drujishe.boot_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.drujishe.boot_security.dao.UserDao;
import ru.drujishe.boot_security.model.MyUser;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(MyUser myUser) {
        userDao.add(myUser);
    }

    @Override
    public void update(long id, MyUser updatedMyUser) {
        userDao.update(id, updatedMyUser);
    }

    @Override
    public void delete(long id) {
        userDao.delete(id);
    }

    @Override
    public List<MyUser> getAll() {
        return userDao.getAll();
    }

    @Override
    public MyUser getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public MyUser findByUsername(String username) {
        return userDao.findByUsername(username);
    }

}
