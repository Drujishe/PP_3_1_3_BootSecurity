package ru.drujishe.boot_security.dao;


import org.springframework.security.core.userdetails.User;
import ru.drujishe.boot_security.model.Person;

import java.util.List;

public interface UserDao {

    void add(Person person);

    void update(long id, Person updatedPerson);

    void delete(long id);

    List<Person> getAll();

    Person getUserById(long id);
    Person getPersonByUsername(String username);

    User findByUsername(String username);
}
