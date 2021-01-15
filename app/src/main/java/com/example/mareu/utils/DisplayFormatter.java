package com.example.mareu.utils;

public interface DisplayFormatter {

    static String checkDisplay(int info) {
        String infoString = String.valueOf(info);
        if (info < 10)
            infoString = "0" + info;
        return infoString;
    }
}
