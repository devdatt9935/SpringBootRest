package com.dev.SpringBootRest.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.dev.SpringBootRest.entity.User;
import com.dev.SpringBootRest.repo.UserRepository;
import com.dev.SpringBootRest.service.UserService;
import com.dev.dto.UserDto;
//import com.dev.mapper.UserMapper;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private ModelMapper modelMapper;
    private UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        //User user = UserMapper.mapToUser(userDto);
        User user = modelMapper.map(userDto,User.class);
        User savedUser = userRepository.save(user);
        //UserDto updatedUserDto = UserMapper.mapTOUserDto(savedUser);
        UserDto updatedUserDto = modelMapper.map(savedUser, UserDto.class);
        return updatedUserDto;
    }
    @Override
    public UserDto getUserById(Long userId) {
        //return UserMapper.mapTOUserDto(userRepository.findById(userId).get());
        return modelMapper.map(userRepository.findById(userId).get(), UserDto.class);
    }
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(x -> modelMapper.map(x, UserDto.class)).collect(Collectors.toList()); 
    }
    @Override
    public UserDto updateUser(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getId()).get();
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        userRepository.save(existingUser);
        return modelMapper.map(existingUser,UserDto.class);
    }
    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}
