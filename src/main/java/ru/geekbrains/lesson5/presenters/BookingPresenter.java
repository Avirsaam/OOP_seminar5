package ru.geekbrains.lesson5.presenters;

import ru.geekbrains.lesson5.models.Table;

import java.time.LocalDateTime;
import java.util.Collection;


public class BookingPresenter implements ViewObserver {

    private final View bookingView;
    private final Model tableModel;

    public BookingPresenter(View bookingView, Model tableModel){
        this.bookingView = bookingView;
        this.tableModel = tableModel;
        bookingView.setObserver(this);
    }

    private Collection<Table> loadTables(){
        return tableModel.loadTables();
    }

    public void showTables(){
        bookingView.updateTablesView(loadTables());
    }

    private void showReservationTableResult(int reservationNo){
        bookingView.updateTableResult(reservationNo);
    }

    private void showBookingCancellationResult(int bookingNo){
        bookingView.updateBookingCancellationStatus(bookingNo);
    }

    @Override
    public void onTableReservation(LocalDateTime orderDate, int tableNo, String name) {
        int reservationNo = tableModel.reservationTable(orderDate, tableNo, name);
        showReservationTableResult(reservationNo);
    }


    /**
     * Обработчик события изменения бронирования
     * @param oldReservationId - номер бронирования
     * @param newReservationDate - новое время бронирования
     */
    @Override
    public void onTableReservationChange(int oldReservationId, LocalDateTime newReservationDate) {
       int newReservationId = tableModel.changeTableReservation(oldReservationId, newReservationDate);
       if (newReservationId > 0){
           showBookingCancellationResult(oldReservationId);
           showReservationTableResult(newReservationId);
       }
    }

    @Override
    public void onBookingCancellation(int reservationId) {
        int cancelled = tableModel.cancellReservation(reservationId);
        showBookingCancellationResult(cancelled);
    }
}
