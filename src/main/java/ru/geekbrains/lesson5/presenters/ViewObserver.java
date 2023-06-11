package ru.geekbrains.lesson5.presenters;

import java.time.LocalDateTime;


public interface ViewObserver {

    void showTables();

    void onTableReservation(LocalDateTime orderDate, int tableNo, String name);

    void onTableReservationChange(int oldReservationId, LocalDateTime newReservationDate);

    void onBookingCancellation(int reservationId);
}
