package com.artragazzi.projetofullstack.mapper;

import com.artragazzi.projetofullstack.dto.UserDTO;
import com.artragazzi.projetofullstack.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Converte de entidade para DTO
    @Mapping(source = "name", target = "name")
    @Mapping(source = "login", target = "login")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    UserDTO toDTO(User user);

    // Converte de DTO para entidade
    @Mapping(source = "name", target = "name")
    @Mapping(source = "login", target = "login")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    User toEntity(UserDTO userDTO);
}
