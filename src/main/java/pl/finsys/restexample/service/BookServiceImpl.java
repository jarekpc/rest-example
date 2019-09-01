package pl.finsys.restexample.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pl.finsys.restexample.domain.Book;
import pl.finsys.restexample.service.exception.BookAlreadyExistsException;
import pl.finsys.restexample.repository.BookRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Validated
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book saveBook(@NotNull @Valid Book book) {
        log.info("Creating {}", book);
        List<Book> myList = new ArrayList<>();
        Optional<Book> existing = repository.findById(book.getId());
        if (existing.isPresent()) {
            throw new BookAlreadyExistsException(String.format("There already exists a book with id =%s", book.getId()));
        }
        return repository.save(book);
    }

    @Override
    public List<Book> getList() {
        log.debug("Retrieving the list of all users");
        return repository.findAll();
    }

    @Override
    public Book getBook(Long bookId) {
        return repository.findById(bookId).get();
    }

    @Override
    public void deleteBook(Long bookId) {
        repository.deleteById(bookId);
    }
}
