package pl.finsys.restexample.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.finsys.restexample.domain.Book;
import pl.finsys.restexample.service.BookService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/books")
    public Book saveBook(@RequestBody @Valid final Book book){
        log.debug("Received request to create the {} ", book);
        return bookService.saveBook(book);
    }

    @ApiOperation(value = "Retrieve a list of books")
    @GetMapping("/books")
    public List<Book> listBooks(){
        log.debug("received list books");
        return bookService.getList();
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }
}
