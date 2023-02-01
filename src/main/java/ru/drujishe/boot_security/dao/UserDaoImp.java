package ru.drujishe.boot_security.dao;


import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.drujishe.boot_security.model.MyUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager manager;

    private final PasswordEncoder passwordEncoder;

    @Lazy
    public UserDaoImp(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void add(MyUser myUser) {
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        manager.persist(myUser);
    }


    @Override
    public void update(long id, MyUser updatedMyUser) {
        updatedMyUser.setPassword(passwordEncoder.encode(updatedMyUser.getPassword()));
        manager.merge(updatedMyUser);
    }

    @Override
    public void delete(long id) {
        manager.remove(getUserById(id));
    }

    @Override
    public List<MyUser> getAll() {
        return manager.createQuery("from MyUser", MyUser.class).getResultList();
    }

    @Override
    public MyUser getUserById(long id) {
        return manager.createQuery("from MyUser user where user.id = :id", MyUser.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public MyUser findByUsername(String username) {
        return manager.createQuery("from MyUser user where user.username = :username", MyUser.class)
                .setParameter("username", username).getSingleResult();
    }
}
