package ru.drujishe.boot_security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.drujishe.boot_security.model.MyUser;

@Repository
public class UserDetailsImp implements UserDetailsService {
    private final UserService userService;

    public UserDetailsImp(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = userService.findByUsername(username);
        System.out.println(user.getUsername() + " " + user.getPassword() + " BUGAGA");
        return MyUser.fromMyUser(user);
    }
}
