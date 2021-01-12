package com.example.mareu.service.UserService;

import com.example.mareu.R;
import com.example.mareu.model.Room;
import com.example.mareu.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DummyUsersGenerator {

    private final static List<User> DUMMY_USERS = Arrays.asList(
            new User("Blue", "Fox"),
            new User("Red", "Rabbit"),
            new User("Green", "Panda"),
            new User("Pink", "Platypus"),
            new User("Purple", "Frog"),
            new User("Yellow", "Spider"),
            new User("Black", "Bird"),
            new User("Orange", "Turtle"),
            new User("White", "Snake"),
            new User("Grey", "Squirrel"),
            new User("Rainbow", "Koala")
    );

    public static List<User> generateUsers() {
        return DUMMY_USERS;
    }

    public static List<String> generateUserEmails() {
        List<String> emails = new ArrayList<>();
        int rand = (int) (Math.random() * 10);
        emails.add(DUMMY_USERS.get(rand).getEmail());
         rand = (int) (Math.random() * 10);
        emails.add(DUMMY_USERS.get(rand).getEmail());
        rand = (int) (Math.random() * 10);
        emails.add(DUMMY_USERS.get(rand).getEmail());
        return emails;
    }
}
