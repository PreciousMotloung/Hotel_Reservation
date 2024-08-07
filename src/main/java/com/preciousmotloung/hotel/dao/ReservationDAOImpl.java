package com.preciousmotloung.hotel.dao;

import com.preciousmotloung.hotel.model.Reservation;
import com.preciousmotloung.hotel.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the ReservationDAO interface.
 */
public class ReservationDAOImpl implements ReservationDAO {

    private Connection connection;

    /**
     * Constructs a ReservationDAOImpl and initializes the database connection.
     *
     * @throws SQLException if there is a database access error.
     */
    public ReservationDAOImpl() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    /**
     * Adds a new reservation to the database.
     *
     * @param reservation the reservation to add.
     * @throws Exception if there is an error during the operation.
     */
    @Override
    public void addReservation(Reservation reservation) throws Exception {
        String query = "INSERT INTO reservations (guest_name, room_number, contact_number) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, reservation.getGuestName());
        preparedStatement.setInt(2, reservation.getRoomNumber());
        preparedStatement.setString(3, reservation.getContactNumber());
        preparedStatement.executeUpdate();
    }

    /**
     * Retrieves a reservation by its ID.
     *
     * @param reservationId the ID of the reservation to retrieve.
     * @return the reservation if found, null otherwise.
     * @throws Exception if there is an error during the operation.
     */
    @Override
    public Reservation getReservation(int reservationId) throws Exception {
        String query = "SELECT * FROM reservations WHERE reservation_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, reservationId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Reservation reservation = new Reservation();
            reservation.setReservationId(resultSet.getInt("reservation_id"));
            reservation.setGuestName(resultSet.getString("guest_name"));
            reservation.setRoomNumber(resultSet.getInt("room_number"));
            reservation.setContactNumber(resultSet.getString("contact_number"));
            reservation.setReservationDate(resultSet.getTimestamp("reservation_date"));
            return reservation;
        }
        return null; // No reservation found
    }

    /**
     * Retrieves all reservations from the database.
     *
     * @return a list of all reservations.
     * @throws Exception if there is an error during the operation.
     */
    @Override
    public List<Reservation> getAllReservation() throws Exception {
        String query = "SELECT * FROM reservations";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Reservation> reservations = new ArrayList<>();
        while (resultSet.next()) {
            Reservation reservation = new Reservation();
            reservation.setReservationId(resultSet.getInt("reservation_id"));
            reservation.setGuestName(resultSet.getString("guest_name"));
            reservation.setRoomNumber(resultSet.getInt("room_number"));
            reservation.setContactNumber(resultSet.getString("contact_number"));
            reservation.setReservationDate(resultSet.getTimestamp("reservation_date"));
            reservations.add(reservation);
        }
        return reservations; // Return the list of reservations
    }

    /**
     * Updates an existing reservation in the database.
     *
     * @param reservation the reservation to update.
     * @throws Exception if there is an error during the operation.
     */
    @Override
    public void updateReservation(Reservation reservation) throws Exception {
        String query = "UPDATE reservations SET guest_name = ?, room_number = ?, contact_number = ? WHERE reservation_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, reservation.getGuestName());
        preparedStatement.setInt(2, reservation.getRoomNumber());
        preparedStatement.setString(3, reservation.getContactNumber());
        preparedStatement.setInt(4, reservation.getReservationId());
        preparedStatement.executeUpdate();
    }

    /**
     * Deletes a reservation from the database by its ID.
     *
     * @param reservationId the ID of the reservation to delete.
     * @throws Exception if there is an error during the operation.
     */
    @Override
    public void deleteReservation(int reservationId) throws Exception {
        String query = "DELETE FROM reservations WHERE reservation_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, reservationId);
        preparedStatement.executeUpdate();
    }
}
