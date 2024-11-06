package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.FileRepository;
import ee.ivkhkdev.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private AppHelper<User> userAppHelper;

    @Mock
    private FileRepository<User> storage;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUserSuccessfully() {
        User user = new User(); // Создаем тестового пользователя
        when(userAppHelper.create()).thenReturn(user); // Мокаем создание пользователя
        doNothing().when(storage).save(user, "users"); // Мокаем сохранение пользователя

        boolean result = userService.add();

        assertTrue(result);
        verify(userAppHelper, times(1)).create();
        verify(storage, times(1)).save(user, "users");
    }

    @Test
    void testAddUserFailure() {
        when(userAppHelper.create()).thenReturn(null); // Мокаем создание пользователя, возвращая null

        boolean result = userService.add();

        assertFalse(result);
        verify(userAppHelper, times(1)).create();
        verify(storage, never()).save(any(), eq("users"));
    }

    @Test
    void testListUsers() {
        List<User> users = Collections.singletonList(new User()); // Создаем список с одним пользователем
        when(storage.load("users")).thenReturn(users); // Мокаем загрузку списка пользователей

        List<User> result = userService.list();

        verify(storage, times(1)).load("users");
        assertTrue(result.containsAll(users));
    }
}