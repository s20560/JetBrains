package contacts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {

    Scanner sc = new Scanner(System.in);

    public void openMenu(String fileName) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("[menu] Enter action (add, list, search, count, exit): ");
            String input = sc.next();

            switch (input) {
                case "add":
                    System.out.println("Enter the type(person, organization): ");
                    input = sc.next();
                    switch (input) {
                        case "person":
                            Person.createContact();
                            System.out.println("The record added.");
                            break;

                        case "organization":
                            Organization.createContact();
                            System.out.println("The record added.");
                            break;
                    }
                    System.out.println();
                    break;

                case "list":
                    boolean isListing = true;
                    phoneBook.listContacts(phoneBook.getBook());
                    if (phoneBook.getBook().size() == 0) {
                        System.out.println("No records.");
                    } else {
                        while (isListing) {
                            System.out.println("[list] Enter action ([number], back): ");
                            input = sc.next();
                            Pattern pattern = Pattern.compile("[\\d]");
                            Matcher matcher = pattern.matcher(input);
                            if (matcher.find()) {
                                Contact toList = phoneBook.getBook().get(Integer.parseInt(input) - 1);
                                if (toList instanceof Person) {
                                    ((Person) toList).displayInfo();
                                } else if (toList instanceof Organization) {
                                    ((Organization) toList).displayInfo();
                                }
                                System.out.println();

                                boolean isInRecord = true;
                                while (isInRecord) {
                                    System.out.println("[record] Enter action (edit, delete, menu): ");
                                    input = sc.next();
                                    switch (input) {
                                        case "edit":
                                            if (toList instanceof Person) {
                                                ((Person) toList).edit();
                                                System.out.println("Saved");
                                                ((Person) toList).displayInfo();
                                                System.out.println();
                                            } else if (toList instanceof Organization) {
                                                ((Organization) toList).edit();
                                                System.out.println("Saved");
                                                ((Organization) toList).displayInfo();
                                                System.out.println();
                                            }
                                            break;

                                        case "delete":
                                            phoneBook.removeContact(toList);
                                            System.out.println("Removed contact\n");
                                            break;

                                        case "menu":
                                            isInRecord = false;
                                            isListing = false;
                                            System.out.println();
                                            break;
                                    }
                                }
                            } else if (input.equals("back")) {
                                isListing = false;
                            }
                        }
                    }
                    System.out.println();
                    break;

                case "search":
                    boolean isSearching = true;
                    System.out.println("Enter search query: ");
                    input = sc.next();
                    ArrayList<Contact> foundContacts = phoneBook.search(input);
                    if (foundContacts.size() == 0) {
                        System.out.println("No records found.");
                    } else {
                        while (isSearching) {
                            System.out.println("[search] Enter action ([number], back, again): ");
                            input = sc.next();
                            Pattern pattern = Pattern.compile("[\\d]");
                            Matcher matcher = pattern.matcher(input);
                            if (matcher.find()) {
                                Contact toDisplay = foundContacts.get(Integer.parseInt(input) - 1);
                                if (toDisplay instanceof Person) {
                                    ((Person) toDisplay).displayInfo();
                                } else if (toDisplay instanceof Organization) {
                                    ((Organization) toDisplay).displayInfo();
                                }
                                System.out.println();

                                boolean isInRecord = true;
                                while (isInRecord) {
                                    System.out.println("[record] Enter action (edit, delete, menu): ");
                                    input = sc.next();
                                    switch (input) {
                                        case "edit":
                                            if (toDisplay instanceof Person) {
                                                ((Person) toDisplay).edit();
                                                System.out.println("Saved");
                                                ((Person) toDisplay).displayInfo();
                                                System.out.println();
                                            } else if (toDisplay instanceof Organization) {
                                                ((Organization) toDisplay).edit();
                                                System.out.println("Saved");
                                                ((Organization) toDisplay).displayInfo();
                                                System.out.println();
                                            }
                                            break;

                                        case "delete":
                                            phoneBook.removeContact(toDisplay);
                                            System.out.println("Removed contact\n");
                                            break;

                                        case "menu":
                                            isInRecord = false;
                                            isSearching = false;
                                            System.out.println();
                                            break;
                                    }
                                }
                            } else if (input.equals("back")) {
                                isSearching = false;
                            } else if (input.equals("again")) {
                                System.out.println("Enter search query: ");
                                input = sc.next();
                                foundContacts = phoneBook.search(input);
                            }
                        }
                    }
                    System.out.println();
                    break;

                case "count":
                    System.out.printf("\nThe Phone book has %d records.\n", phoneBook.contactsCounter());
                    System.out.println();
                    break;

                case "exit":
                    isRunning = false;
                    try {
                        SerializationUtils.serialize(phoneBook.getBook(), fileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
            }
        }
    }
}
