package com.competa.competademo.repository;

import com.competa.competademo.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Integer> {

    //TODO - как правильно извлекать из репозитория - методом findByType или запросом
    //без разницы можно и так: "@Query("select t from Type t where t.type = ?1")" и так: Type findByType(String type);, происходит одно и тоже
    Type findByType(String type);

    List<Type> findAll();

    //тут если не правильный тип в аргументе, должен быть не Integer а String
//    @Query("select с from Type с where с.type = ?1")
//    List<Type> findAllByTypeId(Integer id);

}
