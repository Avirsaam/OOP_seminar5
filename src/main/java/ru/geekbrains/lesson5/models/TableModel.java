package ru.geekbrains.lesson5.models;

import ru.geekbrains.lesson5.presenters.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.time.LocalDateTime;

public class TableModel implements Model {


    private Collection<Table> tables;
    {
        loadTables();
    }

    /**
     * Получение списка всех столиков
     * @return
     */
    public Collection<Table> loadTables(){
        if (tables == null){
            tables = new ArrayList<>();

            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
        }
        return tables;
    }

    /**
     * Бронирование столика
     * @param reservationDate Дата бронирования
     * @param tableNo Номер столика
     * @param name Имя клиента
     * @return Номер брони
     */
    public int reservationTable(LocalDateTime reservationDate, int tableNo, String name){

        for (Table table : tables) {
            if (table.getNo() == tableNo){
                Reservation reservation = new Reservation(reservationDate, name);
                table.getReservations().add(reservation);
                return reservation.getId();
            }
        }
        return -1;
    }

    /**Разработать метод самостоятельно в рамках домашнего задания
     *
     * @param reservationId - номер бронирования, которое нужно изменить
     * @param newDate - дата нового бронирования
     * @return - новый номер брони
     *  (по-хорошему можно сделать чтобы номер броне оставался прежним, и менялась только дата бронирования
     *  но в условиях задачи сказано что реализация данного метода должна использовать существующий метод
     *  создания бронирования, а он, в свою очередь, инкрементируюет автоматически номер бронирования.
     */
    public int changeTableReservation(int reservationId, LocalDateTime newDate){
        String name = "";
        int tableNo = 0;

        for (Table t: tables ) {
            for (Reservation r : t.getReservations()){
                if (reservationId == r.getId()){
                    name = r.getName();
                    tableNo = t.getNo();
                    cancellReservation(reservationId);
                    return  reservationTable(newDate, tableNo, name);
                }
            }
        }
        return -1;
    }

    public int cancellReservation(int reservationId){
        for (Table thisTable: tables ) {
            Reservation reservationFound = null;
            for (Reservation r : thisTable.getReservations()) {
                if (r.getId() == reservationId) reservationFound = r;
            }
            if (reservationFound != null){
                thisTable.getReservations().remove(reservationFound);
                return reservationId;
            }
        }
        return -1;
    }



}
