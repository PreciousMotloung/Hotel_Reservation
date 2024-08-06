package com.preciousmotloung.hotel.dao;

import com.preciousmotloung.hotel.model.Reservation;
import com.preciousmotloung.hotel.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO{

    //Database connection instance
    private Connection connection;

    // constructor initializes the database connection
    public ReservationDAOImpl() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void addReservation(Reservation reservation) throws Exception {
    //SQL query to insert a new reservation
    String query = "INSERT INTO reservations (guest_name,room_number,contact_number) VALUES (?,?,?)";
    PreparedStatement preparedStatement = connection.prepareStatement(query);

    preparedStatement.setString(1, reservation.getGuestName());
    preparedStatement.setInt(2,reservation.getRoomNumber());
    preparedStatement.setString(3,reservation.getContactNumber());

    preparedStatement.executeUpdate();

    }

    @Override
    public Reservation getReservation(int reservationId) throws Exception {

        String query = "SELECT * FROM reservations WHERE reservation_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,reservationId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            Reservation reservation = new Reservation();
            reservation.setReservationId(resultSet.getInt("reservation_id"));
            reservation.setGuestName(resultSet.getString("guest_name"));
            reservation.setRoomNumber(resultSet.getInt("room_number"));
            reservation.setContactNumber(resultSet.getString("contact_number"));
            reservation.setReservationDate(resultSet.getTimestamp("reservation_date"));

            return  reservation;
        }

        return null; //No reservation found
    }

    @Override
    public List<Reservation> getAllReservation() throws Exception {
        //SQL query to select all reservations
        String query ="SELECT * FROM reservations";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery(); // Execute the query

        List<Reservation> reservations = new ArrayList<>();

        //Iterate through the result set and create Reservation objects
        while (resultSet.next()){
            Reservation reservation = new Reservation();
            reservation.setReservationId(resultSet.getInt("reservation_id"));
            reservation.setGuestName(resultSet.getString("guest_name"));
            reservation.setRoomNumber(resultSet.getInt("room_number"));
            reservation.setContactNumber(resultSet.getString("contact_number"));
            reservation.setReservationDate(resultSet.getTimestamp("reservation_date"));

            reservations.add(reservation); // Add to the list
        }

        return reservations; // Return the list of reservations
    }

    @Override
    public void updateReservation(Reservation reservation) throws Exception
    {
        String query = "UPDATE reservations SET guest_name = ?,room_number =?,contact_number = ? WHERE reservation_id = " +
                "?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, reservation.getGuestName());
        preparedStatement.setInt(2,reservation.getRoomNumber());
        preparedStatement.setString(3,reservation.getContactNumber());
        preparedStatement.setInt(4,reservation.getReservationId());

        preparedStatement.executeUpdate();


    }

    @Override
    public void deleteReservation(int reservationId) throws Exception {
        String query = "DELETE FROM reservations WHERE reservation_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1,reservationId);
        preparedStatement.executeUpdate();
    }

}
