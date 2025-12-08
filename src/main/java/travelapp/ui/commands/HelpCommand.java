package travelapp.ui.commands;

import java.util.Scanner;

public class HelpCommand implements Command {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public String getName() {
        return "Довідка";
    }

    @Override
    public void execute() {
        System.out.println("""
        === ДОВІДКА ===
        Програма "Туристичні путівки" дозволяє:
         • Переглядати каталог турів
         • Фільтрувати та сортувати путівки
         • Бронювати місця
         • Завантажувати та зберігати дані у файлах
         • Виходити з програми
        """);
        System.out.println("0. 🔙 Повернутись у головне меню");
        sc.nextLine();
    }
}
