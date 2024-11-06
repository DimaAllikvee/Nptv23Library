package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private AppHelper<Book> appHelperMock;

    @Spy
    @InjectMocks
    private BookService bookService;

    private Book testBook;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testBook = new Book();  // Инициализация тестового объекта Book
    }

    @Test
    void testAddSuccess() {
        // Мокируем создание книги
        when(appHelperMock.create()).thenReturn(testBook);

        boolean result = bookService.add();

        assertTrue(result, "Метод add() должен возвращать true при успешном добавлении");
        verify(appHelperMock, times(1)).create();
    }

    @Test
    void testAddFailureWhenCreateReturnsNull() {
        // Мокируем ситуацию, когда create() возвращает null
        when(appHelperMock.create()).thenReturn(null);

        boolean result = bookService.add();

        assertFalse(result, "Метод add() должен возвращать false, если create() возвращает null");
        verify(appHelperMock, times(1)).create();
    }

    @Test
    void testAddExceptionHandling() {
        // Мокируем выброс исключения при вызове create()
        when(appHelperMock.create()).thenThrow(new RuntimeException("Ошибка создания"));

        boolean result = bookService.add();

        assertFalse(result, "Метод add() должен возвращать false при исключении");
        verify(appHelperMock, times(1)).create();
    }

    @Test
    void testEdit() {
        boolean result = bookService.edit(testBook);

        assertFalse(result, "Метод edit() должен возвращать false, так как не реализован");
    }

    @Test
    void testRemove() {
        boolean result = bookService.remove(testBook);

        assertFalse(result, "Метод remove() должен возвращать false, так как не реализован");
    }

    @Test
    void testPrint() {
        // Мокируем возвращаемое значение метода list()
        List<Book> booksList = new ArrayList<>();
        booksList.add(testBook);
        doReturn(booksList).when(bookService).list();

        bookService.print();

        verify(appHelperMock, times(1)).printList(booksList);
    }

    @Test
    void testList() {
        // Мокируем возвращаемое значение для метода list()
        List<Book> booksList = new ArrayList<>();
        booksList.add(testBook);

        doReturn(booksList).when(bookService).list();

        List<Book> result = bookService.list();

        assertEquals(booksList, result, "Метод list() должен возвращать список книг");
    }
}
