package com.preciousmotloung.hotel.dao;

import com.preciousmotloung.hotel.model.Reservation;

import java.util.List;

public interface ReservationDAO {

    // Method to add a reservation
    void addReservation(Reservation reservation) throws Exception;

    //Method to get a reservation by ID
    Reservation getReservation(int reservationId) throws Exception;

    //Method to get all reservations
    List<Reservation> getAllReservation() throws Exception;

    //Method to update a reservation
    void updateReservation(Reservation reservation) throws Exception;

    //Method to delete a reservation by ID
    void deleteReservation(int reservationId) throws Exception;

}
