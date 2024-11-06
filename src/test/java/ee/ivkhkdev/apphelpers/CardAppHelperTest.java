package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.Card;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardAppHelperTest {
    private Service<Book> bookService;
    private Service<User> userService;
    private CardAppHelper cardAppHelper;

    @BeforeEach
    void setUp() {
        bookService = mock(Service.class);
        userService = mock(Service.class);
        cardAppHelper = new CardAppHelper(bookService, userService);
    }

    @Test
    void testCreateCardSuccessfully() {

        Author author = new Author("Lev", "Tolstoy");
        Book book = new Book();
        book.setTitle("Voina i mir");
        book.getAuthors().add(author);
        book.setPublishedYear(2000);
        User user = new User();
        List<User> users = List.of(user);
        user.setFirstname("John");
        user.setLastname("Snow");
        user.setPhone("123456");
        List<Book> books = List.of(book);

        when(bookService.list()).thenReturn(books);
        when(userService.list()).thenReturn(users);


        CardAppHelper spyCardAppHelper = Mockito.spy(cardAppHelper);

        doReturn("1" ,"n", "1").when(spyCardAppHelper).getString();

        Card card = spyCardAppHelper.create();


        assertNotNull(card);
        assertEquals("Voina i mir", card.getBook().getTitle());
        assertEquals("John", card.getUser().getFirstname());
        assertEquals("Snow", card.getUser().getLastname());
        assertEquals(LocalDate.now(), card.getBorrowedBookDate());


    }

    @Test
    void testCreateCardWithInvalidInput(){
        when(bookService.list()).thenReturn(List.of(new Book()));
        when(userService.list()).thenReturn(List.of(new User()));


        CardAppHelper spyCardAppHelper = Mockito.spy(cardAppHelper);

        doReturn("123").when(spyCardAppHelper).getString();

        Card card = spyCardAppHelper.create();
        assertNull(card);
    }

    @Test
    void testPrintListWithCards() {
        Author author = new Author("Lev", "Tolstoy");
        Book book = new Book();
        book.setTitle("Voina i mir");
        book.getAuthors().add(author);
        book.setPublishedYear(2000);

        User user = new User();
        user.setFirstname("John");
        user.setLastname("Snow");
        user.setPhone("123456");

        Card card1 = new Card();
        card1.setBook(book);
        card1.setUser(user);
        card1.setBorrowedBookDate(null);

        List<Card> cards = List.of(card1);

        CardAppHelper spyCardAppHelper = Mockito.spy(cardAppHelper);

        spyCardAppHelper.printList(cards);
    }

    @Test
    void testPrintListNoCards() {

        List<Card> cards = List.of();

        CardAppHelper spyCardAppHelper = Mockito.spy(cardAppHelper);

        boolean result = spyCardAppHelper.printList(cards);

        assertFalse(result);
    }

    @Test
    void testReturnBookSuccessfuly() {

        Author author = new Author("Lev", "Tolstoy");
        Book book = new Book();
        book.setTitle("Voina i mir");
        book.getAuthors().add(author);
        book.setPublishedYear(2000);

        User user = new User();
        user.setFirstname("John");
        user.setLastname("Snow");
        user.setPhone("123456");

        Card card1 = new Card();
        card1.setBook(book);
        card1.setUser(user);
        card1.setReturnedBookDate(null);

        List<Card> cards = List.of(card1);

        CardAppHelper spyCardAppHelper = Mockito.spy(cardAppHelper);

        doReturn("1").when(spyCardAppHelper).getString();

        List<Card> updatedCards = spyCardAppHelper.returnBook(cards);

        assertNotNull(updatedCards);

        assertEquals(LocalDate.now(), updatedCards.get(0).getReturnedBookDate());


    }

        @Test
        void testReturnBookWithSuccessfully() {
            Author author = new Author("Lev", "Tolstoy");
            Book book = new Book();
            book.setTitle("Voina i mir");
            book.getAuthors().add(author);
            book.setPublishedYear(2000);

            User user = new User();
            user.setFirstname("John");
            user.setLastname("Snow");
            user.setPhone("123456");

            Card card1 = new Card();
            card1.setBook(book);
            card1.setUser(user);
            card1.setReturnedBookDate(null);

            List<Card> cards = List.of(card1);

            CardAppHelper spyCardAppHelper = Mockito.spy(cardAppHelper);

            doReturn("1").when(spyCardAppHelper).getString();

            List<Card> updatedCards = spyCardAppHelper.returnBook(cards);

            assertNotNull(updatedCards);
            assertEquals(LocalDate.now(), updatedCards.get(0).getReturnedBookDate());
        }
}