package ru.drujishe.boot_security.dao;


import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.drujishe.boot_security.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void add(Person person) {
        manager.persist(person);
    }

    @Override
    public void update(long id, Person updatedPerson) {
        manager.merge(updatedPerson);
    }

    @Override
    public void delete(long id) {
        manager.remove(getUserById(id));
    }

    @Override
    public List<Person> getAll() {
        return manager.createQuery("" +
                        "select distinct person from Person person " +
                        "join fetch person.roles roles " +
                        "order by person.id", Person.class)
                .getResultList();
    }

    @Override
    public Person getUserById(long id) {
        return manager.createQuery("" +
                        "select distinct person from Person person " +
                        "join fetch person.roles roles " +
                        "where person.id = :id", Person.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Person getPersonByUsername(String username) {
        return manager.createQuery("" +
                        "select distinct person from Person person " +
                        "join fetch person.roles roles " +
                        "where person.username = :username", Person.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public User findByUsername(String username) {
        Person person = manager.createQuery("" +
                        "select distinct person from Person person " +
                        "join fetch person.roles roles " +
                        "where person.username = :username", Person.class)
                .setParameter("username", username)
                .getSingleResult();

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
}
