package com.dev.SpringBootRest.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.dev.SpringBootRest.entity.User;
import com.dev.SpringBootRest.repo.UserRepository;
import com.dev.SpringBootRest.service.UserService;
import com.dev.dto.UserDto;
import com.dev.exception.EmailAlreadyExistsException;
import com.dev.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private ModelMapper modelMapper;
    private UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        User emailUser = userRepository.findByEmail(userDto.getEmail());
        if(emailUser!=null){
            throw new EmailAlreadyExistsException("Email Already Exists For User");
        }
        
        User user = modelMapper.map(userDto,User.class);
        User savedUser = userRepository.save(user);
        UserDto updatedUserDto = modelMapper.map(savedUser, UserDto.class);
        return updatedUserDto;
    }
    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", userId)
        );
        return modelMapper.map(user, UserDto.class);
    }
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(x -> modelMapper.map(x, UserDto.class)).collect(Collectors.toList()); 
    }
    @Override
    public UserDto updateUser(UserDto userDto) {
        
        User user = userRepository.findById(userDto.getId()).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", userDto.getId())
        );
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);
        return modelMapper.map(user,UserDto.class);
    }
    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", userId)
        );
        userRepository.deleteById(userId);
    }

}
