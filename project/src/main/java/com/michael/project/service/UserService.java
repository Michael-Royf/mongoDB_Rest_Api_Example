package com.michael.project.service;

import com.michael.project.payload.request.UserRequest;
import com.michael.project.payload.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(String id);

    String deleteUser(String id);

    UserResponse updateUser(String id, UserRequest userRequest);

}
