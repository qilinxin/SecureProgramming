package edu.adelaide.service.impl;

import edu.adelaide.dto.CreateUserRequest;
import edu.adelaide.entity.UserInfo;
import edu.adelaide.mapper.UserInfoMapper;
import edu.adelaide.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

/**
 * Default implementation of UserService.
 */
@Service
public class UserServiceImpl implements UserService {

  private final UserInfoMapper userInfoMapper;

  public UserServiceImpl(UserInfoMapper userInfoMapper) {
    this.userInfoMapper = userInfoMapper;
  }

  @Override
  @Transactional
  public String createUser(CreateUserRequest request) {
    String userId = (request.getUserId() == null || request.getUserId().isBlank())
        ? UUID.randomUUID().toString()
        : request.getUserId();

    // Map DTO -> Entity
    UserInfo entity = new UserInfo();
    entity.setUserId(userId);
    entity.setUsername(request.getUsername());
    entity.setEmail(request.getEmail());
    entity.setPhoneNumber(request.getPhoneNumber());
    // WARNING: In production, hash the password (bcrypt/argon2)
    entity.setPassword(request.getPassword());
    // Optional fields like role/status can be set here if you need defaults

    int rows = userInfoMapper.insertSelective(entity);
    if (rows != 1) {
      throw new IllegalStateException("Failed to insert user record.");
    }

    // If MBG configured <generatedKey>, entity.getId() will be filled here.
    // We return business userId to avoid relying on DB-generated id.
    return userId;
  }
}
