package ru.jm.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jm.springmvc.dao.RoleDao;
import ru.jm.springmvc.dao.UserDao;
import ru.jm.springmvc.model.Role;
import ru.jm.springmvc.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    public void addUser(User user, String[] checkboxRoles) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();

        parseStringRoles(roles, checkboxRoles);
        user.setRoles(roles);
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user, String editPassword, String[] checkboxRoles) {

        if (editPassword.length() > 2 && editPassword.length() < 6) {
            user.setPassword(passwordEncoder.encode(editPassword));
        }
        Set<Role> roles = new HashSet<>();

        parseStringRoles(roles, checkboxRoles);
        user.setRoles(roles);
        userDao.updateUser(user);
    }

    public void parseStringRoles(Set<Role> roles, String[] checkboxRoles) {
        for (int i = 0; i < checkboxRoles.length; i++) {
            if (checkboxRoles[i].equals("ROLE_ADMIN")) {
                roles.add(roleDao.getOneRole(1L));
            }
            if (checkboxRoles[i].equals("ROLE_USER")) {
                roles.add(roleDao.getOneRole(2L));
            }
        }
    }

    @Override
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }


}