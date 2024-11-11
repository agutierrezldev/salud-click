package com.agutierrezl.specialty_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectivityTestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @GetMapping("/test-connection")
    public String testConnection() {
        try {
            // Realiza una consulta simple para verificar la conectividad
            String sql = "SELECT 1";
            jdbcTemplate.queryForObject(sql, Integer.class);
            return "Database connection successful!";
        } catch (Exception e) {
            return "Database connection failed: " + e.getMessage();
        }
    }
}
