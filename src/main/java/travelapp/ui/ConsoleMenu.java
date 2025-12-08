package travelapp.ui;

import travelapp.service.PackageService;
import java.util.Scanner;

public class ConsoleMenu {
    private final CommandManager manager;
    private final Scanner scanner = new Scanner(System.in);

    // ✅ конструктор із параметром PackageService
    public ConsoleMenu(PackageService service) {
        this.manager = new CommandManager(service);
    }

    public void run() {
        while (true) {
            manager.showMenu();
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Введіть число!");
                continue;
            }
            manager.executeCommand(choice);
        }
    }
}
