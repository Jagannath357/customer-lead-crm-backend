package com.crm.lead.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.crm.lead.dto.UserDTOs.LoginRequest;
import com.crm.lead.dto.UserDTOs.UserRequest;
import com.crm.lead.dto.UserDTOs.UserResponse;
import com.crm.lead.entity.User;
import com.crm.lead.exception.BadRequestException;
import com.crm.lead.exception.ResourceNotFoundException;
import com.crm.lead.repository.UserRepository;
import com.crm.lead.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	// Explicit constructor injection replacing Lombok's @RequiredArgsConstructor
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public UserResponse login(LoginRequest request) {
		User user = userRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid username or password"));
		if (!user.getPassword().equals(request.getPassword())) {
			throw new BadRequestException("Invalid username or password");
		}
		return modelMapper.map(user, UserResponse.class);
	}

	@Override
	public UserResponse createUser(UserRequest request) {
		if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
			throw new BadRequestException("Password is required");
		}
		if (userRepository.existsByUsername(request.getUsername())) {
			throw new BadRequestException("Username already exists");
		}
		if (userRepository.existsByEmail(request.getEmail())) {
			throw new BadRequestException("Email already registered");
		}
		User user = modelMapper.map(request, User.class);
		User savedUser = userRepository.save(user);
		return modelMapper.map(savedUser, UserResponse.class);
	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public UserResponse updateUser(Long id, UserRequest request) {
	    com.crm.lead.entity.User user = userRepository.findById(id)
	            .orElseThrow(() -> new com.crm.lead.exception.ResourceNotFoundException("User profile not found with id: " + id));

	    // Update database parameters using manual getters and setters
	    user.setFullName(request.getFullName());
	    user.setEmail(request.getEmail());
	    user.setMobile(request.getMobile());
	    user.setRole(request.getRole());
	    if (request.getStatus() != null) {
            user.setStatus(request.getStatus()); // Add this setter inside the if block
        }
	    if (request.getPassword() != null && !request.getPassword().trim().isEmpty()) {
	        user.setPassword(request.getPassword());
	    }

	    // Save changes and return mapped response DTO
	    com.crm.lead.entity.User updatedUser = userRepository.save(user);
	    return modelMapper.map(updatedUser, UserResponse.class);
	}

	@Override
	public List<UserResponse> getAllUsers() {
		return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public UserResponse getUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		return modelMapper.map(user, UserResponse.class);
	}

	@Override
	public void deleteUser(Long id) {
		if (!userRepository.existsById(id)) {
			throw new ResourceNotFoundException("User not found");
		}
		userRepository.deleteById(id);
	}
	@Override
	@org.springframework.transaction.annotation.Transactional
	public void updateUserStatus(Long id, String status) {
	    com.crm.lead.entity.User user = userRepository.findById(id)
	            .orElseThrow(() -> new com.crm.lead.exception.ResourceNotFoundException("User not found with id: " + id));
	    
	    // Set the status value (Ensure your entity uses either String or a matching Enum type)
	    user.setStatus(status); 
	    
	    userRepository.save(user);
	}
}