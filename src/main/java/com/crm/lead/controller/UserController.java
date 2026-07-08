package com.crm.lead.controller;

import com.crm.lead.dto.UserDTOs.LoginRequest;
import com.crm.lead.dto.UserDTOs.UserRequest;
import com.crm.lead.dto.UserDTOs.UserResponse;
import com.crm.lead.response.ApiResponse;
import com.crm.lead.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User Management APIs")
public class UserController {

	private final UserService userService;

	// Explicit constructor injection replacing Lombok's @RequiredArgsConstructor
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	@Operation(summary = "Authenticate user credentials for CRM entry")
	public ResponseEntity<ApiResponse<UserResponse>> login(@Valid @RequestBody LoginRequest request) {
		UserResponse responseData = userService.login(request);
		return ResponseEntity.ok(ApiResponse.success("Login Successful", responseData));
	}

	@PostMapping("/register")
	@Operation(summary = "Register a brand new user profile or executive into the database")
	public ResponseEntity<ApiResponse<UserResponse>> registerUser(@Valid @RequestBody UserRequest request) {
		UserResponse responseData = userService.createUser(request);
		return ResponseEntity.ok(ApiResponse.success("User registered successfully", responseData));
	}

	@GetMapping
	@Operation(summary = "Retrieve all registered user profiles")
	public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
		List<UserResponse> users = userService.getAllUsers();
		return ResponseEntity.ok(ApiResponse.success("Users retrieved successfully", users));
	}

	@GetMapping("/{id}")
	@Operation(summary = "Fetch a single user profile details by ID")
	public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable Long id) {
		UserResponse user = userService.getUserById(id);
		return ResponseEntity.ok(ApiResponse.success("User profile found", user));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Permanently remove a user from the CRM database")
	public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.ok(ApiResponse.success("User profile permanently deleted", null));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update an existing user's complete profile parameters by ID")
	public ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable Long id,
			@Valid @RequestBody UserRequest request) {

		UserResponse response = userService.updateUser(id, request);
		return ResponseEntity.ok(ApiResponse.success("User profile updated successfully", response));
	}

	@PutMapping("/{id}/status/{status}")
	@Operation(summary = "Admin toggle user account operational status via explicit path variables")
	public ResponseEntity<ApiResponse<Void>> toggleUserStatus(@PathVariable Long id, @PathVariable String status) {

		userService.updateUserStatus(id, status);
		return ResponseEntity.status(org.springframework.http.HttpStatus.OK)
				.body(ApiResponse.success("User status changed to " + status, null));
	}
}