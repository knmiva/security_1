package ru.jm.springmvc.service;

import java.util.List;

import ru.jm.springmvc.model.User;

public interface UserService {

    void updateUser(User user, String editPassword, String[] checkboxRoles);
    void deleteUser(Long id);
    User getUserById(Long id);
    List<User> getAllUsers();
    void addUser(User user, String[] checkboxRoles);
    User findUserByUsername(String username);

}
