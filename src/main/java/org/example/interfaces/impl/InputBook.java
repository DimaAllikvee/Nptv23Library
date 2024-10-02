package org.example.interfaces.impl;

import org.example.interfaces.BookProvider;
import org.example.interfaces.InputProvider;
import org.example.model.Author;
import org.example.model.Book;

public class InputBook implements BookProvider {
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
}
