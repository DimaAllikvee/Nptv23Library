package org.example.interfaces.impl;

import org.example.App;
import org.example.interfaces.BookProvider;
import org.example.interfaces.InputProvider;
import org.example.model.Author;
import org.example.model.Book;

import java.util.Arrays;

public class AppBookHelper implements BookProvider {
    @Override
    public Book createBook(InputProvider inputProvider) {
        Book book = new Book();
        System.out.print("Название книги: ");
        book.setTitle(inputProvider.getInput());
        System.out.print("Количество авторов ");
        int countAuthors = Integer.parseInt(inputProvider.getInput());
        for (int i = 0; i < countAuthors; i++) {
            System.out.println("Автор: " + (i + 1));
            Author author = new Author();
            System.out.print("Имя автора: ");
            author.setAuthorname(inputProvider.getInput());
            System.out.print("Фамилия автора: ");
            author.setAuthorSurname(inputProvider.getInput());
            book.getAuthors()[i] = author;
            System.out.println();
        }
        System.out.print("Год издания: ");
        book.setPublishedYear(Integer.parseInt(inputProvider.getInput()));
        return book;
    }

    @Override
    public void getList() {
        for (int i = 0; i < App.books.length; i++) {
            Book book = App.books[i];

            if (book != null) { // Проверяем, что книга не null
                StringBuilder sbAuthorsBook = new StringBuilder();
                Author[] authors = book.getAuthors();

                // Собираем информацию об авторах
                for (Author author : authors) {
                    if (author != null) { // Проверяем, что автор не null
                        sbAuthorsBook.append(author.getAuthorname())
                                .append(" ")
                                .append(author.getAuthorSurname())
                                .append(", ");
                    }
                }

                // Удаляем последнюю запятую и пробел, если есть
                if (sbAuthorsBook.length() > 0) {
                    sbAuthorsBook.setLength(sbAuthorsBook.length() - 2); // Удаляем последнюю запятую и пробел
                }

                // Выводим информацию о книге
                System.out.printf("%d. %s. %s. %d%n",
                        i + 1,
                        book.getTitle(),
                        sbAuthorsBook.toString(), // Используем StringBuilder для вывода авторов
                        book.getPublishedYear());
            }
        }
    }
}

