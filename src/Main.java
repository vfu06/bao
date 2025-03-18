import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static String[] menuItems = {
            "1: Pork Bao",
            "2: Vegan Bao",
            "3: Plain Steamed Bun",
            "4: Soy Milk",
            "5: Quit"
    };

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
        //while loop to keep program going
        if (option.equals("a")) {
            //while (loggedIn == false) {
            System.out.println("Enter Username: ");
            username = log.nextLine();
            System.out.println("Enter Password: ");
            password = log.nextLine();
            List<String[]> clientData = readCSV("client.csv");
            for(String[] row : clientData) {
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

    public static void writeCSV(String filePath, List<String[]> clientData){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : clientData) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void main(String[] args) {

        System.out.println("Welcome to Jiajia's Bao Stall");

        boolean loggedIn = login_register();
        while (!loggedIn)
            loggedIn = login_register();

        System.out.print("Recommended: ");

        //BST<String> tree = new BST<>();
        Scanner in = new Scanner(System.in);
        String selection = "";
        while (!selection.equals("5")) {
            displayColumns(menuItems);
            selection = in.nextLine().substring(0, 1).toUpperCase();

            try {
                if (selection.equals("1")) {
                    System.out.println("How many would you like to buy: ");
                    String value = in.nextLine();
                    try {
                        int pBaoQuantity = Integer.parseInt(value);
                    } catch (Exception e) {
                        System.out.println("File not found");
                    }
                }
                if (selection.equals("2")) {
                    System.out.println("How many would you like to buy: ");
                    String value = in.nextLine();
                    try {
                        int vBaoQuantity = Integer.parseInt(value);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                if (selection.equals("3")) {
                    System.out.println("How many would you like to buy: ");
                    String value = in.nextLine();
                    try {
                        int sBaoQuantity = Integer.parseInt(value);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                if (selection.equals("4")) {
                    System.out.println("How many would you like to buy: ");
                    String value = in.nextLine();
                    try {
                        int sMQuantity = Integer.parseInt(value);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            } catch (UnsupportedOperationException e) {
                System.out.println(e);
            }

        }

        if (selection.equals("5")) {
            System.out.println("Thank you for ordering from Jiajia's Bao Stall!");
        }
        //add make file here
    }

}