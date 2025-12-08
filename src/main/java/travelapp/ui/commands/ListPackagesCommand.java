package travelapp.ui.commands;

import travelapp.TravelPackage;
import travelapp.service.PackageService;
import java.util.Scanner;

public class ListPackagesCommand implements Command {
    private final PackageService service;
    private final Scanner sc = new Scanner(System.in);

    public ListPackagesCommand(PackageService service) {
        this.service = service;
    }

    @Override
    public String getName() {
        return "Показати всі путівки";
    }

    @Override
    public void execute() {
        while (true) {
            System.out.println("\n=== КАТАЛОГ ПУТІВОК ===");
            if (service.getAll().isEmpty()) {
                System.out.println("(Каталог порожній)");
            } else {
                for (TravelPackage tp : service.getAll()) {
                    System.out.println(tp);
                }
            }
            System.out.println("\n0. 🔙 Повернутись у головне меню");
            System.out.print("Ваш вибір: ");
            if (sc.nextLine().trim().equals("0")) return;
        }
    }
}
