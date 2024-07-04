package App.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/book/")
public class BookController {

    @GetMapping(path = "/{id}")
    public BookResponse getBookById(@PathVariable long id) {
        BookResponse book = new BookResponse();
        book.setId(id);
        book.setTitle("Book #" + id);
        return book;
    }

}
