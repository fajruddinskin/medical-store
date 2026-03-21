package com.medicalstore.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SqlExecutionService {

    private final JdbcTemplate jdbcTemplate;

    public SqlExecutionService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void execute(String sql) {
        String normalized = sql.trim().toLowerCase();

        if (normalized.startsWith("drop")
                || normalized.contains("delete from")
                || normalized.contains("truncate")) {
            throw new IllegalArgumentException("Dangerous SQL not allowed");
        }

        jdbcTemplate.execute(sql);
    }

}
