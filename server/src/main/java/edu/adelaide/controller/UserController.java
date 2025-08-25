package edu.adelaide.controller;

import edu.adelaide.dto.CreateUserRequest;
import edu.adelaide.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST endpoints for user operations.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) { this.userService = userService; }

  /**
   * Create a new user.
   * @return JSON like {"userId": "..."}
   */
  @PostMapping
  public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateUserRequest req) {
    String userId = userService.createUser(req);
    return ResponseEntity.status(201).body(Map.of("userId", userId));
  }
}
