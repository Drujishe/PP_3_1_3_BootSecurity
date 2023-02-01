package ru.drujishe.boot_security.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.drujishe.boot_security.model.Role;

@Component
public class RoleConverter implements Converter<String, Role> {
    @Override
    public Role convert(String id) {
        return Role.getAllRoles().get(Integer.parseInt(id));
    }
}
