package ru.drujishe.boot_security.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.drujishe.boot_security.model.MyUser;


import java.util.List;


@Service
@Transactional
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void add(MyUser myUser) {
        manager.persist(myUser);
    }

    @Override
    public void update(int id, MyUser updatedMyUser) {
        manager.merge(updatedMyUser);
    }

    @Override
    public void delete(int id) {
        manager.remove(getUserById(id));
    }

    @Override
    public List<MyUser> getAll() {
        return manager.createQuery("from MyUser", MyUser.class).getResultList();
    }

    @Override
    public MyUser getUserById(int id) {
        return manager.createQuery("from MyUser user where user.id = " + id, MyUser.class).getSingleResult();
    }
}
