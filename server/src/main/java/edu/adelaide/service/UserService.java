package edu.adelaide.service;

import edu.adelaide.dto.CreateUserRequest;

/**
 * User operations.
 */
public interface UserService {

  /**
   * Create a user record in DB.
   * If request.userId is blank, a new UUID will be generated and used as business ID.
   *
   * @return the business userId saved to DB
   */
  String createUser(CreateUserRequest request);
}
