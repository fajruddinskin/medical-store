package com.medicalstore.controller;

import com.medicalstore.dto.SqlRequest;
import com.medicalstore.service.SqlExecutionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sql")
public class SqlController {

    private final SqlExecutionService sqlExecutionService;

    public SqlController(SqlExecutionService sqlExecutionService) {
        this.sqlExecutionService = sqlExecutionService;
    }

    @PostMapping("/execute")
    public ResponseEntity<String> executeSql(@Valid @RequestBody SqlRequest request) {
        sqlExecutionService.execute(request.getSql());
        return ResponseEntity.ok("SQL executed successfully");
    }
}
