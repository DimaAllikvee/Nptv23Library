package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.model.Author;
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

class AuthorServiceTest {

    @Mock
    private AppHelper<Author> appHelperMock;

    @Spy
    @InjectMocks
    private AuthorService authorService;

    private Author testAuthor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testAuthor = new Author();
    }

    @Test
    void testAddSuccess() {
        when(appHelperMock.create()).thenReturn(testAuthor);

        boolean result = authorService.add();

        assertTrue(result, "Метод add() должен возвращать true при успешном добавлении");
        verify(appHelperMock, times(1)).create();
    }

    @Test
    void testAddFailureWhenCreateReturnsNull() {
        when(appHelperMock.create()).thenReturn(null);

        boolean result = authorService.add();

        assertFalse(result, "Метод add() должен возвращать false, если create() возвращает null");
        verify(appHelperMock, times(1)).create();
    }

    @Test
    void testAddExceptionHandling() {
        when(appHelperMock.create()).thenThrow(new RuntimeException("Ошибка создания"));

        boolean result = authorService.add();

        assertFalse(result, "Метод add() должен возвращать false при исключении");
        verify(appHelperMock, times(1)).create();
    }

    @Test
    void testEdit() {
        boolean result = authorService.edit(testAuthor);

        assertFalse(result, "Метод edit() должен возвращать false, так как не реализован");
    }

    @Test
    void testRemove() {
        boolean result = authorService.remove(testAuthor);

        assertFalse(result, "Метод remove() должен возвращать false, так как не реализован");
    }

    @Test
    void testPrint() {
        // Мокируем возвращаемое значение метода list() для спай-объекта authorService
        List<Author> authorsList = new ArrayList<>();
        authorsList.add(testAuthor);
        doReturn(authorsList).when(authorService).list();

        authorService.print();

        verify(appHelperMock, times(1)).printList(authorsList);
    }

    @Test
    void testList() {
        List<Author> authorsList = new ArrayList<>();
        authorsList.add(testAuthor);

        doReturn(authorsList).when(authorService).list();

        List<Author> result = authorService.list();

        assertEquals(authorsList, result, "Метод list() должен возвращать список авторов");
    }
}
