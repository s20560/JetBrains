package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Organization extends Contact implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String address;

    public Organization(String phoneNumber, String name, String address, TYPE type) {
        super(phoneNumber, type);
        this.name = name;
        this.address = address;
    }

    public static void createContact() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the organization name: ");
        String nameInput = sc.nextLine();
        System.out.println("Enter the address: ");
        String addressInput = sc.nextLine();
        System.out.println("Enter the number: ");
        String numberInput = sc.nextLine();
        if (!checkNumberValidation(numberInput)) {
            System.out.println("Wrong number format!");
            numberInput = "[no number]";
        }
        Contact contact = new Organization(numberInput, nameInput, addressInput, TYPE.ORGANIZATION);
        phoneBook.book.add(contact);
    }

    public void displayInfo() {
        System.out.println("Organization name: " + getName());
        System.out.println("Address: " + getAddress());
        System.out.println("Number: " + getPhoneNumber());
        System.out.println("Time created: " + getCreationTime().withNano(0).withSecond(0));
        System.out.println("Time last edit: " + getLastEditTime().withNano(0).withSecond(0));
    }

    public void edit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select a field (name, address, number): ");
        String input = sc.next();
        switch (input) {
            case "name":
                System.out.println("Enter name: ");
                sc.nextLine();
                setName(sc.nextLine());
                break;

            case "address":
                System.out.println("Enter address: ");
                sc.nextLine();
                setAddress(sc.nextLine());
                break;

            case "number":
                System.out.println("Enter number: ");
                sc.nextLine();
                String numberInput = sc.nextLine();
                if (checkNumberValidation(numberInput)) {
                    setPhoneNumber(numberInput);
                }
                break;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setLastEditTime(LocalDateTime.now());
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        setLastEditTime(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name'=" + getName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                '}';
    }
}
