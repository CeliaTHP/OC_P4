package com.example.mareu.service.RoomService;

import com.example.mareu.R;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyRoomsGenerator {

    private final static ArrayList<Room> DUMMY_ROOMS = new ArrayList<Room>(11) {
        {
            add(new Room("PEACH", R.drawable.ic_pink_pic));
            add(new Room("MARIO", R.drawable.ic_red_pic));
            add(new Room("LUIGI", R.drawable.ic_green_pic));
            add(new Room("WALUIGI", R.drawable.ic_purple_pic));
            add(new Room("HARMONY", R.drawable.ic_blue_pic));
            add(new Room("DAISY", R.drawable.ic_orange_pic));
            add(new Room("TOAD", R.drawable.ic_white_pic));
            add(new Room("GOOMBA", R.drawable.ic_brown_pic));
            add(new Room("CHOMP", R.drawable.ic_black_pic));
            add(new Room("BONES", R.drawable.ic_grey_pic));
        }
    };

    public static ArrayList<Room> generateRoom() {
        return DUMMY_ROOMS;
    }

}
