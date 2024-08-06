package com.preciousmotloung.hotel.model;

import java.sql.Timestamp;

public class Reservation {

    private int reservationId;
    private String guestName;
    private int roomNumber;
    private String contactNumber;
    private Timestamp reservationDate;

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getReservationId() {
        return this.reservationId;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestName() {
        return this.guestName;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber() {
        return this.roomNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }

    public void setReservationDate(Timestamp reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Timestamp getReservationDate() {
        return reservationDate;
    }

    @Override
    public String toString() {
        return String.format(
                        "+----------------|-------------------|-------------|-----------------a|---------------------" +
                                "+\n" +
                        "| %-13d | %-17s | %-11d | %-17s | %-22s |" ,
                reservationId, guestName, roomNumber, contactNumber, reservationDate
        );
    }
}
