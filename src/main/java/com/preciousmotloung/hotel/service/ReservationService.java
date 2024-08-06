package com.preciousmotloung.hotel.service;

import com.preciousmotloung.hotel.dao.ReservationDAO;
import com.preciousmotloung.hotel.dao.ReservationDAOImpl;
import com.preciousmotloung.hotel.model.Reservation;

import java.sql.SQLException;
import java.util.List;

public class ReservationService
{
    private ReservationDAO reservationDAO;

    //Constructor initializes the DAO implementation
    public ReservationService() throws SQLException {
        this.reservationDAO = new ReservationDAOImpl();
    }

    //Helper method to check if a room is available
    private boolean isRoomAvailable(int roomNumber) throws Exception
    {
        List<Reservation> reservations = reservationDAO.getAllReservation();

        for(Reservation reservation:reservations)
        {
            if(reservation.getRoomNumber() == roomNumber){
                return false; //Room is already booked
            }
        }
        return true;// Room is available
    }

    //Method to make a reservation, ensuring room availability
    public void makeReservation(Reservation reservation) throws Exception
    {
        if(isRoomAvailable(reservation.getRoomNumber())){
            reservationDAO.addReservation(reservation);
        }else {
            throw new Exception("Room is already booked");
        }
    }

    public Reservation viewReservation(int reservationId) throws Exception
    {
        return reservationDAO.getReservation(reservationId);
    }

    public List<Reservation> viewAllReservation() throws Exception
    {
        return reservationDAO.getAllReservation();
    }

    public void modifyReservation(Reservation reservation) throws Exception
    {
        reservationDAO.updateReservation(reservation);
    }

    public void deleteReservation(int reservation) throws Exception
    {
        reservationDAO.deleteReservation(reservation);
    }



}
