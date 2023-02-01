package ru.drujishe.boot_security.service;

import org.springframework.stereotype.Service;
import ru.drujishe.boot_security.dao.RoleDao;
import ru.drujishe.boot_security.model.Role;

@Service
public class RoleServiceImp implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void add(Role role) {
        roleDao.add(role);
    }
}
