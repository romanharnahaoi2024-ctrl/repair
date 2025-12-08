package travelapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import travelapp.Booking;
import travelapp.MealPlan;
import travelapp.Transport;
import travelapp.TravelPackage;
import travelapp.model.*;
import travelapp.storage.PackageRepository;

import java.nio.file.Files;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PackageServiceTest {

    PackageRepository repo;
    PackageService service;

    @BeforeEach
    void setup() throws Exception {
        repo = new PackageRepository(
                Files.createTempFile("pkg", ".csv").toString(),
                Files.createTempFile("book", ".csv").toString()
        );
        service = new PackageService(repo);

        repo.updatePackage(new TravelPackage(
                1, "Turkey", "Sea", 7, 500,
                Transport.PLANE, MealPlan.AI, 20, 4.5
        ));
    }

    @Test
    void testFilterWorks() {
        var list = service.filter("Sea", Transport.PLANE, MealPlan.AI, 0, 0);
        assertEquals(1, list.size());
    }

    @Test
    void testSortByPrice() {
        repo.updatePackage(new TravelPackage(
                2, "Egypt", "Sea", 10, 300,
                Transport.PLANE, MealPlan.AI, 20, 4.7
        ));

        var sorted = service.sortBy("price");
        assertEquals(300, sorted.get(0).getBasePrice());
    }

    @Test
    void testBookSuccess() {
        Booking b = service.book(
                1, "Roman", "111",
                LocalDate.now(), LocalDate.now().plusDays(7),
                2
        );

        assertEquals(2 * 500, b.getTotalPrice());
        assertEquals(18, repo.findPackageById(1).get().getAvailableSeats());
        assertEquals(1, repo.listBookings().size());
    }

    @Test
    void testBookFailsIfNotEnoughSeats() {
        assertThrows(IllegalArgumentException.class, () ->
                service.book(1, "Test", "000",
                        LocalDate.now(), LocalDate.now(), 999)
        );
    }
}
