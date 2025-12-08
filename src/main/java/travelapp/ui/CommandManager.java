package travelapp.ui;

import travelapp.service.PackageService;
import travelapp.ui.commands.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommandManager {
    private final Map<Integer, Command> commands = new LinkedHashMap<>();

    public CommandManager(PackageService service) {
        commands.put(1, new ListPackagesCommand(service));
        commands.put(2, new FilterPackagesCommand(service));
        commands.put(3, new SortPackagesCommand(service));
        commands.put(4, new ui.commands.BookPackageCommand(service));
        commands.put(5, new LoadFromFileCommand(service));
        commands.put(6, new SaveToFileCommand(service));
        commands.put(7, new HelpCommand());
        commands.put(0, new ExitCommand());
    }

    public void showMenu() {
        System.out.println("\n=== МЕНЮ ТУРИСТИЧНОГО АГЕНТСТВА ===");
        commands.forEach((num, cmd) -> System.out.println(num + ". " + cmd.getName()));
        System.out.print("Оберіть пункт: ");
    }

    public void executeCommand(int choice) {
        Command cmd = commands.get(choice);
        if (cmd == null) {
            System.out.println("❌ Невірний вибір!");
            return;
        }
        cmd.execute();
    }
}
