package com.dev.mapper;

import com.dev.SpringBootRest.entity.User;
import com.dev.dto.UserDto;

public class UserMapper {

    public static UserDto mapTOUserDto(User user){
        UserDto userDto = new UserDto(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail()
        );
        return userDto;
    }

    public static User mapToUser(UserDto userDto){
        User user = new User(
            userDto.getId(),
            userDto.getFirstName(),
            userDto.getLastName(),
            userDto.getEmail()
        );
        return user;
    }
}
