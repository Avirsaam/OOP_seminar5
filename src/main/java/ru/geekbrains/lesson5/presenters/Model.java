package ru.geekbrains.lesson5.presenters;

import ru.geekbrains.lesson5.models.Table;

import java.time.LocalDateTime;
import java.util.Collection;


public interface Model {

    /**
     * Получение списка всех столиков
     * @return
     */
    Collection<Table> loadTables();

    /**
     * Бронирование столика
     * @param reservationDate Дата бронирования
     * @param tableNo Номер столика
     * @param name Имя клиента
     * @return Номер брони
     */
    int reservationTable(LocalDateTime reservationDate, int tableNo, String name);
    int cancellReservation (int reservationId);
    int changeTableReservation(int reservationId, LocalDateTime newDate);


}
