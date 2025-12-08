package travelapp.ui.commands;

import travelapp.service.PackageService;
import java.util.Scanner;

public class LoadFromFileCommand implements Command {
    private final PackageService service;
    private final Scanner sc = new Scanner(System.in);

    public LoadFromFileCommand(PackageService service) {
        this.service = service;
    }

    @Override
    public String getName() {
        return "Завантажити з файлу";
    }

    @Override
    public void execute() {
        try {
            service.load();
            System.out.println("✅ Дані успішно завантажено!");
        } catch (Exception e) {
            System.out.println("⚠️ Не вдалося завантажити: " + e.getMessage());
        }
        System.out.println("0. 🔙 Повернутись у головне меню");
        sc.nextLine();
    }
}
