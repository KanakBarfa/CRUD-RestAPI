package com.javatpoint.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javatpoint.model.Books;
import com.javatpoint.repository.BooksRepository;
//defining the business logic
@Service
public class BooksService
{
    @Autowired
    BooksRepository booksRepository=new BooksRepository();
    //getting all books record by using the method findaAll() of CrudRepository
    public List<Books> getAllBooks()
    {
        return new ArrayList<Books>(booksRepository.findAll());
    }
    //getting a specific record by using the method findById() of CrudRepository
    public Books getBooksById(int id)
    {
        return booksRepository.findById(id);
    }
    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(Books books)
    {
        booksRepository.save(books);
    }
    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id)
    {
        booksRepository.deleteById(id);
    }
    //updating a record
    public void update(Books books, int bookid)
    {
        booksRepository.save(books);
    }
}