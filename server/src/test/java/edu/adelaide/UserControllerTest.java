package edu.adelaide;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.adelaide.dto.CreateUserRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test:
 * - append a random numeric suffix to username ONLY in the test
 * - call the REST endpoint to insert
 * - verify the exact username persisted in DB
 */
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper om;
  @Autowired private JdbcTemplate jdbcTemplate;

  private static String randomNumericSuffix(int digits) {
    int bound = (int) Math.pow(10, digits);
    return "_" + String.format("%0" + digits + "d",
        ThreadLocalRandom.current().nextInt(bound));
  }

  @Test
  @DisplayName("Insert user with username + random suffix; DB should persist the same value")
  void createUser_withRandomSuffix_insertsExpectedUsername() throws Exception {
    // Compose a unique username for observability (e.g., alice_0427)
    String base = "ddddd";
    String username = base + randomNumericSuffix(4);

    // Use a unique email to avoid unique-constraint conflicts
    String email = "alice+" + System.currentTimeMillis() + "@example.com";

    // Build request
    CreateUserRequest req = new CreateUserRequest();
    req.setUsername(username);
    req.setEmail(email);
    req.setPhoneNumber(null);
    req.setPassword("demo-pass");

    // Call endpoint
    mockMvc.perform(post("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(om.writeValueAsString(req)))
        .andExpect(status().isCreated());

    // Verify by querying the DB directly
    String savedUsername = jdbcTemplate.queryForObject(
        "SELECT username FROM t_user_info WHERE email = ?",
        (rs, rowNum) -> rs.getString(1),
        email
    );
    assertThat(savedUsername).isEqualTo(username);

    // Optional: clean up to keep the test environment tidy
//    jdbcTemplate.update("DELETE FROM t_user_info WHERE email = ?", email);
  }
}
