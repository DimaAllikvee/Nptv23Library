package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserAppHelperTest {

    @InjectMocks
    private UserAppHelper userAppHelper;

    @Mock
    private UserAppHelper inputMock;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testCreateUserSuccessfully() {
        when(inputMock.getString()).thenReturn("Ivan", "Ivanov"); // Мокаем ввод для имени и фамилии

        User user = userAppHelper.create();

        assertNotNull(user);
        assertEquals("Ivan", user.getFirstname());
        assertEquals("Ivanov", user.getLastname());
    }

    @Test
    void testPrintList() {
        List<User> users = List.of(
                new User("Ivan", "Ivanov","123456"),
                new User("Jana", "Tomme","234567")
        );

        userAppHelper.printList(users);

        String expectedOutput1 = "1. Ivan Ivanov. 123456";
        String expectedOutput2 = "2. Jana Tamme. 234567";

        assertTrue(outputStreamCaptor.toString().contains(expectedOutput1));
        assertTrue(outputStreamCaptor.toString().contains(expectedOutput2));
    }
}