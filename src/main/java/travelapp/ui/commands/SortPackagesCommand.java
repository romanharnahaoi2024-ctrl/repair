package travelapp.ui.commands;

import travelapp.TravelPackage;
import travelapp.service.PackageService;

import java.util.List;
import java.util.Scanner;

public class SortPackagesCommand implements Command {
    private final PackageService service;
    private final Scanner sc = new Scanner(System.in);

    public SortPackagesCommand(PackageService service) {
        this.service = service;
    }

    @Override
    public String getName() {
        return "Сортування (за ціною, тривалістю, рейтингом, назвою)";
    }

    @Override
    public void execute() {
        while (true) {
            System.out.println("\n=== СОРТУВАННЯ ===");
            System.out.println("1. За ціною");
            System.out.println("2. За тривалістю");
            System.out.println("3. За рейтингом");
            System.out.println("4. За назвою");
            System.out.println("0. 🔙 Повернутись у головне меню");
            System.out.print("Ваш вибір: ");

            String input = sc.nextLine().trim();
            if (input.equals("0")) return;

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Введіть число!");
                continue;
            }

            String key = switch (choice) {
                case 1 -> "price";
                case 2 -> "duration";
                case 3 -> "rating";
                case 4 -> "name";
                default -> null;
            };

            if (key == null) continue;

            List<TravelPackage> sorted = service.sortBy(key);
            sorted.forEach(System.out::println);

            System.out.println("\n0. 🔙 Назад");
            if (sc.nextLine().trim().equals("0")) return;
        }
    }
}
