package ru.practicum.shareit.user;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserServiceImpl;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {
    final UserServiceImpl userServiceImpl;

    final String pathUserId = "/{userId}";

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping
    public UserDto addNewUser(@RequestBody User user) {
        log.info("Поступил запрос на создание нового пользователя");
        return userServiceImpl.addUser(user);
    }


    @PatchMapping(pathUserId)
    public UserDto updateUser(@PathVariable long userId, @RequestBody User user) {
        log.info(String.format("%s %d", "Поступил запрос на изменение пользователя с id =", userId));
        return userServiceImpl.updateUser(user, userId);
    }


    @GetMapping
    public List<UserDto> getAllUsers() {
        log.info("Поступил запрос на вывод всех пользователей");
        return userServiceImpl.getAllUsers();
    }

    @GetMapping(pathUserId)
    public UserDto getUserById(@PathVariable long userId) {
        log.info(String.format("%s %d", "Поступил запрос на вывод пользователя с id =", userId));
        return userServiceImpl.getUserById(userId);
    }


    @DeleteMapping(pathUserId)
    public void deleteUser(@PathVariable long userId) {
        log.info(String.format("%s %d", "Поступил запрос на удаление пользователя с id =", userId));
        userServiceImpl.deleteUser(userId);
    }
}