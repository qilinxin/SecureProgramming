package edu.adelaide.client;

//import edu.adelaide.client.config.ServerApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/** Entry point for the client module. */
@SpringBootApplication
//@EnableConfigurationProperties(ServerApiProperties.class)
public class ClientApplication {
  public static void main(String[] args) {
    SpringApplication.run(ClientApplication.class, args);
  }
}
