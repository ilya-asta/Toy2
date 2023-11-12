import java.util.Objects;

public class Toy {
    private static int nextId = 1;

    private final int id;
    private String name;
    private int quantity;
    private double frequency;

    public Toy(String name, int quantity, double frequency) {
        this.id = nextId++;
        this.name = name;
        this.quantity = quantity;
        this.frequency = frequency;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Toy.nextId = nextId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return String.format("Toy{id=%d, name='%s', quantity=%d, frequency=%.2f}", id, name, quantity, frequency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Toy toy = (Toy) o;
        return id == toy.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
