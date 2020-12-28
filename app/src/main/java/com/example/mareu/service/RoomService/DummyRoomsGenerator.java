package com.example.mareu.service.RoomService;

import com.example.mareu.R;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;

import java.util.Arrays;
import java.util.List;

public class DummyRoomsGenerator {

    private final static List<Room> DUMMY_ROOMS = Arrays.asList(//USE ARRAY-ITEMS
            new Room("PEACH", R.drawable.ic_pink_pic),
            new Room("MARIO", R.drawable.ic_red_pic),
            new Room("LUIGI", R.drawable.ic_green_pic),
            new Room("WALUIGI", R.drawable.ic_purple_pic),
            new Room("HARMONY", R.drawable.ic_blue_pic),
            new Room("DAISY", R.drawable.ic_orange_pic),
            new Room("TOAD", R.drawable.ic_white_pic),
            new Room("GOOMBA", R.drawable.ic_brown_pic),
            new Room("CHOMP", R.drawable.ic_black_pic),
            new Room("BONES", R.drawable.ic_grey_pic)
    );

    public static List<Room> generateRoom() {
        return DUMMY_ROOMS;
    }

}
