package com.medicalstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@RestController
@RequestMapping("/api/sql")
@CrossOrigin(origins = "*")
public class SqlController {

    @Autowired
    private DataSource dataSource;

    @PostMapping("/execute")
    public ResponseEntity<Map<String, Object>> executeSql(@RequestBody Map<String, String> request) {
        String sql = request.get("sql");
        Map<String, Object> response = new HashMap<>();

        if (sql == null || sql.trim().isEmpty()) {
            response.put("success", false);
            response.put("message", "SQL query cannot be empty");
            return ResponseEntity.badRequest().body(response);
        }

        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();

            if (sql.trim().toUpperCase().startsWith("SELECT")) {
                ResultSet rs = stmt.executeQuery(sql);
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                List<String> columns = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    columns.add(metaData.getColumnName(i));
                }

                List<Map<String, Object>> rows = new ArrayList<>();
                while (rs.next()) {
                    Map<String, Object> row = new LinkedHashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        row.put(columns.get(i - 1), rs.getObject(i));
                    }
                    rows.add(row);
                }

                response.put("success", true);
                response.put("type", "SELECT");
                response.put("columns", columns);
                response.put("rows", rows);
                response.put("rowCount", rows.size());
                response.put("message", "Query executed successfully. " + rows.size() + " row(s) returned.");

                rs.close();
            } else {
                int affectedRows = stmt.executeUpdate(sql);
                response.put("success", true);
                response.put("type", "MODIFY");
                response.put("affectedRows", affectedRows);
                response.put("message", "Query executed successfully. " + affectedRows + " row(s) affected.");
            }

            stmt.close();
            return ResponseEntity.ok(response);

        } catch (SQLException e) {
            response.put("success", false);
            response.put("message", "SQL Error: " + e.getMessage());
            response.put("errorCode", e.getErrorCode());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}