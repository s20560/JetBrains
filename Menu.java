package budget;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Menu {

    Scanner sc = new Scanner(System.in);
    Account listManager = new Account();
    File file = new File("purchases.txt");
    String input;

    public void openMenu () throws IOException {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Choose your action:");
            System.out.println("1) Add income");
            System.out.println("2) Add purchase");
            System.out.println("3) Show list of purchases");
            System.out.println("4) Balance");
            System.out.println("5) Save");
            System.out.println("6) Load");
            System.out.println("7) Analyze (Sort)");
            System.out.println("0) Exit");
            input = sc.next();
            System.out.println();

            switch (input) {
                case "1":
                    System.out.println("Enter income:");
                    listManager.setBudget(sc.nextDouble());
                    System.out.println("Income was added!\n");
                    break;

                case "2":
                    boolean isAdding = true;
                    while(isAdding){
                        System.out.println("Choose the type of purchase");
                        System.out.println("1) Food");
                        System.out.println("2) Clothes");
                        System.out.println("3) Entertainment");
                        System.out.println("4) Other");
                        System.out.println("5) Back");
                        input = sc.next();
                        System.out.println();
                        switch (input) {
                            case "1":
                                PRODUCT_TYPE type = PRODUCT_TYPE.FOOD;
                                listManager.addProduct(type);
                                break;

                            case "2":
                                type = PRODUCT_TYPE.CLOTHES;
                                listManager.addProduct(type);
                                break;

                            case "3":
                                type = PRODUCT_TYPE.ENTERTAINMENT;
                                listManager.addProduct(type);
                                break;

                            case "4":
                                type = PRODUCT_TYPE.OTHER;
                                listManager.addProduct(type);
                                break;

                            case "5":
                                isAdding = false;
                                break;
                        }
                    }
                    break;

                case "3":
                    boolean isShowing = true;
                    while (isShowing) {
                        System.out.println("Choose the type of purchases");
                        System.out.println("1) Food");
                        System.out.println("2) Clothes");
                        System.out.println("3) Entertainment");
                        System.out.println("4) Other");
                        System.out.println("5) All");
                        System.out.println("6) Back");
                        input = sc.next();
                        System.out.println();
                        switch (input) {
                            case "1":
                                PRODUCT_TYPE type = PRODUCT_TYPE.FOOD;
                                System.out.println("Food:");
                                listManager.showByType(type);
                                break;

                            case "2":
                                type = PRODUCT_TYPE.CLOTHES;
                                System.out.println("Clothes:");
                                listManager.showByType(type);
                                break;

                            case "3":
                                type = PRODUCT_TYPE.ENTERTAINMENT;
                                System.out.println("Entertainment:");
                                listManager.showByType(type);
                                break;

                            case "4":
                                type = PRODUCT_TYPE.OTHER;
                                System.out.println("Other:");
                                listManager.showByType(type);
                                break;

                            case "5":
                                System.out.println("All:");
                                listManager.showAll(listManager.shoppingList);
                                break;

                            case "6":
                                isShowing = false;
                                break;
                        }
                    }
                    break;

                case "4":
                    System.out.println("Balance: $" + String.format("%.2f", listManager.getBudget()) + "\n");
                    break;

                case "5":
                    FileWriter fileWriter = new FileWriter(file, false);
                    fileWriter.write(String.format("%.2f", listManager.getBudget()) + "\r\n");
                    for(Product p : listManager.shoppingList) {
                        fileWriter.write(p.getType() + "\r\n");
                        fileWriter.write(p.getName() + " #" + String.format("%.2f", p.getPrice()) + "\r\n");
                    }
                    fileWriter.close();
                    listManager.shoppingList.clear();
                    System.out.println("Purchases were saved!\n");
                    break;

                case "6":
                    listManager.shoppingList.clear();
                    Scanner scanner = new Scanner(file);
                    listManager.budget = Double.parseDouble(scanner.nextLine());
                    while (scanner.hasNext()) {
                        String category = scanner.nextLine();
                        String[] product = scanner.nextLine().split("\\s#");
                        Product p = new Product(product[0], Double.parseDouble(product[1]), PRODUCT_TYPE.valueOf(category));
                        listManager.shoppingList.add(p);
                    }
                    System.out.println("Purchases were loaded!\n");
                    break;

                case "7":
                    boolean isSorting = true;
                    while (isSorting) {
                        System.out.println("How do you want to sort?");
                        System.out.println("1) Sort all purchases");
                        System.out.println("2) Sort by type");
                        System.out.println("3) Sort certain type");
                        System.out.println("4) Back");
                        input = sc.next();
                        System.out.println();
                        switch (input) {
                            case "1":
                                System.out.println("All");
                                listManager.sortAll();
                                break;

                            case "2":
                                listManager.sortByType();
                                break;

                            case "3":
                                System.out.println("Choose the type of purchase");
                                System.out.println("1) Food");
                                System.out.println("2) Clothes");
                                System.out.println("3) Entertainment");
                                System.out.println("4) Other");
                                input = sc.next();
                                System.out.println();
                                switch (input) {
                                    case "1":
                                        PRODUCT_TYPE type = PRODUCT_TYPE.FOOD;
                                        System.out.println("Food:");
                                        listManager.sortByCertainType(type);
                                        break;

                                    case "2":
                                        type = PRODUCT_TYPE.CLOTHES;
                                        System.out.println("Clothes:");
                                        listManager.sortByCertainType(type);
                                        break;

                                    case "3":
                                        type = PRODUCT_TYPE.ENTERTAINMENT;
                                        System.out.println("Entertainment:");
                                        listManager.sortByCertainType(type);
                                        break;

                                    case "4":
                                        type = PRODUCT_TYPE.OTHER;
                                        System.out.println("Other:");
                                        listManager.sortByCertainType(type);
                                        break;
                                }
                                break;

                            case "4":
                                isSorting = false;
                                break;
                        }
                    }
                    break;

                case "0":
                    isRunning = false;
                    System.out.println("Bye!");
                    break;

                default:
                    System.out.println("Choose correct option!");
                    break;
            }
        }
    }
}