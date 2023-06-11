package ru.geekbrains.lesson5.views;

import ru.geekbrains.lesson5.models.Table;
import ru.geekbrains.lesson5.presenters.View;
import ru.geekbrains.lesson5.presenters.ViewObserver;
import ru.geekbrains.lesson5.models.Reservation;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Random;
import java.util.Scanner;

public class BookingView implements View {
    private Scanner in;
    public BookingView(){
        in = new Scanner(System.in);
    }

    private ViewObserver observer;

    @Override
    public void setObserver(ViewObserver observer) {
        this.observer = observer;
    }

    public void show(){
        Random r = new Random();
        String [] names = {"Василий", "Ирина", "Борис", "Кристина", "Олег", "Татьяна", "Игорь"};
        int orderId;

        String menuSelected = "";
        do {
            System.out.print("\033[H\033[J");
            System.out.println("-".repeat(20));
            System.out.println("Резервирование столиков");
            System.out.println("-".repeat(20));
            System.out.println("[1] - просмотр бронирования");
            System.out.println("[2] - добавление бронирования");
            System.out.println("[3] - изменение бронирования");
            System.out.println("[4] - удаление бронирования");
            System.out.println("[x] - выход");

            menuSelected = in.next();

            switch (menuSelected){
                case "1":
                    observer.showTables();
                    break;
                case "2":
                    int table = r.nextInt(100, 106);
                    String name = names[r.nextInt(names.length-1)];
                    reservationTableEvent(getRandomDate(),table,name);
                    break;
                case "3":
                    orderId = Integer.parseInt(getStringValue("Введите ваш старый номер бронирования: "));
                    changeReservationTableEvent(orderId, getRandomDate());
                    break;
                case "4":
                    orderId = Integer.parseInt(getStringValue("Введите номер бронирования для отмены: "));
                    cancellReservationEvent(orderId);




                //    observer.onTableReservation();



            }

        } while (!menuSelected.equals("x"));


    }

    @Override
    public void updateTablesView (Collection<Table> tables) {
        for (Table table: tables) {
            System.out.print(table);
            if (table.getReservations().size() > 0) {
                System.out.println(": бронирования:");
                for (Reservation r : table.getReservations()) {
                    System.out.println("\t" + r.getId() + " " + r.getName() + " " + r.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yy HH:mm")));
                }
            } else {
                System.out.println(" - нет бронирований");
            }
        }
    }

    @Override
    public void updateTableResult(int reservationNo) {
        if (reservationNo > 0)
            System.out.printf("Столик успешно забронирован. Номер вашей брони: #%d\n", reservationNo);
    }

    public void updateBookingCancellationStatus(int reservationNo){
        if (reservationNo > 0)
            System.out.printf("Бронь: #%d успешно удалена\n", reservationNo);
    }

    /**
     * Действие клиента (пользователь нажал на кнопку бронирования), бронирование столика
     * @param reservationDate дата бронирования
     * @param tableNo номер столика
     * @param name имя клиета
     */
    public void reservationTableEvent(LocalDateTime reservationDate, int tableNo, String name){
        observer.onTableReservation(reservationDate, tableNo, name);
    }


    /**
     * Действие клиента (пользователь нажал на кнопку изменения бронирования), изменение бронирование столика
     * @param oldReservationId - номер существующего бронирования
     * @param reservationLocalDateTime - новая дата бронирования
     * //@param tableNo - не используется, так как можно узнать по orderId
     * //@param name - не используется, так как можно узнать по orderId
     */
    public void changeReservationTableEvent(int oldReservationId, LocalDateTime reservationLocalDateTime){
        observer.onTableReservationChange(oldReservationId, reservationLocalDateTime);
    }

    public void cancellReservationEvent(int reservationId){
        observer.onBookingCancellation(reservationId);
    }

    private String getStringValue(String title) {
        System.out.printf("%s", title);
        return in.next();
    }

    private LocalDateTime getRandomDate(){
        Random r = new Random();
        return LocalDateTime.of(
                LocalDateTime.now().getYear(),
                r.nextInt(1,13),
                r.nextInt(1,28),
                r.nextInt(1,24),
                r.nextInt(1,60)
                );
    }

}
