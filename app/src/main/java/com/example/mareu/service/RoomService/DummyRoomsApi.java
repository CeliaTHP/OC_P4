package com.example.mareu.service.RoomService;

import com.example.mareu.model.Room;

import java.util.List;

public class DummyRoomsApi implements RoomsApi {

    private List<Room> roomList = DummyRoomsGenerator.generateRoom();

    @Override
    public List<Room> getRooms() {
        return roomList;
    }

}
