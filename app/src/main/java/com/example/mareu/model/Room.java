package com.example.mareu.model;

public class Room {

    private String mName;
    private int mPicId;

    public Room(String name, int picId) {
        this.mName = name;
        this.mPicId = picId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmPicId() {
        return mPicId;
    }

    public void setmPicId(int mPicId) {
        this.mPicId = mPicId;
    }
}
