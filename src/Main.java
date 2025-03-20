import java.nio.file.Files;
import java.util.List;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.*;

public class Main extends Bao{

    private static String[] menuItems = {
            "1: Pork Bao",
            "2: Vegan Bao",
            "3: Plain Steamed Bun",
            "4: Soy Milk",
            "5: Quit"
    };

    public Main(String product, int popularity) {
        super(product, popularity);
    }

    private static void displayColumns(String[] menu) {
        for (int i = 0; i < menu.length / 2; i++) {
            System.out.println(menu[i] + " " + menu[i + menu.length / 2]);
        }
        if (menu.length % 2 == 1)
            System.out.println(menu[menu.length - 1]);
    }

    public boolean login_register() {
        boolean loggedIn = false;
        String username = "";
        String password = "";
        String name = "";

        System.out.println("a: Login");
        System.out.println("b: Register");

        Scanner log = new Scanner(System.in);

        String option = log.nextLine();
        if (option.equals("a")) {
            System.out.println("Enter Username: ");
            username = log.nextLine();
            System.out.println("Enter Password: ");
            password = log.nextLine();
            List<String[]> clientData = readCSV("client.csv");
            for (String[] row : clientData) {
                if (username.equals(row[1]) && password.equals(row[2])) {
                    System.out.println("Success");
                    return true;
                }
            }
            System.out.println("Error, please try again");
        }
        if (option.equals("b")) {
            System.out.println("Enter name, username, and password");
            String filePath = "client.csv";
            List<String[]> clientData = readCSV("client.csv");
            System.out.println("Name: ");
            name = log.nextLine();
            System.out.println("Username: ");
            username = log.nextLine();
            System.out.println("Password: ");
            password = log.nextLine();

            String[] newRow = {name, username, password};
            clientData.add(newRow);

            writeCSV(filePath, clientData);
            System.out.println("Thank you for registering with us!");
            return false;
        }

        return loggedIn;
    }

    public static List<String[]> readCSV(String filePath) {
        List<String[]> clientData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("client.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                clientData.add(line.split(","));
            }
        } catch (IOException e) {
            System.out.println("File not found" + e.getMessage());
        }
        return clientData;
    }

    public static void writeCSV(String filePath, List<String[]> clientData) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : clientData) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void start() {
        String pBaoQuantity = "";
        String vBaoQuantity = "";
        String sBaoQuantity = "";
        String sMQuantity = "";

        System.out.println("Welcome to Jiajia's Bao Stall");

        boolean loggedIn = login_register();
        while (!loggedIn)
            loggedIn = login_register();

        String folderPath = "orders";
        String fileNames = generateFileNames();
        String filePath = folderPath + File.separator + generateFileNames();

        System.out.print("Recommended: ");

        //BST<String> tree = new BST<>();
        Scanner in = new Scanner(System.in);
        String selection = "";
        while (!selection.equals("5")) {
            displayColumns(menuItems);
            selection = in.nextLine().substring(0, 1).toUpperCase();

            try{
                if (selection.equals("1")) {
                    System.out.println("How many would you like to buy: ");
                    String pValue = in.nextLine();
                    try {
                        pBaoQuantity = pValue;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                if (selection.equals("2")) {
                        System.out.println("How many would you like to buy: ");
                        String value = in.nextLine();
                        try {
                            vBaoQuantity = value;
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                }
                if (selection.equals("3")) {
                        System.out.println("How many would you like to buy: ");
                        String sValue = in.nextLine();
                        try {
                            sBaoQuantity = sValue;
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                }
                    if (selection.equals("4")) {
                        System.out.println("How many would you like to buy: ");
                        String milk = in.nextLine();
                        try {
                            sMQuantity = milk;
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }

            } catch (Exception e) {
                System.out.println(e);
            }

            if (selection.equals("5")) {
                System.out.println("Thank you for ordering from Jiajia's Bao Stall!");
            }
        }
        try (BufferedWriter bt = new BufferedWriter(new FileWriter(filePath))){
            bt.write("Pork Bao, Vegan Bao, Plain Steamed Buns, Soy Milk");
            bt.newLine();
            bt.write(pBaoQuantity);
            bt.newLine();
            bt.write(vBaoQuantity);
            bt.newLine();
            bt.write(sBaoQuantity);
            bt.newLine();
            bt.write(sMQuantity);
            bt.newLine();
        }catch(IOException e){
            System.out.println("Error writing to file");
        }

    }
public static void main(String[] args) {
    ArrayList<Bao> menuItem = new ArrayList<>();

    String filePath = "menuItem.csv";
    String folderPath = "orders";
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] fields = line.split(",");
            if (fields.length >= 2) {
                String product = fields[0].trim();
                int popularity = Integer.parseInt(fields[1].trim());
                menuItem.add(new Bao(product, popularity));
            }
        }
    } catch (IOException e) {
        System.err.println("Error reading file: " + e.getMessage());
    }
    for (int i = 0; i <= menuItem.size()-1; i++) {
        if (menuItem.get(i).getPopularity() < menuItem.get(i+1).getPopularity())
        {
            Bao temp = menuItem.get(i);
            menuItem.set(i, menuItem.get(i+1));
            menuItem.set(i+1, temp);
        }
    }

    System.out.println("Most Popular Items: " + menuItems);


    Main m = new Main("Pork Bao", 100);
    m.start();

}

    public static String generateFileNames() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String time = dtf.format(LocalDateTime.now());
        return time + ".csv";
    }


}