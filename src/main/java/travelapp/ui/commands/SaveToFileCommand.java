package travelapp.ui.commands;

import travelapp.service.PackageService;
import java.util.Scanner;

public class SaveToFileCommand implements Command {
    private final PackageService service;
    private final Scanner sc = new Scanner(System.in);

    public SaveToFileCommand(PackageService service) {
        this.service = service;
    }

    @Override
    public String getName() {
        return "Зберегти у файл";
    }

    @Override
    public void execute() {
        try {
            service.save();
            System.out.println("✅ Дані збережено!");
        } catch (Exception e) {
            System.out.println("⚠️ Помилка збереження: " + e.getMessage());
        }
        System.out.println("0. 🔙 Повернутись у головне меню");
        sc.nextLine();
    }
}

