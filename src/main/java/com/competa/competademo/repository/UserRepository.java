package com.competa.competademo.repository;

import com.competa.competademo.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//todo @Repository немного ускорит работу
//todo возможно JpaRepository тут избыточен, скорее всего CrudRepository будет достаточно
public interface UserRepository extends CrudRepository<User, Long> {

    //todo наверное лучше возвращать Optional<User>, чтобы делать проверку на наличие записи в бд
    User findByEmail(String email);

    //fix
    List<User> findAll();
}
