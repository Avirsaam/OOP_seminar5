package ru.geekbrains.lesson5.models;

import java.time.LocalDateTime;



public class Reservation {

    private static int counter = 9000;
    private final int id;

    private LocalDateTime date;
    private String name;

    public int getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    {
        id = ++counter;
    }

    public Reservation(LocalDateTime date, String name) {
        this.date = date;
        this.name = name;
    }
}
