package budget;

import java.util.ArrayList;
import java.util.Scanner;

public class Account {

    double budget;
    ArrayList<Product> shoppingList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void addProduct(PRODUCT_TYPE type) {
        System.out.println("Enter purchase name:");
        String productName = sc.nextLine();
        System.out.println("Enter its price:");
        Product p = new Product(productName, sc.nextDouble(), type);
        sc.nextLine();
        this.shoppingList.add(p);
        this.budget -= p.getPrice();
        System.out.println("Purchase was added!\n");
    }

    public void showByType(PRODUCT_TYPE type) {
        double sum = 0;
        if(this.shoppingList.isEmpty()){
            System.out.println("Purchase list is empty");
        } else {
            for(Product product : this.shoppingList) {
                if(product.getType().equals(type)){
                    sum += product.getPrice();
                    System.out.println(product.getName() + " $" + String.format("%.2f", product.getPrice()));
                }
            }
            System.out.println("Total: $" + String.format("%.2f", sum));
        }
        System.out.println();
    }

    public void showAll(ArrayList<Product> list) {
        double sum = 0;
        if(list.isEmpty()){
            System.out.println("Purchase list is empty");
        } else {
            for(Product product : list) {
                sum += product.getPrice();
                System.out.println(product.getName() + " $" + String.format("%.2f", product.getPrice()));
            }
            System.out.println("Total: $" + String.format("%.2f", sum));
        }
        System.out.println();
    }

    public void sortAll() {
        ArrayList<Product> sorted = bubbleSort(shoppingList);
        showAll(sorted);
    }

    public void sortByType() {
        double foodPrice = 0;
        double entertainmentPrice = 0;
        double clothesPrice = 0;
        double otherPrice = 0;
        double total = 0;

        for (Product p : shoppingList) {
            if (p.getType().equals(PRODUCT_TYPE.FOOD)) {
                foodPrice += p.getPrice();
                total += p.getPrice();
            } else if (p.getType().equals(PRODUCT_TYPE.ENTERTAINMENT)) {
                entertainmentPrice += p.getPrice();
                total += p.getPrice();
            } else if (p.getType().equals(PRODUCT_TYPE.CLOTHES)) {
                clothesPrice += p.getPrice();
                total += p.getPrice();
            } else if (p.getType().equals(PRODUCT_TYPE.OTHER)) {
                otherPrice += p.getPrice();
                total += p.getPrice();
            }
        }
        Product food = new Product("Food", foodPrice, PRODUCT_TYPE.FOOD);
        Product entertainment = new Product("Entertainment", entertainmentPrice, PRODUCT_TYPE.ENTERTAINMENT);
        Product clothes = new Product("Clothes", clothesPrice, PRODUCT_TYPE.CLOTHES);
        Product other = new Product("Other", otherPrice, PRODUCT_TYPE.OTHER);
        ArrayList<Product> categories = new ArrayList<>();
        categories.add(food);
        categories.add(entertainment);
        categories.add(clothes);
        categories.add(other);
        ArrayList<Product> sortedCategories = bubbleSort(categories);

        System.out.println("Types:");
        for (Product category : sortedCategories) {
            System.out.println(category.getName() + " - $" + String.format("%.2f", category.getPrice()));
        }
        System.out.println("Total sum: $" + String.format("%.2f", total) + "\n");
    }

    public void sortByCertainType(PRODUCT_TYPE type) {
        ArrayList<Product> listByType = new ArrayList<>();
        for (Product p : shoppingList) {
            if (p.getType().equals(type)) {
                listByType.add(p);
            }
        }
        ArrayList<Product> sorted = bubbleSort(listByType);
        showAll(sorted);
    }

    public ArrayList<Product> bubbleSort(ArrayList<Product> shoppingList) {
        ArrayList<Product> sorted = shoppingList;

        for (int i = 0; i < sorted.size() - 1 ; i++) {
            for (int j = 0; j < sorted.size() - i - 1; j++) {
                if (sorted.get(j).getPrice() < sorted.get(j + 1).getPrice()) {
                    Product temp =  sorted.get(j);
                    sorted.set(j, sorted.get(j + 1));
                    sorted.set(j + 1, temp);
                }
            }
        }
        return sorted;
    }
}