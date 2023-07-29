package com.competa.competademo.service;

import com.competa.competademo.models.Type;
import com.competa.competademo.dto.TypeDto;

import java.util.List;

public interface TypeService {
    void saveType(TypeDto typeDto);

    // TODO - какова роль этого сервиса?
    Type findByType(String type); // может быть искать по id ?

    List<TypeDto> findAllTypes();

    void saveType(Type type);

}
