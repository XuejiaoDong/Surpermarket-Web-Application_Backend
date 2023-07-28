package com.mercury.SpringBootRestDemo.controller;

import com.mercury.SpringBootRestDemo.bean.Cart;
import com.mercury.SpringBootRestDemo.bean.User;
import com.mercury.SpringBootRestDemo.security.JwtTokenProvider;
//import com.mercury.SpringBootRestDemo.service.UserService;
import com.mercury.SpringBootRestDemo.service.CartService;
import com.mercury.SpringBootRestDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/users")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CartService cartService; // Add CartService


    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@Validated @RequestBody User user) {
        User newUser = userService.registerNewUser(user);

        // Create a new cart for the new user
        Cart newCart = new Cart();
        newCart.setCartId(newUser.getUserId());
        newCart.setUserId(newUser.getUserId());
        cartService.saveOrUpdateCart(newCart); // Save the new cart to the database

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);

    }

//    @PostMapping("/signin")
//    public ResponseEntity<?> authenticateUser(@Validated @RequestBody User loginUser) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginUser.getUserName(),
//                        loginUser.getUserPassword()
//                )
//        );
//        User user = userService.findUserByUsername(loginUser.getUserName());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtTokenProvider.generateToken(authentication,user.getUserId());
//        return ResponseEntity.ok("Bearer " + jwt);
//
//    }
//@PostMapping("/signin")
//public ResponseEntity<?> authenticateUser(@Validated @RequestBody User loginUser) {
//    Authentication authentication = authenticationManager.authenticate(
//            new UsernamePasswordAuthenticationToken(
//                    loginUser.getUserName(),
//                    loginUser.getUserPassword()
//            )
//    );
//    User user = userService.findUserByUsername(loginUser.getUserName());
//    SecurityContextHolder.getContext().setAuthentication(authentication);
//    String jwt = jwtTokenProvider.generateToken(authentication,user.getUserId());
//
//    // 将折扣信息添加到返回的数据中
//    Map<String, Object> data = new HashMap<>();
//    data.put("token", "Bearer " + jwt);
//    data.put("discount", user.getRole().getDiscount());
//
//    return ResponseEntity.ok(data);
//}

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody User loginUser) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUserName(),
                        loginUser.getUserPassword()
                )
        );
        User user = userService.findUserByUsername(loginUser.getUserName());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication,user.getUserId());

        // 将折扣信息添加到返回的数据中
        Map<String, Object> data = new HashMap<>();
        data.put("token", "Bearer " + jwt);
        data.put("discount", user.getRole().getDiscount());
        data.put("userId", user.getUserId());
        data.put("role",user.getRole());
        return ResponseEntity.ok(data);
    }



    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.findUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @Validated @RequestBody User updatedUser) {
        User existingUser = userService.findUserById(id);
        if (existingUser != null) {
            // 在这里更新用户数据
            existingUser.setUserName(updatedUser.getUserName());
            existingUser.setPhone(updatedUser.getPhone());
            existingUser.setAddress(updatedUser.getAddress());
            existingUser.setPostCode(updatedUser.getPostCode());

            // 将更新后的用户数据保存到数据库
            userService.saveUser(existingUser);

            return ResponseEntity.ok(existingUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
