package ru.drujishe.boot_security.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.drujishe.boot_security.model.MyUser;
import ru.drujishe.boot_security.repositories.UserRepository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager manager;

    private final UserRepository userRepository;

    @Autowired
    public UserDaoImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void add(MyUser myUser) {
        userRepository.save(myUser);
    }

    @Override
    public void update(int id, MyUser updatedMyUser) {
        userRepository.deleteById((long) id);
        userRepository.save(updatedMyUser);
//        manager.merge(updatedMyUser);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById((long) id);
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
