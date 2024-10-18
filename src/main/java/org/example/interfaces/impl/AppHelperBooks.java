package org.example.interfaces.impl;

import org.example.App;
import org.example.interfaces.BookProvider;
import org.example.interfaces.Input;
import org.example.model.Author;
import org.example.model.Book;

public class AppHelperBooks implements BookProvider {
    @Override
    public Book create(Input input) {
        Book book = new Book();
        System.out.print("Название книги: ");
        book.setTitle(input.getString());
        System.out.print("Количество авторов: ");
        int countAuthors = Integer.parseInt(input.getString());
        for (int i = 0; i < countAuthors; i++) {
            System.out.println("Автор: " + (i + 1));
            Author author = new Author();
            System.out.print("Имя автора: ");
            author.setAuthorname(input.getString());
            System.out.print("Фамилия автора: ");
            author.setAuthorSurname(input.getString());
            book.getAuthors()[i] = author;
            System.out.println();
        }
        System.out.print("Год издания: ");
        book.setPublishedYear(Integer.parseInt(input.getString()));
        return book;
    }

    @Override
    public String getList() {
        StringBuilder result = new StringBuilder(); // Create a StringBuilder to accumulate the output

        for (int i = 0; i < App.books.length; i++) {
            Book book = App.books[i];

            if (book != null) { // Check if the book is not null
                StringBuilder sbAuthorsBook = new StringBuilder();
                Author[] authors = book.getAuthors();

                // Collect author information
                for (Author author : authors) {
                    if (author != null) { // Check if the author is not null
                        sbAuthorsBook.append(author.getAuthorname())
                                .append(" ")
                                .append(author.getAuthorSurname())
                                .append(", ");
                    }
                }

                // Remove the last comma and space, if present
                if (!sbAuthorsBook.isEmpty()) {
                    sbAuthorsBook.setLength(sbAuthorsBook.length() - 2); // Remove the last comma and space
                }

                // Append the book information to the result
                result.append(String.format("%d. %s. %s. %d%n",
                        i + 1,
                        book.getTitle(),
                        sbAuthorsBook.toString(), // Use StringBuilder for author output
                        book.getPublishedYear()));
            }
        }

        return result.toString(); // Return the accumulated string
    }
}
