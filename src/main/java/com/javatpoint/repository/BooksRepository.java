package com.javatpoint.repository;

import com.javatpoint.model.Books;
import com.javatpoint.mydb.Db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BooksRepository {

    @Autowired
    private Db db;

    public BooksRepository() {
        // Define table and columns in the constructor
        String tableName = "books";
        db=new Db();
        String[] columns = {"id", "name", "author", "price"};
        db.createTable(tableName, columns);
    }

    public Books save(Books book) {
        Map<String, Object> row = new HashMap<>();
        row.put("id", book.getBookid());
        row.put("name", book.getBookname());
        row.put("author", book.getAuthor());
        row.put("price", book.getPrice());
        db.insert("books", book.getBookid(), row);
        return book;
    }

    public Books findById(Integer id) {
        Map<String, Object> row = db.selectById("books", id);
        if (row != null) {
            Books book = new Books();
            book.setBookid((Integer) row.get("id"));
            book.setBookname((String) row.get("name"));
            book.setAuthor((String) row.get("author"));
            book.setPrice((int)row.get("price"));
            return book;
        }
        return null;
    }

    public List<Books> findAll() {
        List<Books> booksList = new ArrayList<>();
        Map<Integer, Map<String, Object>> data = db.selectAll("books");
        for (Map.Entry<Integer, Map<String, Object>> entry : data.entrySet()) {
            Integer id = entry.getKey();
            Map<String, Object> row = entry.getValue();
            if (id != -1) {  // Skip column definitions
                Books book = new Books();
                book.setBookid((Integer) row.get("id"));
                book.setBookname((String) row.get("name"));
                book.setAuthor((String) row.get("author"));
                book.setPrice((int)row.get("price"));
                booksList.add(book);
            }
        }
        return booksList;
    }

    public void deleteById(Integer id) {
        db.delete("books", id);
    }
}
