package travelapp.ui.commands;

import travelapp.MealPlan;
import travelapp.Transport;
import travelapp.TravelPackage;
import travelapp.service.PackageService;
import util.InputHelper;

import java.util.List;
import java.util.Scanner;

public class FilterPackagesCommand implements Command {
    private final PackageService service;
    private final Scanner sc = new Scanner(System.in);

    public FilterPackagesCommand(PackageService service) {
        this.service = service;
    }

    @Override
    public String getName() {
        return "Пошук / Фільтрація";
    }

    @Override
    public void execute() {
        while (true) {
            System.out.println("\n=== ФІЛЬТРАЦІЯ ПУТІВОК ===");
            System.out.println("0. 🔙 Повернутись у головне меню");
            System.out.print("Тип путівки (або Enter): ");
            String type = sc.nextLine().trim();
            if (type.equals("0")) return;

            Transport transport = InputHelper.chooseEnum(Transport.class, "Тип транспорту");
            MealPlan mealPlan = InputHelper.chooseEnum(MealPlan.class, "Тип харчування");

            double minPrice = InputHelper.readDouble("Мінімальна ціна (Enter = будь-яка): ");
            double maxPrice = InputHelper.readDouble("Максимальна ціна (Enter = будь-яка): ");

            List<TravelPackage> results = service.filter(type, transport, mealPlan, minPrice, maxPrice);

            if (results.isEmpty()) System.out.println("Нічого не знайдено.");
            else results.forEach(System.out::println);

            System.out.println("\nНатисніть Enter, щоб продовжити або 0 — щоб повернутись у меню:");
            if (sc.nextLine().trim().equals("0")) return;
        }
    }
}
