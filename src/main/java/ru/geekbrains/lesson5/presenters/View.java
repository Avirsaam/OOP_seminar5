package ru.geekbrains.lesson5.presenters;

import ru.geekbrains.lesson5.models.Table;

import java.util.Collection;

public interface View {


    void setObserver(ViewObserver observer);

    void updateTablesView(Collection<Table> tables);

    void updateTableResult(int reservationNo);

    void show();

    void updateBookingCancellationStatus(int reservationNo);
}
