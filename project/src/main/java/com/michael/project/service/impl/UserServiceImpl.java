package com.michael.project.service.impl;

import com.michael.project.entity.User;
import com.michael.project.payload.request.UserRequest;
import com.michael.project.payload.response.UserResponse;
import com.michael.project.repository.UserRepository;
import com.michael.project.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
        user = userRepository.save(user);

        return mapper.map(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> mapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(String id) {
        User user = getUser(id);
        return mapper.map(user, UserResponse.class);
    }

    @Override
    public String deleteUser(String id) {
        User user = getUser(id);
        userRepository.delete(user);
        return String.format("User with id %s was deleted", id);
    }

    @Override
    public UserResponse updateUser(String id, UserRequest userRequest) {
        User user = getUser(id);
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user= userRepository.save(user);
        return mapper.map(user, UserResponse.class);
    }


    private User getUser(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("User with id %s not found", id)));
    }

}
