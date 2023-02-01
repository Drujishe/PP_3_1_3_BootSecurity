package ru.drujishe.boot_security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.drujishe.boot_security.model.MyUser;

@Repository
public interface UserRepository extends JpaRepository<MyUser,Long> {

}
