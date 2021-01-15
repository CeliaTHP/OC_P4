package com.example.mareu.service.UserService;

import com.example.mareu.model.Room;
import com.example.mareu.model.User;
import com.example.mareu.service.RoomService.DummyRoomsGenerator;

import java.util.List;

public class DummyUsersApi implements UsersApi {

    private List<User> users = DummyUsersGenerator.generateUsers();

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public List<String> getUserEmails() {
        return DummyUsersGenerator.generateUserEmails();
    }
}
