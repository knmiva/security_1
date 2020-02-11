package ru.jm.springmvc.dao;

import java.util.List;

import ru.jm.springmvc.model.User;

public interface UserDao {

    List<User> getAllUsers();

    User getUserById(Long id);

    User findUserByUsername(String username);

    void addUser(User user);

    void deleteUser(Long id);

    void updateUser(User user);
}
