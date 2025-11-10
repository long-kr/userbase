package com.userbase.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.userbase.dto.CreateRequest;
import com.userbase.dto.UpdateRequest;
import com.userbase.dto.UserDto;
import com.userbase.entity.User;

/**
 * Mapper interface for converting between User entities and DTOs.
 * Builder is disabled because MapStruct would try to use the builder
 * to create User instances, which can lead to problems with JPA-managed
 * entities that have fields inherited from BaseEntity.
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(CreateRequest createRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void updateEntity(@MappingTarget User user, UpdateRequest updateRequest);
}