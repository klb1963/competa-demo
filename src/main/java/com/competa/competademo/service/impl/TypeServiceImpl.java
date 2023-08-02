package com.competa.competademo.service.impl;

import com.competa.competademo.dto.TypeDto;
import com.competa.competademo.models.Type;
import com.competa.competademo.repository.TypeRepository;
import com.competa.competademo.service.TypeService;

import java.util.List;
import java.util.stream.Collectors;

public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public void saveType(TypeDto typeDto) {
        Type type = new Type();
        type.setType(typeDto.getType());
        // к примеру: что будет, если Type уже существует?
        saveType(type);
    }

    @Override
    public Type findByType(String type) {
        // TODO - не видит typeRepository
        // его надо заинжектить
        // тут нужна доп логика
        // к примеру: что будет, если Type не будет найдет?
        return typeRepository.findByType(type);
    }

    @Override
    public List<TypeDto> findAllTypes() {
        // TODO - не видит typeRepository
        List<Type> types = typeRepository.findAll();
        return types.stream().map((type) -> convertEntityToDto(type))
                .collect(Collectors.toList());
    }

    @Override
    public void saveType(Type type) {
        // TODO - не видит typeRepository
        typeRepository.save(type);
    }

    //todo тут вообще не понятно
    private TypeDto convertEntityToDto(Type type) {
        TypeDto typeDto = new TypeDto();
        String mytype = typeDto.getType();
        return typeDto;
    }
}
