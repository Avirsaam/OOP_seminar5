package ru.geekbrains.lesson5;

import ru.geekbrains.lesson5.models.TableModel;
import ru.geekbrains.lesson5.presenters.BookingPresenter;
import ru.geekbrains.lesson5.views.BookingView;

import java.time.LocalDateTime;


public class Program {

    /**
     * TODO: ДОМАШНЯЯ РАБОТА
     *  Метод changeReservationTable должен заработать!
     * @param args
     */
    public static void main(String[] args) {

        BookingView bookingView = new BookingView();
        TableModel tableModel = new TableModel();
        BookingPresenter bookingPresenter = new BookingPresenter(bookingView, tableModel);
        bookingView.show();

//        bookingPresenter.showTables();
//
//        bookingView.reservationTableEvent(LocalDateTime.now(), 103, "Станислав");
//        bookingPresenter.showTables();
//
//        bookingPresenter.onTableReservationChange(9001, LocalDateTime.now());
//        bookingPresenter.showTables();

        //bookingView.changeReservationTable();
    }

}
