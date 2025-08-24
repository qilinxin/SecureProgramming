package edu.adelaide;

//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application entry point for the server module.
 * - @SpringBootApplication enables component scanning and auto-configuration.
 * - @MapperScan points MyBatis to your mapper interfaces package.
 */
@SpringBootApplication
//@MapperScan("edu.adelaide.mapper")
public class ServerApplication {
  public static void main(String[] args) {
    SpringApplication.run(ServerApplication.class, args);
  }
}
