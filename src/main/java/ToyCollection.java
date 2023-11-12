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

    /**
     * Добавить игрушку
     * @param name
     * @param quantity
     * @param frequency
     */
    public void addToy(String name, int quantity, double frequency) {
        Toy toy = new Toy(name, quantity, frequency);
        toys.add(toy);
    }

    /**
     * Получить все игрушки в АрэйЛисте
     * @return
     */
    public List<Toy> getAllPrizeToys() {
        return new ArrayList<>(prizeToys);
    }

    /**
     * Инициализация
     */
    public void initializeInitialToys() {
        addToy("Кукла", 10, 20);
        addToy("Машинка", 15, 30);
        addToy("Мяч", 8, 10);
    }

    /**
     * Метод используется для получения игрушки и розыгрыша их с импользованием вероятностей
     * @return
     */
    public Toy receivePrizeToy() {
        Toy prizeToy = drawPrize();
        if (prizeToy != null) {
            System.out.println("Получена призовая игрушка: " + prizeToy);
            saveToFile(List.of(prizeToy), "prizeToys.txt");
            prizeToys.add(prizeToy);
        } else {
            System.out.println("Не удалось получить призовую игрушку.");
        }
        return prizeToy;
    }

    /**
     * Обновление частоты по ее ID (сейчас не используется)
     * @param toyId
     * @param newFrequency
     */
    public void updateFrequency(int toyId, double newFrequency) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setFrequency(newFrequency);
                break;
            }
        }
    }

    /**
     * Метод для розыгрыша игрушек исполузуется случацйное число от 0 до 100 и с помощью его и идет розыгрыш
     * @return
     */
    public Toy drawPrize() {
        Random random = new Random();
        double draw = random.nextDouble() * 100;

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
        return null;
    }

    /**
     * Получит весь список
     * @return
     */
    public List<Toy> getAllToys() {
        return new ArrayList<>(toys);
    }

    /**
     * Метод для сохранения в файл
     * @param toys
     * @param fileName
     */
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

    /**
     *Отчистка всех призовых игрушек
     */
    public void clearPrizeToysFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("prizeToys.txt"))) {
            System.out.println("Файл prizeToys.txt очищен.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при очистке файла prizeToys.txt");
        }
    }

    /**
     * Так же удаление осталось от прошлых версий программы(жалко удалять)
     * @param fileName
     */
    public void removeAllPrizeToys(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("");
            System.out.println("Все призовые игрушки удалены из файла: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при удалении призовых игрушек из файла: " + fileName);
        }
    }
}



