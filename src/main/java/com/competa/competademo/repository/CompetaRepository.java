package com.competa.competademo.repository;

import com.competa.competademo.entity.User;
import com.competa.competademo.models.Competa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompetaRepository extends CrudRepository< Competa, Long> {

    @Query ("select с from Competa с where с.user.email = ?1")
    List<Competa> findAllByUserEmail(String email);

    @Query ("select с from Competa с where с.user = ?1")
    List<Competa> findAllByUser(User user);

}
