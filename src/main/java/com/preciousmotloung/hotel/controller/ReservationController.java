package com.preciousmotloung.hotel.controller;

import com.preciousmotloung.hotel.model.Reservation;
import com.preciousmotloung.hotel.service.ReservationService;

import java.sql.SQLException;
import java.util.List;

/**
 * ReservationController handles the API endpoints for managing hotel reservations.
 */
public class ReservationController {
    private ReservationService reservationService;

    /**
     * Constructs a ReservationController with a ReservationService.
     *
     * @throws SQLException if there is a database access error.
     */
    public ReservationController() throws SQLException {
        this.reservationService = new ReservationService();
    }

    /**
     * Adds a new reservation.
     *
     * @param guestName the name of the guest.
     * @param roomNumber the room number for the reservation.
     * @param contactNumber the contact number of the guest.
     */
    public void addReservation(String guestName, int roomNumber, String contactNumber) {
        try {
            Reservation reservation = new Reservation();
            reservation.setGuestName(guestName);
            reservation.setRoomNumber(roomNumber);
            reservation.setContactNumber(contactNumber);
            reservationService.makeReservation(reservation);
            System.out.println("Reservation added successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Displays all reservations.
     */
    public void viewAllReservations() {
        try {
            List<Reservation> reservations = reservationService.viewAllReservation();
            System.out.println("Current Reservations:");
            System.out.println("-".repeat(95) + "+");
            System.out.println("| Reservation ID | Guest Name       | Room Number | Contact Number    | Reservation Date |");
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
            System.out.println("-".repeat(95) + "+");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Displays a specific reservation by ID.
     *
     * @param reservationId the ID of the reservation to view.
     */
    public void viewReservation(int reservationId) {
        try {
            Reservation reservation = reservationService.viewReservation(reservationId);
            if (reservation != null) {
                System.out.println("Reservation Details: " + reservation);
            } else {
                System.out.println("Reservation not found.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates an existing reservation.
     *
     * @param reservationId the ID of the reservation to update.
     * @param guestName the updated name of the guest.
     * @param roomNumber the updated room number.
     * @param contactNumber the updated contact number.
     */
    public void updateReservation(int reservationId, String guestName, int roomNumber, String contactNumber) {
        try {
            Reservation reservation = new Reservation();
            reservation.setReservationId(reservationId);
            reservation.setGuestName(guestName);
            reservation.setRoomNumber(roomNumber);
            reservation.setContactNumber(contactNumber);
            reservationService.modifyReservation(reservation);
            System.out.println("Reservation updated successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes a reservation by ID.
     *
     * @param reservationId the ID of the reservation to delete.
     * @throws Exception if there is an error during deletion.
     */
    public void deleteReservation(int reservationId) throws Exception {
        try {
            reservationService.deleteReservation(reservationId);
            System.out.println("Reservation deleted successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
