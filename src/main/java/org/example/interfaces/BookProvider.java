package org.example.interfaces;

import org.example.model.Book;

public interface BookProvider {
    Book create(Input input);
    String getList();
}

