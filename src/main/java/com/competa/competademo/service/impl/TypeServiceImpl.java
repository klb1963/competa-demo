package com.competa.competademo.service.impl;

import com.competa.competademo.dto.TypeDto;
import com.competa.competademo.dto.UserDto;
import com.competa.competademo.entity.User;
import com.competa.competademo.models.Type;
import com.competa.competademo.service.TypeService;
import com.competa.competademo.repository.TypeRepository;

import java.util.List;
import java.util.stream.Collectors;

public class TypeServiceImpl implements TypeService {
    @Override
    public void saveType(TypeDto typeDto) {
        Type type = new Type();
        type.setType(typeDto.getType());
        saveType(type);
    }

    @Override
    public Type findByType(String type) {
        // TODO - не видит typeRepository
        return typeRepository.findByType(type);
    }

    @Override
    public List<TypeDto> findAllTypes() {
        // TODO - не видит typeRepository
        List<Type> users = typeRepository.findAll();
        return type.stream().map((type) -> convertEntityToDto(type))
                .collect(Collectors.toList());
    }

    @Override
    public void saveType(Type type) {
        // TODO - не видит typeRepository
        typeRepository.save(type);
    }

    private TypeDto convertEntityToDto(Type type) {
        TypeDto typeDto = new TypeDto();
        String mytype = typeDto.getType();
        return typeDto;
    }
}
