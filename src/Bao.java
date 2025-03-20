import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;

public class Bao< E extends Comparable<E>> {
    private String product;
    private int popularity;


    public Bao(String product, int popularity) {
        this.product = product;
        this.popularity = popularity;
    }

    public void popularity() {
        String filePath = "menuItem.csv";
        ArrayList<Bao> menuItem = new ArrayList<>();

        try(FileWriter fw = new FileWriter("menuItem", true)){
            fw.append(String.valueOf(popularity)).append("/n");
            System.out.println("Successfully wrote to the file.");
        }catch(IOException e){
            System.out.println("Error writing to file");
        }
    }
    public int getPopularity() {
        return popularity;
    }









    @Override
    public String toString() {
        return super.toString();
    }
}
