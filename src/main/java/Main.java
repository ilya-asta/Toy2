import javax.swing.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ToyCollection toyCollection = new ToyCollection();
        toyCollection.initializeInitialToys();

        SwingUtilities.invokeLater(() -> {
            ToyShopGUI toyShopGUI = new ToyShopGUI(toyCollection);
            toyShopGUI.setVisible(true);
        });
    }
}










