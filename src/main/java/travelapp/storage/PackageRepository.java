package travelapp.storage;

import travelapp.Booking;
import travelapp.MealPlan;
import travelapp.Transport;
import travelapp.TravelPackage;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public class PackageRepository {
    private final Path packagesPath;
    private final Path bookingsPath;
    private final List<TravelPackage> packages = new ArrayList<>();
    private final List<Booking> bookings = new ArrayList<>();

    public PackageRepository(String packagesFile, String bookingsFile) {
        this.packagesPath = Paths.get(packagesFile);
        this.bookingsPath = Paths.get(bookingsFile);
    }

    public void load() throws IOException {
        packages.clear();
        bookings.clear();

        if (Files.exists(packagesPath)) {
            try (BufferedReader br = Files.newBufferedReader(packagesPath)) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] p = line.split(";");
                    packages.add(new TravelPackage(
                            Integer.parseInt(p[0]), p[1], p[2],
                            Integer.parseInt(p[3]), Double.parseDouble(p[4]),
                            Transport.valueOf(p[5]), MealPlan.valueOf(p[6]),
                            Integer.parseInt(p[7]), Double.parseDouble(p[8])
                    ));
                }
            }
        }

        if (Files.exists(bookingsPath)) {
            try (BufferedReader br = Files.newBufferedReader(bookingsPath)) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] b = line.split(";");
                    bookings.add(new Booking(
                            Integer.parseInt(b[0]), Integer.parseInt(b[1]),
                            b[2], b[3], LocalDate.parse(b[4]),
                            LocalDate.parse(b[5]), Integer.parseInt(b[6]),
                            Double.parseDouble(b[7])
                    ));
                }
            }
        }
    }

    public void save() throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(packagesPath)) {
            for (TravelPackage tp : packages) {
                bw.write(String.join(";", String.valueOf(tp.getId()), tp.getName(), tp.getType(),
                        String.valueOf(tp.getDurationDays()), String.valueOf(tp.getBasePrice()),
                        tp.getTransport().name(), tp.getMealPlan().name(),
                        String.valueOf(tp.getAvailableSeats()), String.valueOf(tp.getRating())));
                bw.newLine();
            }
        }

        try (BufferedWriter bw = Files.newBufferedWriter(bookingsPath)) {
            for (Booking b : bookings) {
                bw.write(String.join(";", String.valueOf(b.getId()), String.valueOf(b.getPackageId()),
                        b.getCustomerName(), b.getContactInfo(), b.getStartDate().toString(),
                        b.getEndDate().toString(), String.valueOf(b.getSeatsBooked()), String.valueOf(b.getTotalPrice())));
                bw.newLine();
            }
        }
    }

    public List<TravelPackage> listPackages() { return Collections.unmodifiableList(packages); }
    public List<Booking> listBookings() { return Collections.unmodifiableList(bookings); }

    public Optional<TravelPackage> findPackageById(int id) {
        return packages.stream().filter(p -> p.getId() == id).findFirst();
    }

    public void updatePackage(TravelPackage updated) {
        for (int i = 0; i < packages.size(); i++) {
            if (packages.get(i).getId() == updated.getId()) {
                packages.set(i, updated);
                return;
            }
        }
        packages.add(updated);
    }

    public void addBooking(Booking booking) { bookings.add(booking); }
    public int nextBookingId() { return bookings.stream().mapToInt(Booking::getId).max().orElse(0) + 1; }
}
