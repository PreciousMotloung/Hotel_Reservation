
import com.preciousmotloung.hotel.model.Reservation;
import org.junit.Before;
import org.junit.Test;
import java.sql.Timestamp;
import static org.junit.Assert.assertEquals;

public class ReservationTest {

    private Reservation reservation;
    private final int reservationId = 1;
    private final String guestName = "Precious Motloung";
    private final int roomNumber = 101;
    private final String contactNumber = "0637478561";
    private final Timestamp reservationDate = Timestamp.valueOf("2024-08-05 10:00:00");

    @Before
    public void setUp() {
        reservation = new Reservation();
        reservation.setReservationId(reservationId);
        reservation.setGuestName(guestName);
        reservation.setRoomNumber(roomNumber);
        reservation.setContactNumber(contactNumber);
        reservation.setReservationDate(reservationDate);
    }

    @Test
    public void testGetReservationId() {
        assertEquals(reservationId, reservation.getReservationId());
    }

    @Test
    public void testSetReservationId() {
        int newReservationId = 2;
        reservation.setReservationId(newReservationId);
        assertEquals(newReservationId, reservation.getReservationId());
    }

    @Test
    public void testGetGuestName() {
        assertEquals(guestName, reservation.getGuestName());
    }

    @Test
    public void testSetGuestName() {
        String newGuestName = "Precious Motloung";
        reservation.setGuestName(newGuestName);
        assertEquals(newGuestName, reservation.getGuestName());
    }

    @Test
    public void testGetRoomNumber() {
        assertEquals(roomNumber, reservation.getRoomNumber());
    }

    @Test
    public void testSetRoomNumber() {
        int newRoomNumber = 102;
        reservation.setRoomNumber(newRoomNumber);
        assertEquals(newRoomNumber, reservation.getRoomNumber());
    }

    @Test
    public void testGetContactNumber() {
        assertEquals(contactNumber, reservation.getContactNumber());
    }

    @Test
    public void testSetContactNumber() {
        String newContactNumber = "0637478561";
        reservation.setContactNumber(newContactNumber);
        assertEquals(newContactNumber, reservation.getContactNumber());
    }

    @Test
    public void testGetReservationDate() {
        assertEquals(reservationDate, reservation.getReservationDate());
    }

    @Test
    public void testSetReservationDate() {
        Timestamp newReservationDate = Timestamp.valueOf("2024-08-06 11:00:00");
        reservation.setReservationDate(newReservationDate);
        assertEquals(newReservationDate, reservation.getReservationDate());
    }

}
