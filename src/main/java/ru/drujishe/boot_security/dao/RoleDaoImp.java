package ru.drujishe.boot_security.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.drujishe.boot_security.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class RoleDaoImp implements RoleDao {
    @PersistenceContext
    EntityManager manager;

    @Override
    public void add(Role role) {
        manager.persist(role);
    }
}
