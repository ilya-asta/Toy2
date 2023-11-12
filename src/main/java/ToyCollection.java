import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToyCollection {
    private List<Toy> toys;
    private List<Toy> prizeToys;

    public ToyCollection() {
        toys = new ArrayList<>();
        prizeToys = new ArrayList<>();
    }

    public void addToy(String name, int quantity, double frequency) {
        Toy toy = new Toy(name, quantity, frequency);
        toys.add(toy);
    }
    public List<Toy> getAllPrizeToys() {
        return new ArrayList<>(prizeToys);
    }
    public void initializeInitialToys() {
        addToy("Кукла", 10, 20);
        addToy("Машинка", 15, 30);
        addToy("Мяч", 8, 10);
    }
    public Toy receivePrizeToy() {
        Toy prizeToy = drawPrize();
        if (prizeToy != null) {
            System.out.println("Получена призовая игрушка: " + prizeToy);
            saveToFile(List.of(prizeToy), "prizeToys.txt");
            prizeToys.add(prizeToy);  // Добавляем призовую игрушку в prizeToys
        } else {
            System.out.println("Не удалось получить призовую игрушку.");
        }
        return prizeToy;
    }


    public void updateFrequency(int toyId, double newFrequency) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setFrequency(newFrequency);
                break;
            }
        }
    }

    public Toy drawPrize() {
        Random random = new Random();
        double draw = random.nextDouble() * 100; // случайное число от 0 до 100

        System.out.println("Случайное число для розыгрыша: " + draw);

        double cumulativeFrequency = 0.0;
        for (Toy toy : toys) {
            cumulativeFrequency += toy.getFrequency();
            if (draw <= cumulativeFrequency && toy.getQuantity() > 0) {
                toy.setQuantity(toy.getQuantity() - 1);
                System.out.println("Выбрана игрушка: " + toy);
                return toy;
            }
        }

        System.out.println("Не удалось выбрать игрушку.");
        return null; // Если не удалось выдать игрушку
    }


    public List<Toy> getAllToys() {
        return new ArrayList<>(toys);
    }

    public void saveToFile(List<Toy> toys, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Toy toy : toys) {
                writer.write(toy.toString() + "\n");
            }
            System.out.println("Данные добавлены в файл: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при добавлении данных в файл: " + fileName);
        }
    }
    public void clearPrizeToysFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("prizeToys.txt"))) {
            // Очищаем файл, не записывая в него ничего
            System.out.println("Файл prizeToys.txt очищен.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при очистке файла prizeToys.txt");
        }
    }

    public void removeAllPrizeToys(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Очищаем файл, перезаписывая пустую строку
            writer.write("");
            System.out.println("Все призовые игрушки удалены из файла: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при удалении призовых игрушек из файла: " + fileName);
        }
    }


}


