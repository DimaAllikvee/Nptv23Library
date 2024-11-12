package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Author;

import java.util.List;

public class AuthorAppHelper implements AppHelper<Author>, Input {

    public AuthorAppHelper() {
    }

    @Override
    public Author create() {
        try {
            Author author = new Author();
            System.out.print("Имя автора: ");
            author.setAuthorName(getString());
            System.out.print("Фамилия автора: ");
            author.setAuthorSurname(getString());
            return author;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Author> update(List<Author> authors) {
        try {
            printList(authors);
            System.out.println("Введите номер автора для изменения: ");
            int numberAuthor = Integer.parseInt(getString());

            if (numberAuthor < 1 || numberAuthor > authors.size()) {
                System.out.println("Неверный номер автора!");
                return authors;
            }

            System.out.println("Имя автора: " + authors.get(numberAuthor - 1).getAuthorName());
            System.out.println("Изменить имя автора? (y/n): ");
            String change = getString();
            if (change.equals("y")) {
                System.out.print("Введите новое имя автора: ");
                authors.get(numberAuthor - 1).setAuthorName(getString());
            }

            System.out.println("Фамилия автора: " + authors.get(numberAuthor - 1).getAuthorSurname());
            System.out.println("Изменить фамилию автора? (y/n): ");
            change = getString();
            if (change.equals("y")) {
                System.out.print("Введите новую фамилию автора: ");
                authors.get(numberAuthor - 1).setAuthorSurname(getString());
            }

            return authors;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean printList(List<Author> authors) {
        System.out.println("---------- Список авторов --------");
        for (int i = 0; i < authors.size(); i++) {
            Author author = authors.get(i);
            System.out.printf("%d. %s %s%n", i + 1, author.getAuthorName(), author.getAuthorSurname());
        }
        return true;
    }
}
