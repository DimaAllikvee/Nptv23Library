package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.FileRepository;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.storage.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorServiceTest {
    private AuthorService authorService;
    private AppHelper<Author> appHelperAuthor;
    private FileRepository<Author> storage;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        appHelperAuthor = mock(AppHelper.class);
        storage = mock(FileRepository.class);
        authorService = new AuthorService(appHelperAuthor, storage);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testAddBookSuccessfully() {
        appHelperAuthor = mock(AppHelper.class);
        storage = mock(FileRepository.class);
        authorService = new AuthorService(appHelperAuthor, storage);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testAddBookReturnsFalseWhenNull() {
        appHelperAuthor = mock(AppHelper.class);
        storage = mock(FileRepository.class);
        authorService = new AuthorService(appHelperAuthor, storage);
        System.setOut(new PrintStream(outContent));
    }



    @Test
    public void testListAuthors() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Имя1", "Фамилия1"));
        when(storage.load("authors")).thenReturn(authors);

        List<Author> result = authorService.list();

        assertEquals(authors, result);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        outContent.reset();
    }
}