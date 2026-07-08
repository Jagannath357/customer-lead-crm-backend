package com.crm.lead.dto;

import com.crm.lead.constant.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class UserDTOs {

	public static class LoginRequest {
		@NotBlank(message = "Username is required")
		private String username;
		@NotBlank(message = "Password is required")
		private String password;

		public LoginRequest() {
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}

	public static class UserRequest {
		@NotBlank(message = "Username is required")
		private String username;
		private String password;
		@NotBlank(message = "Full name is required")
		private String fullName;
		@NotBlank(message = "Mobile is required")
		@Pattern(regexp = "^\\d{10,15}$", message = "Mobile must be between 10 to 15 digits")
		private String mobile;
		@NotBlank(message = "Email is required")
		@Email(message = "Invalid email format")
		private String email;
		@NotNull(message = "Role is required")
		private UserRole role;

		// Added status field to support explicit profile configuration edits
		private String status;

		public UserRequest() {
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public UserRole getRole() {
			return role;
		}

		public void setRole(UserRole role) {
			this.role = role;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	}

	public static class UserResponse {
		private Long id;
		private String username;
		private String fullName;
		private String mobile;
		private String email;
		private UserRole role;
		private String status;
		private LocalDateTime createdDate;

		public UserResponse() {
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public UserRole getRole() {
			return role;
		}

		public void setRole(UserRole role) {
			this.role = role;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public LocalDateTime getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(LocalDateTime createdDate) {
			this.createdDate = createdDate;
		}
	}
}