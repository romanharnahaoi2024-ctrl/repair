package travelapp.ui.commands;

public class ExitCommand implements Command {
    @Override
    public String getName() {
        return "Вихід";
    }

    @Override
    public void execute() {
        System.out.println("👋 Вихід із програми...");
        System.exit(0);
    }
}
