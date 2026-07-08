package com.crm.lead.service;

import com.crm.lead.dto.UserDTOs.*;
import java.util.List;

public interface UserService {
    UserResponse login(LoginRequest request);
    UserResponse createUser(UserRequest request);
    UserResponse updateUser(Long id, UserRequest request);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    void deleteUser(Long id);
    void updateUserStatus(Long id, String status);
}