package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.Card;
import ee.ivkhkdev.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardAppHelperTest {
    private CardAppHelper cardAppHelper;
    private Service<Book> mockBookService;
    private Service<User> mockUserService;
    private Input mockInput;

    @BeforeEach
    void setUp() {// Мокаем сервисы для книг и пользователей
        mockBookService = mock(Service.class);
        mockUserService = mock(Service.class);

        // Мокаем интерфейс Input
        mockInput = mock(Input.class);

        // Инициализация CardAppHelper с моками
        cardAppHelper = new CardAppHelper(mockBookService, mockUserService) {
            @Override
            public String getString() {
                return mockInput.getString();
            }
        };
    }

    @Test
    void testCreate_CorrectCard() {
        // Настроим поведение моков для книг и пользователей
        Book book = new Book();
        book.setTitle("Мастер и Маргарита");
        User user = new User();
        user.setFirstname("Иван");
        user.setLastname("Иванов");

        when(mockBookService.list()).thenReturn(List.of(book));
        when(mockUserService.list()).thenReturn(List.of(user));

        // Мокаем ввод
        when(mockInput.getString()).thenReturn("1").thenReturn("1");

        // Создаем карту
        Card card = cardAppHelper.create();

        // Проверяем, что карта была успешно создана
        assertNotNull(card);
        assertEquals(book, card.getBook());
        assertEquals(user, card.getUser());
        assertNotNull(card.getBorrowedBookDate());  // Дата займа должна быть текущей
    }

    @Test
    void testCreate_InvalidBookNumber() {
        // Настроим поведение моков для книг и пользователей
        Book book = new Book();
        book.setTitle("Мастер и Маргарита");
        User user = new User();
        user.setFirstname("Иван");
        user.setLastname("Иванов");

        when(mockBookService.list()).thenReturn(List.of(book));
        when(mockUserService.list()).thenReturn(List.of(user));

        // Мокаем ввод с ошибочным номером книги
        when(mockInput.getString()).thenReturn("2").thenReturn("1");  // Некорректный номер книги

        // Пытаемся создать карту
        Card card = cardAppHelper.create();

        // Проверяем, что карта не была создана (возвращается null)
        assertNull(card);
    }

    @Test
    void testCreate_InvalidUserNumber() {
        // Настроим поведение моков для книг и пользователей
        Book book = new Book();
        book.setTitle("Мастер и Маргарита");
        User user = new User();
        user.setFirstname("Иван");
        user.setLastname("Иванов");

        when(mockBookService.list()).thenReturn(List.of(book));
        when(mockUserService.list()).thenReturn(List.of(user));

        // Мокаем ввод с некорректным номером пользователя
        when(mockInput.getString()).thenReturn("1").thenReturn("2");  // Некорректный номер пользователя

        // Пытаемся создать карту
        Card card = cardAppHelper.create();

        // Проверяем, что карта не была создана (возвращается null)
        assertNull(card);
    }

    @Test
    void testPrintList_ValidCards() {
        // Создаем несколько карт
        Book book1 = new Book();
        book1.setTitle("Мастер и Маргарита");
        User user1 = new User();
        user1.setFirstname("Иван");
        user1.setLastname("Иванов");

        Book book2 = new Book();
        book2.setTitle("1984");
        User user2 = new User();
        user2.setFirstname("Петр");
        user2.setLastname("Петров");

        Card card1 = new Card();
        card1.setBook(book1);
        card1.setUser(user1);
        card1.setBorrowedBookDate(LocalDate.now());

        Card card2 = new Card();
        card2.setBook(book2);
        card2.setUser(user2);
        card2.setBorrowedBookDate(LocalDate.now());

        List<Card> cards = List.of(card1, card2);

        // Перехватываем вывод в консоль
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Вызов метода printList
        boolean result = cardAppHelper.printList(cards);

        // Восстанавливаем вывод
        System.setOut(originalSystemOut);

        // Проверяем, что вывод на консоль был правильным
        String output = outputStream.toString().trim();
        assertTrue(output.contains("Мастер и Маргарита. читает: Иван Иванов"));
        assertTrue(output.contains("1984. читает: Петр Петров"));
        assertTrue(result);  // Должен вернуть true, так как есть выданные книги
    }

    @Test
    void testPrintList_EmptyCards() {
        // Пустой список карт
        List<Card> cards = new ArrayList<>();

        // Перехватываем вывод в консоль
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Вызов метода printList
        boolean result = cardAppHelper.printList(cards);

        // Восстанавливаем вывод
        System.setOut(originalSystemOut);

        // Проверяем, что вывод на консоль был правильным
        String output = outputStream.toString().trim();
        assertTrue(output.toString().contains("--------- Список выданных книг --------"));
        assertTrue(output.toString().contains("--------- Конец списка --------"));
        assertFalse(result);  // Должен вернуть false, так как нет выданных книг
    }

    @Test
    void testReturnBook_Success() {
        // Создаем список карт
        Book book = new Book();
        book.setTitle("Мастер и Маргарита");
        User user = new User();
        user.setFirstname("Иван");
        user.setLastname("Иванов");

        Card card = new Card();
        card.setBook(book);
        card.setUser(user);
        card.setBorrowedBookDate(LocalDate.now());

        List<Card> cards = new ArrayList<>();
        cards.add(card);

        // Мокаем вывод метода printList
        when(mockInput.getString()).thenReturn("1");

        // Возврат книги
        List<Card> updatedCards = cardAppHelper.returnBook(cards);

        // Проверяем, что дата возврата книги установлена
        assertNotNull(updatedCards.get(0).getReturnedBookDate());
    }

    @Test
    void testReturnBook_InvalidCardNumber() {
        // Создаем список карт
        Book book = new Book();
        book.setTitle("Мастер и Маргарита");
        User user = new User();
        user.setFirstname("Иван");
        user.setLastname("Иванов");

        Card card = new Card();
        card.setBook(book);
        card.setUser(user);
        card.setBorrowedBookDate(LocalDate.now());

        List<Card> cards = new ArrayList<>();
        cards.add(card);

        // Мокаем ввод с ошибочным номером карты
        when(mockInput.getString()).thenReturn("2");  // Некорректный номер карты

        // Пытаемся вернуть книгу
        List<Card> updatedCards = cardAppHelper.returnBook(cards);

        // Проверяем, что карта не была обновлена
        assertNull(updatedCards);
    }
}

