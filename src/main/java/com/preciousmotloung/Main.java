package com.preciousmotloung;

import com.preciousmotloung.hotel.controller.ReservationController;
import com.preciousmotloung.hotel.util.DatabaseConnection;

import java.util.Scanner;

public class Main {
    private static String getValidGuestName(Scanner scanner) {
        String guestName;
        while (true) {
            System.out.println("Enter Guest Name:");
            guestName = scanner.next();
            if (isValidGuestName(guestName)) {
                break;
            } else {
                System.out.println("Invalid Guest Name. It should not be empty.");
            }
        }
        return guestName;
    }

    private static String getValidContactNumber(Scanner scanner) {
        String contactNumber;
        while (true) {
            System.out.println("Enter Contact Number:");
            contactNumber = scanner.next();
            if (isValidContactNumber(contactNumber)) {
                break;
            } else {
                System.out.println("Invalid Contact Number. It should be a 10-digit number starting with 0.");
            }
        }
        return contactNumber;
    }

    private static int getValidRoomNumber(Scanner scanner) {
        int roomNumber;
        while (true) {
            System.out.println("Enter Room Number:");
            String roomNumberInput = scanner.next();
            try {
                roomNumber = Integer.parseInt(roomNumberInput);
                if (isValidRoomNumber(roomNumber)) {
                    break;
                } else {
                    System.out.println("Invalid Room Number. It should be between 1 and 200.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Room Number. It should be a number.");
            }
        }
        return roomNumber;
    }

    private static int getValidReservationId(Scanner scanner) {
        int reservationId;
        while (true) {
            System.out.println("Enter Reservation ID:");
            String reservationIdInput = scanner.next();
            try {
                reservationId = Integer.parseInt(reservationIdInput);
                if (isValidReservationId(reservationId)) {
                    break;
                } else {
                    System.out.println("Invalid Reservation ID. It should be a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Reservation ID. It should be a number.");
            }
        }
        return reservationId;
    }

    private static boolean isValidGuestName(String guestName) {
        return guestName != null && !guestName.trim().isEmpty();
    }

    private static boolean isValidContactNumber(String contactNumber) {
        return contactNumber != null && contactNumber.matches("0[0-9]{9}");
    }

    private static boolean isValidRoomNumber(int roomNumber) {
        return roomNumber >= 1 && roomNumber <= 200;
    }

    private static boolean isValidReservationId(int reservationId) {
        return reservationId > 0;
    }
    public static void main(String[] args) throws Exception {
        DatabaseConnection.checkDatabaseConnection();
        System.out.println();

        ReservationController reservationController = new ReservationController();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Make Reservation\n2. View Reservation\n3. View All Reservations\n4. " +
                    "Update Reservation\n5. Delete Reservation\n6. Exit\nChoose an option:");
            String option = scanner.next();

            switch (option) {
                case "1":
                    String guestName = getValidGuestName(scanner);
                    int roomNumber = getValidRoomNumber(scanner);
                    String contactNumber = getValidContactNumber(scanner);
                    reservationController.addReservation(guestName, roomNumber, contactNumber);
                    break;
                case "2":
                    int reservationId = getValidReservationId(scanner);
                    reservationController.viewReservation(reservationId);
                    break;
                case "3":
                    reservationController.viewAllReservations();
                    break;
                case "4":
                    int updatedReservationId = getValidReservationId(scanner);
                    String updatedGuestName = getValidGuestName(scanner);
                    int updatedRoomNumber = getValidRoomNumber(scanner);
                    String updatedContactNumber = getValidContactNumber(scanner);
                    reservationController.updateReservation(updatedReservationId, updatedGuestName, updatedRoomNumber,
                            updatedContactNumber);
                    break;
                case "5":
                    int deleteReservationId = getValidReservationId(scanner);
                    reservationController.deleteReservation(deleteReservationId);
                    break;
                case "6":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

}
