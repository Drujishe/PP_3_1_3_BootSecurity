package ru.drujishe.boot_security.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.drujishe.boot_security.dao.UserDao;
import ru.drujishe.boot_security.model.Person;

import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Lazy
    public UserServiceImp(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void add(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        userDao.add(person);
    }

    @Override
    public void update(long id, Person updatedPerson) {
        updatedPerson.setPassword(passwordEncoder.encode(updatedPerson.getPassword()));
        userDao.update(id, updatedPerson);
    }

    @Override
    public void delete(long id) {
        userDao.delete(id);
    }

    @Override
    public List<Person> getAll() {
        return userDao.getAll();
    }

    @Override
    public Person getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public Person getPersonByUsername(String username) {
        return userDao.getPersonByUsername(username);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

}
