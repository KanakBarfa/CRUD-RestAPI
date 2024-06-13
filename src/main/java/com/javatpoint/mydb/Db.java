package com.javatpoint.mydb;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Db {
    private final Map<String, Map<Integer, Map<String, Object>>> database = new HashMap<>();

    public void createTable(String tableName, String[] columns) {
        Map<Integer, Map<String, Object>> table = new HashMap<>();
        for (String column : columns) {
            table.put(-1, new HashMap<>()); // Use -1 as a placeholder for column definitions
        }
        database.put(tableName, table);
    }

    public void insert(String tableName, Integer id, Map<String, Object> rowData) {
        database.computeIfAbsent(tableName, k -> new HashMap<>()).put(id, rowData);
    }

    public Map<Integer, Map<String, Object>> selectAll(String tableName) {
        return database.getOrDefault(tableName, new HashMap<>());
    }

    public Map<String, Object> selectById(String tableName, Integer id) {
        return database.getOrDefault(tableName, new HashMap<>()).get(id);
    }

    public void delete(String tableName, Integer id) {
        if (database.containsKey(tableName)) {
            database.get(tableName).remove(id);
        } else {
            throw new RuntimeException("Table " + tableName + " does not exist.");
        }
    }
}
