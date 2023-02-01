package ru.drujishe.boot_security.service;

import org.springframework.stereotype.Component;
import ru.drujishe.boot_security.model.MyUser;
import ru.drujishe.boot_security.model.Role;

import javax.annotation.PostConstruct;
import java.util.HashSet;

@Component
public class DbInit {
    private final UserService userService;
    private final RoleService roleService;

    public DbInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void init() {
        try {
            Role.getAllRoles().forEach(roleService::add);           // наполнение таблицы ролей
                                                                    // создание базового админа
            HashSet<Role> roles = new HashSet<>();                  // admin - admin
            roles.add(new Role(0, "ROLE_ADMIN"));
            roles.add(new Role(1, "ROLE_USER"));
            userService.add(
                    new MyUser("Администратор", "Администраторов", 100,
                            roles,
                            "admin",
                            "admin")
            );
        } catch (Exception exception) {/*ignore*/}
    }

}
