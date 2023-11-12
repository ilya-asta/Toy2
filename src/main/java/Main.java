import javax.swing.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ToyCollection toyCollection = new ToyCollection();
            toyCollection.initializeInitialToys();

            // Проводим розыгрыш призовой игрушки
            toyCollection.receivePrizeToy();
        });

        ToyCollection toyCollection = new ToyCollection();
        toyCollection.initializeInitialToys();

        toyCollection.addToy("Кукла", 10, 20);
        toyCollection.addToy("Машинка", 15, 30);
        toyCollection.addToy("Мяч", 8, 10);

        toyCollection.updateFrequency(1, 15); // Обновляем частоту для куклы

        Scanner scanner = new Scanner(System.in);

        // Цикл обработки команд пользователя
        while (true) {
            System.out.println("Выберите команду:");
            System.out.println("1. Получить игрушку");
            System.out.println("2. Показать все игрушки");
            System.out.println("3. Выйти");
            System.out.println("4. Удалить все игрушки");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Toy prizeToy = toyCollection.drawPrize();
                    if (prizeToy != null) {
                        System.out.println("Получена призовая игрушка: " + prizeToy);
                        toyCollection.saveToFile(List.of(prizeToy), "prizeToys.txt");
                    } else {
                        System.out.println("Не удалось получить призовую игрушку.");
                    }
                    break;
                case 2:
                    List<Toy> allToys = toyCollection.getAllToys();
                    System.out.println("Все игрушки: " + allToys);
                    break;
                case 3:
                    System.out.println("Программа завершена.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Некорректная команда. Попробуйте еще раз.");
                    break;
                case 4:
                    toyCollection.removeAllPrizeToys("prizeToys.txt");
                    System.out.println("Игрушки удалены");

            }
        }
    }
}



