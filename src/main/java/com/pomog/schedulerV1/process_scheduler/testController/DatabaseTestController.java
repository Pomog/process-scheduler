package com.pomog.schedulerV1.process_scheduler.testController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class DatabaseTestController {
    
    private DataSource dataSource;
    
    public DatabaseTestController(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @GetMapping("/test-db")
    public ResponseEntity<String> testDatabaseConnection() {
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(1)) {
                String meta = connection.getMetaData().toString();
                return ResponseEntity.ok("Connected to the database! \n" + meta);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}