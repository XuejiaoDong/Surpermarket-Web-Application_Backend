package com.mercury.SpringBootRestDemo.service;

import com.mercury.SpringBootRestDemo.bean.Role;
import com.mercury.SpringBootRestDemo.bean.User;
import com.mercury.SpringBootRestDemo.dao.RoleDao;
import com.mercury.SpringBootRestDemo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(User user) {
        // Get the 'user' role
        Optional<Role> userRole = roleDao.findByRoleName("user");

        // If the 'user' role is not present, you can either throw an exception or create the role
        if (userRole.isEmpty()) {
            throw new IllegalArgumentException("Role not found");
        }

        user.setRole(userRole.get());

        // Check if the raw password is null
        String rawPassword = user.getUserPassword();
        if (rawPassword == null) {
            throw new IllegalArgumentException("Raw password cannot be null");
        }

        // Encode the user's password
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setUserPassword(encodedPassword);

        // Save the new user to the database
        return userDao.save(user);
    }
    public User findUserById(int id) {
        return userDao.findById(id).orElse(null);
    }

    public User findUserByUsername(String userName) {
        return userDao.findByUserName(userName).orElse(null);
    }

    public User saveUser(User user) {
        return userDao.save(user);
    }


    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User updateUserRole(int userId, int roleId) {
        User user = userDao.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id " + userId));

        user.setRoleId(roleId);
        return userDao.save(user);
    }


}