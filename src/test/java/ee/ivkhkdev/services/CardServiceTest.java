package ee.ivkhkdev.services;

import ee.ivkhkdev.apphelpers.CardAppHelper;
import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.FileRepository;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.Card;
import ee.ivkhkdev.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardServiceTest {
    private CardService cardService;

    @Mock
    private AppHelper<Card> cardAppHelper;

    @Mock
    private Service<Book> bookService;

    @Mock
    private Service<User> userService;

    @Mock
    private FileRepository<Card> storage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cardService = new CardService(cardAppHelper, bookService, userService, storage);
    }



    @Test
    void testAddCardSuccessfully() {
        Card card = new Card();
        when(cardAppHelper.create()).thenReturn(card);
        boolean result = cardService.add();
        assertTrue(result);
        verify(storage, times(1)).save(card, "cards");
    }

    @Test
    void testAddCardFailsWhenCardIsNull() {
        when(cardAppHelper.create()).thenReturn(null);
        boolean result = cardService.add();
        assertFalse(result);
        verify(storage, never()).save(any(), eq("cards"));
    }

    @Test
    void testAddCardHandlesException() {
        when(cardAppHelper.create()).thenThrow(new RuntimeException("Test Exception"));

        boolean result = cardService.add();

        assertFalse(result);
        verify(storage, never()).save(any(), eq("cards"));
    }

    @Test
    void testReturnBookSuccessfully() {
        Book book = new Book();
        book.setTitle("Test Book");

        User user = new User();
        user.setFirstname("John");
        user.setLastname("Snow");

        Card card = new Card();
        card.setBook(book);
        card.setUser(user);
        card.setReturnedBookDate(null);

        List<Card> cards = List.of(new Card());


        when(storage.load("cards")).thenReturn(cards);

        when(((CardAppHelper) cardAppHelper).returnBook(any())).thenReturn(List.of(new Card()));
        List<Card> modifiedCards = List.of(new Card());

        doNothing().when(storage).saveAll(modifiedCards, "cards");
        cardService = new CardService(cardAppHelper, bookService, userService, storage);

        boolean result = cardService.returnBook();

        assertTrue(result);
        verify(storage, times(1)).saveAll(cards, "cards");
    }

    @Test
    void testReturnBookFailsWhenModifiedCardsIsNull() {
        when(storage.load("cards")).thenReturn(List.of());
        when(((CardAppHelper) cardAppHelper).returnBook(any())).thenReturn(null);

        boolean result = cardService.returnBook();

        assertFalse(result);
        verify(storage, never()).saveAll(any(), eq("cards"));
    }

    @Test
    void testPrintSuccessfully() {
        Card card = new Card();
        List<Card> cards = List.of(card);
        when(storage.load("cards")).thenReturn(cards);
        when(cardAppHelper.printList(cards)).thenReturn(true);

        boolean result = cardService.print();

        assertTrue(result);
        verify(cardAppHelper, times(1)).printList(cards);
    }

    @Test
    void testPrintFailsWhenNoCards() {
        when(storage.load("cards")).thenReturn(List.of());
        when(cardAppHelper.printList(any())).thenReturn(false);

        boolean result = cardService.print();

        assertFalse(result);
        verify(cardAppHelper, times(1)).printList(any());
    }

    @Test
    void testListReturnsLoadedCards() {
        Card card = new Card();
        List<Card> cards = List.of(card);
        when(storage.load("cards")).thenReturn(cards);

        List<Card> result = cardService.list();

        assertEquals(cards, result);
        verify(storage, times(1)).load("cards");
    }
}
