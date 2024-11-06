package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.interfaces.Service;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

public class BookAppHelperTest {

    private BookAppHelper bookAppHelper;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private Service<Author> authorServiceMock;

    @BeforeEach
    public void setUp() {
        authorServiceMock = Mockito.mock(Service.class);
        bookAppHelper = new BookAppHelper(authorServiceMock);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testCreate() {
        // Настройка мок-сервиса, чтобы он возвращал список авторов
        Author author = new Author("Имя1", "Фамилия1");
        doReturn(Arrays.asList(author)).when(authorServiceMock).list();

        // Мокируем методы ввода
        BookAppHelper spyHelper = Mockito.spy(bookAppHelper);
        doReturn("Название книги", "n", "1", "1", "2024").when(spyHelper).getString();

        Book book = spyHelper.create();

        assertNotNull(book);
        assertEquals("Название книги", book.getTitle());
        assertEquals(1, book.getAuthors().size()); // Убедитесь, что автор добавлен
        assertEquals("Имя1", book.getAuthors().get(0).getAuthorName());
        assertEquals("Фамилия1", book.getAuthors().get(0).getAuthorSurname());
        assertEquals(2024, book.getPublishedYear());
    }


    @Test
    public void testPrintList() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Книга1", Arrays.asList(new Author("Имя1", "Фамилия1")), 2022));
        books.add(new Book("Книга2", Arrays.asList(new Author("Имя2", "Фамилия2")), 2023));

        bookAppHelper.printList(books);

        String expectedOutput1 = "1. Книга1. Имя1 Фамилия1. 2022";
        String expectedOutput2 = "2. Книга2. Имя2 Фамилия2. 2023";

        assertTrue(outContent.toString().contains(expectedOutput1));
        assertTrue(outContent.toString().contains(expectedOutput2));
    }

    @Test
    public void testCreateHandlesError() {
        // Mocking to throw an exception
        BookAppHelper spyHelper = Mockito.spy(bookAppHelper);
        doThrow(new RuntimeException("Test exception")).when(spyHelper).getString();

        Book book = spyHelper.create();

        assertNull(book);
        assertTrue(outContent.toString().contains("Error: Test exception")); // Assuming you handle the error in a way that this message appears
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        outContent.reset();
    }
}
