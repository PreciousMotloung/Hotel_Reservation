package com.preciousmotloung.hotel.controller;

import com.preciousmotloung.hotel.model.Reservation;
import com.preciousmotloung.hotel.service.ReservationService;

import java.sql.SQLException;
import java.util.List;

public class ReservationController {
    private ReservationService reservationService;

    //Constructor initializes the service
    public ReservationController() throws SQLException {
        this.reservationService = new ReservationService();
    }

    //Method to add a reservation
    public void addReservation(String guestName,int roomNumber,String contactNumber)
    {
        try{
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

    public void viewAllReservations()
    {
        try {
            List<Reservation> reservations = reservationService.viewAllReservation();

            System.out.println("Current Reservations:");
            System.out.println();
            System.out.println("-".repeat(95)+"+");
            System.out.println("| Reservation ID | Guest Name       | Room Number | Contact Number    | Reservation " +
                    "Date        |");
            for(Reservation reservation:reservations)
            {
                System.out.println(reservation);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("-".repeat(95)+"+");
        System.out.println();
    }

    public void viewReservation(int reservationId)
    {
        try{
            Reservation reservation = reservationService.viewReservation(reservationId);
            if(reservation != null)
            {
                System.out.println("Reservation Details: "+reservation);
            }else {
                System.out.println("Reservation not found.");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateReservation(int reservationId,String guestName,int roomNumber,String contactNumber)
    {
        try{
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

    public void deleteReservation(int reservationId) throws Exception {
        try{
            reservationService.deleteReservation(reservationId);
            System.out.println("Reservation deleted successfully");
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
