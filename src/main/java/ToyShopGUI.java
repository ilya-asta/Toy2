import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ToyShopGUI extends JFrame {

    private ToyCollection toyCollection;

    private JTextArea textArea;

    public ToyShopGUI(ToyCollection toyCollection) {
        this.toyCollection = toyCollection;

        setTitle("Детский магазин игрушек");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));

        JButton showToysButton = new JButton("Показать все игрушки");
        JButton clearFileButton = new JButton("Удалить все игрушки из файла");
        JButton getToyButton = new JButton("Получить игрушку");
        JButton exitButton = new JButton("Выход");
        JButton showPrizeToysButton = new JButton("Показать все полученные игрушки");
        JButton showRulesButton = new JButton("Показать правила и вероятности");
        buttonPanel.add(showRulesButton);

        buttonPanel.add(showPrizeToysButton);
        buttonPanel.add(showToysButton);
        buttonPanel.add(clearFileButton);
        buttonPanel.add(getToyButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        showPrizeToysButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAllPrizeToys();
            }
        });

        showRulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRulesAndProbabilities();
            }
        });

        showToysButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAllToys();
            }
        });

        clearFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFile();
            }
        });

        getToyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getToy();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void showAllToys() {
        List<Toy> allToys = toyCollection.getAllToys();
        StringBuilder displayText = new StringBuilder("Список игрушек:\n");
        for (Toy toy : allToys) {
            displayText.append(toy.getName()).append(" (").append(toy.getFrequency()).append("%)\n");
        }
        textArea.setText(displayText.toString());
    }

    private void showAllPrizeToys() {
        List<Toy> prizeToys = toyCollection.getAllPrizeToys();
        StringBuilder displayText = new StringBuilder("Полученные игрушки:\n");
        for (Toy prizeToy : prizeToys) {
            displayText.append(prizeToy.getName()).append(" (").append(prizeToy.getFrequency()).append("%)\n");
        }
        textArea.setText(displayText.toString());
    }

    private void showRulesAndProbabilities() {
        StringBuilder rulesText = new StringBuilder("Правила розыгрыша и вероятности выпадения игрушек:\n");

        List<Toy> allToys = toyCollection.getAllToys();
        double totalProbability = 0.0;

        for (Toy toy : allToys) {
            rulesText.append(toy.getName()).append(": ").append(toy.getFrequency()).append("%\n");
            totalProbability += toy.getFrequency() / 100.0;
        }

        double noToyProbability = 100.0 * (1.0 - totalProbability);
        rulesText.append("Вероятность не получить игрушку: ").append(noToyProbability).append("%\n");

        textArea.setText(rulesText.toString());
    }

    private void clearFile() {
        toyCollection.clearPrizeToysFile();
        textArea.setText("Все игрушки из файла удалены.");
    }

    private void getToy() {
        Toy prizeToy = toyCollection.receivePrizeToy();
        if (prizeToy != null) {
            textArea.setText("Получена призовая игрушка: " + prizeToy.getName());
        } else {
            textArea.setText("Не удалось получить призовую игрушку.");
        }
    }

    public static void main(String[] args) {
        ToyCollection toyCollection = new ToyCollection();
        toyCollection.initializeInitialToys();

        SwingUtilities.invokeLater(() -> {
            ToyShopGUI toyShopGUI = new ToyShopGUI(toyCollection);
            toyShopGUI.setVisible(true);
        });
    }
}


