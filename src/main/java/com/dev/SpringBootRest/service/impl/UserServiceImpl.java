package com.dev.SpringBootRest.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dev.SpringBootRest.entity.User;
import com.dev.SpringBootRest.repo.UserRepository;
import com.dev.SpringBootRest.service.UserService;
import com.dev.dto.UserDto;
import com.dev.mapper.UserMapper;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        UserDto updatedUserDto = UserMapper.mapTOUserDto(savedUser);
        return updatedUserDto;
    }
    @Override
    public UserDto getUserById(Long userId) {
        return UserMapper.mapTOUserDto(userRepository.findById(userId).get());
    }
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapTOUserDto).collect(Collectors.toList()); 
    }
    @Override
    public UserDto updateUser(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getId()).get();
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        userRepository.save(existingUser);
        return UserMapper.mapTOUserDto(existingUser);
    }
    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}
