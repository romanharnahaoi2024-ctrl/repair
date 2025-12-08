package travelapp.model;

import org.junit.jupiter.api.Test;
import travelapp.MealPlan;
import travelapp.Transport;
import travelapp.TravelPackage;

import static org.junit.jupiter.api.Assertions.*;

class TravelPackageTest {

    @Test
    void testGettersAndSetters() {
        TravelPackage tp = new TravelPackage(
                1, "Turkey", "Sea",
                7, 499.99,
                Transport.PLANE, MealPlan.AI,
                20, 4.8
        );

        assertEquals(1, tp.getId());
        assertEquals("Turkey", tp.getName());
        assertEquals("Sea", tp.getType());
        assertEquals(7, tp.getDurationDays());
        assertEquals(499.99, tp.getBasePrice());
        assertEquals(Transport.PLANE, tp.getTransport());
        assertEquals(MealPlan.AI, tp.getMealPlan());
        assertEquals(20, tp.getAvailableSeats());
        assertEquals(4.8, tp.getRating());

        tp.setAvailableSeats(15);
        assertEquals(15, tp.getAvailableSeats());
    }

    @Test
    void testToString() {
        TravelPackage tp = new TravelPackage(
                2, "Egypt", "Sea",
                10, 700,
                Transport.PLANE, MealPlan.FB,
                30, 4.6
        );

        String s = tp.toString();
        System.out.println("TOSTRING = " + s);

        assertTrue(s.contains("Egypt"));
        assertTrue(s.contains("Sea"));
        assertTrue(s.contains("10"));
        assertTrue(s.contains("700,00"));   // ← українська кома!
        assertTrue(s.contains("PLANE"));
        assertTrue(s.contains("FB"));
        assertTrue(s.contains("★4,6"));     // ← українська кома!
    }
}


