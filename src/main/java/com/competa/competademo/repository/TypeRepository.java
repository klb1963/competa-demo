package com.competa.competademo.repository;

import com.competa.competademo.entity.Role;
import com.competa.competademo.entity.User;
import com.competa.competademo.models.Competa;
import com.competa.competademo.models.Type;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TypeRepository extends CrudRepository<Type, Integer> {

    //TODO - как правильно извлекать из репозитория - методом findByType или запросом
    Type findByType(String type); // это правильно?

//    @Query("select с from Type с where с.type = ?1")
//    List<Type> findAllByTypeId(Integer id);

}
