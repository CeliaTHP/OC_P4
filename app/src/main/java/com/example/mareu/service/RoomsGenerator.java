package com.example.mareu.service;

import com.example.mareu.R;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;

import java.util.Arrays;
import java.util.List;

public class RoomsGenerator {

    private final static List<Room> ROOM = Arrays.asList(
            new Room("Peach", R.drawable.ic_pink_pic),
            new Room("Mario", R.drawable.ic_red_pic),
            new Room("Luigi", R.drawable.ic_green_pic),
            new Room("Waluigi", R.drawable.ic_purple_pic),
            new Room("Harmony", R.drawable.ic_blue_pic),
            new Room("Daisy", R.drawable.ic_orange_pic),
            new Room("Toad", R.drawable.ic_white_pic),
            new Room("Goomba", R.drawable.ic_brown_pic),
            new Room("Chomp", R.drawable.ic_black_pic),
            new Room("Bones", R.drawable.ic_grey_pic)
    );

    public static List<Room> generateRoom() {
        return ROOM;
    }


}
