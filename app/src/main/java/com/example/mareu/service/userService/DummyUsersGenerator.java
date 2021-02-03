package com.example.mareu.service.userService;


import com.example.mareu.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DummyUsersGenerator {

    private final static List<User> DUMMY_USERS = new ArrayList<User>(11) {
        {
            add(new User("Blue", "Fox"));
            add(new User("Red", "Rabbit"));
            add(new User("Green", "Panda"));
            add(new User("Pink", "Platypus"));
            add(new User("Purple", "Frog"));
            add(new User("Yellow", "Spider"));
            add(new User("Black", "Bird"));
            add(new User("Orange", "Turtle"));
            add(new User("White", "Snake"));
            add(new User("Grey", "Squirrel"));
            add(new User("Rainbow", "Koala"));

        }
    };

    public static List<User> generateUsers() {
        return DUMMY_USERS;
    }

    public static List<String> generateUserEmails() {
        List<String> emails = new ArrayList<>();
        Random random = new Random();
        emails.add(DUMMY_USERS.get(random.nextInt(DUMMY_USERS.size())).getEmail());
        emails.add(DUMMY_USERS.get(random.nextInt(DUMMY_USERS.size())).getEmail());
        emails.add(DUMMY_USERS.get(random.nextInt(DUMMY_USERS.size())).getEmail());
        return emails;
    }
}
