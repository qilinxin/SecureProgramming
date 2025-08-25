package edu.adelaide.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Request body for creating a user.
 */
public class CreateUserRequest {

  // Optional: if empty, service will generate a UUID
  @Size(max = 64)
  private String userId;

  @NotBlank
  @Size(max = 50)
  private String username;

  @Email
  @Size(max = 100)
  private String email;

  @Size(max = 20)
  private String phoneNumber;

  @NotBlank
  @Size(max = 255)
  private String password; // NOTE: store hashed password in production

  // getters and setters
  public String getUserId() { return userId; }
  public void setUserId(String userId) { this.userId = userId; }

  public String getUsername() { return username; }
  public void setUsername(String username) { this.username = username; }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public String getPhoneNumber() { return phoneNumber; }
  public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }
}
