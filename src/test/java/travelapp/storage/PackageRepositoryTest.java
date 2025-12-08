package travelapp.storage;

import org.junit.jupiter.api.*;
import travelapp.Booking;
import travelapp.MealPlan;
import travelapp.Transport;
import travelapp.TravelPackage;
import travelapp.model.*;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PackageRepositoryTest {

    Path pkgFile;
    Path bookFile;
    PackageRepository repo;

    @BeforeEach
    void setup() throws IOException {
        pkgFile = Files.createTempFile("packages", ".csv");
        bookFile = Files.createTempFile("bookings", ".csv");
        repo = new PackageRepository(pkgFile.toString(), bookFile.toString());
    }

    @Test
    void testListInitiallyEmpty() {
        assertTrue(repo.listPackages().isEmpty());
        assertTrue(repo.listBookings().isEmpty());
    }

    @Test
    void testAddAndFindPackage() {
        TravelPackage tp = new TravelPackage(
                1, "Turkey", "Sea", 7, 500,
                Transport.PLANE, MealPlan.AI, 20, 4.5
        );

        repo.updatePackage(tp);

        assertEquals(1, repo.listPackages().size());
        assertTrue(repo.findPackageById(1).isPresent());
    }

    @Test
    void testAddBooking() {
        Booking b = new Booking(
                1, 10, "Roman", "123",
                LocalDate.now(), LocalDate.now().plusDays(5),
                2, 1000
        );

        repo.addBooking(b);

        assertEquals(1, repo.listBookings().size());
        assertEquals(2, repo.nextBookingId());
    }

    @Test
    void testSaveAndLoad() throws Exception {
        repo.updatePackage(new TravelPackage(
                1, "Egypt", "Sea", 7, 600,
                Transport.PLANE, MealPlan.FB, 30, 4.7
        ));

        repo.addBooking(new Booking(
                1, 1, "Ivan", "321",
                LocalDate.now(), LocalDate.now().plusDays(3),
                2, 1200
        ));

        repo.save();

        PackageRepository newRepo =
                new PackageRepository(pkgFile.toString(), bookFile.toString());

        newRepo.load();

        assertEquals(1, newRepo.listPackages().size());
        assertEquals(1, newRepo.listBookings().size());
    }
}
