package org.example.interfaces;

import org.example.model.User;

public interface UserProvider {
    User create(Input input);
    String getList();
}
