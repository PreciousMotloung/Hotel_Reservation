package com.preciousmotloung.hotel.service;

import com.preciousmotloung.hotel.dao.ReservationDAO;
import com.preciousmotloung.hotel.dao.ReservationDAOImpl;
import com.preciousmotloung.hotel.model.Reservation;

import java.sql.SQLException;
import java.util.List;

/**
 * Service class for managing reservations.
 */
public class ReservationService {
    private ReservationDAO reservationDAO;

    /**
     * Constructs a ReservationService with a ReservationDAO.
     *
     * @throws SQLException if there is a database access error.
     */
    public ReservationService() throws SQLException {
        this.reservationDAO = new ReservationDAOImpl();
    }

    /**
     * Checks if a room is available for reservation.
     *
     * @param roomNumber the room number to check.
     * @return true if the room is available, false otherwise.
     * @throws Exception if there is an error during the operation.
     */
    private boolean isRoomAvailable(int roomNumber) throws Exception {
        List<Reservation> reservations = reservationDAO.getAllReservation();
        for (Reservation reservation : reservations) {
            if (reservation.getRoomNumber() == roomNumber) {
                return false;
            }
        }
        return true;
    }

    /**
     * Makes a reservation if the room is available.
     *
     * @param reservation the reservation to make.
     * @throws Exception if the room is already booked or if there is an error during the operation.
     */
    public void makeReservation(Reservation reservation) throws Exception {
        if (isRoomAvailable(reservation.getRoomNumber())) {
            reservationDAO.addReservation(reservation);
        } else {
            throw new Exception("Room is already booked");
        }
    }

    /**
     * Retrieves a reservation by its ID.
     *
     * @param reservationId the ID of the reservation to retrieve.
     * @return the reservation if found.
     * @throws Exception if there is an error during the operation.
     */
    public Reservation viewReservation(int reservationId) throws Exception {
        return reservationDAO.getReservation(reservationId);
    }

    /**
     * Retrieves all reservations.
     *
     * @return a list of all reservations.
     * @throws Exception if there is an error during the operation.
     */
    public List<Reservation> viewAllReservation() throws Exception {
        return reservationDAO.getAllReservation();
    }

    /**
     * Modifies an existing reservation.
     *
     * @param reservation the reservation to modify.
     * @throws Exception if there is an error during the operation.
     */
    public void modifyReservation(Reservation reservation) throws Exception {
        reservationDAO.updateReservation(reservation);
    }

    /**
     * Deletes a reservation by its ID.
     *
     * @param reservationId the ID of the reservation to delete.
     * @throws Exception if there is an error during the operation.
     */
    public void deleteReservation(int reservationId) throws Exception {
        reservationDAO.deleteReservation(reservationId);
    }
}
