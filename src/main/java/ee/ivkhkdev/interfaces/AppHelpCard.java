package ee.ivkhkdev.interfaces;

import ee.ivkhkdev.model.Card;

import java.util.List;

public interface AppHelpCard extends AppHelper<Card>{
    List<Card> returnBook(List<Card> cards);
}
