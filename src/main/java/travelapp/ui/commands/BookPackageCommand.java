package ui.commands;

import travelapp.Booking;
import travelapp.TravelPackage;
import travelapp.service.PackageService;
import travelapp.ui.commands.Command;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class BookPackageCommand implements Command {
    private final PackageService service;
    private final Scanner sc = new Scanner(System.in);

    public BookPackageCommand(PackageService service) {
        this.service = service;
    }

    @Override
    public String getName() {
        return "Забронювати путівку";
    }

    @Override
    public void execute() {
        while (true) {
            System.out.println("\n=== БРОНЮВАННЯ ПУТІВКИ ===");
            System.out.println("0. 🔙 Повернутись у головне меню");

            List<TravelPackage> packages = service.getAll();
            if (packages.isEmpty()) {
                System.out.println("Каталог порожній!");
                return;
            }

            for (int i = 0; i < packages.size(); i++) {
                System.out.printf("%d. %s (%.2f$ / %d днів, місць: %d)%n",
                        i + 1,
                        packages.get(i).getName(),
                        packages.get(i).getBasePrice(),
                        packages.get(i).getDurationDays(),
                        packages.get(i).getAvailableSeats());
            }

            System.out.print("Оберіть номер для бронювання (або 0 — назад): ");
            String input = sc.nextLine().trim();
            if (input.equals("0")) return;

            try {
                int choice = Integer.parseInt(input) - 1;
                if (choice < 0 || choice >= packages.size()) continue;

                TravelPackage selected = packages.get(choice);
                System.out.println("Ви обрали: " + selected.getName());

                System.out.print("Ім’я клієнта: ");
                String name = sc.nextLine();
                System.out.print("Контакт: ");
                String contact = sc.nextLine();
                System.out.print("Дата виїзду (YYYY-MM-DD): ");
                LocalDate start = LocalDate.parse(sc.nextLine());
                System.out.print("Дата повернення (YYYY-MM-DD): ");
                LocalDate end = LocalDate.parse(sc.nextLine());
                System.out.print("Кількість місць: ");
                int seats = Integer.parseInt(sc.nextLine());

                Booking booking = service.book(selected.getId(), name, contact, start, end, seats);
                System.out.println("\n✅ Успішно заброньовано!");
                System.out.println(booking);
                System.out.println("\n0. 🔙 Назад");
                if (sc.nextLine().trim().equals("0")) return;

            } catch (Exception e) {
                System.out.println("⚠️ Помилка: " + e.getMessage());
            }
        }
    }
}
