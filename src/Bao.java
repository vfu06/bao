import java.io.FileReader;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;

public class Bao< E extends Comparable<E>> {
    private int quantity;
    private int date;
    public E i;

    ArrayList<String> items = new ArrayList<String>();
    //private Treeable<E> products;

    public int getQuantity()
    {
        String filePath = "orders.csv";
        String line;
        String splitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
                for (String quantity : data) {
                    System.out.print(quantity + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.quantity;

    }

    public int getDate()
    {
        return this.date;
    }

    public void items()
    {
        items.add("Pork buns");
        items.add("Vegan bun");
        items.add("Plain Steamed buns");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
