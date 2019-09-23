package com.spring.boot.user.controller;

import com.spring.boot.user.entity.User;
import com.spring.boot.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = POST, produces = "application/json")
    public int addUser(@RequestBody User user) {
        int cnt = userService.insert(user);
        return cnt;
    }

    @RequestMapping(value = "/{id}", method = DELETE, produces = "application/json")
    public int deleteUser(@PathVariable Integer id) {
        int cnt = userService.delete(id);
        return cnt;
    }

    @RequestMapping(value = "/{id}", method = PUT, produces = "application/json")
    public int updateUser(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        int cnt = userService.update(user);
        return cnt;
    }

    @RequestMapping(value = "/{id}", method = GET, produces = "application/json")
    public User getUserById(@PathVariable Integer id) {
        User user = userService.searchById(id);
        return user;
    }

    @RequestMapping(value = "", method = GET, produces = "application/json")
    public List<User> getUsers() {
        List<User> users = userService.search();
        return users;
    }
}
