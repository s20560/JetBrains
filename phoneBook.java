package contacts;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class phoneBook {

    public static ArrayList<Contact> book = new ArrayList<>();

    public static ArrayList<Contact> getBook() {
        return book;
    }

    public static int contactsCounter() {
        return book.size();
    }

    public static void listContacts(ArrayList<Contact> list) {
        int counter = 1;
        for (Contact contact : list) {
            if (contact instanceof Person) {
                System.out.println(counter + ". " + ((Person) contact).getName() + " " + ((Person) contact).getSurname());
            }
            if (contact instanceof Organization) {
                System.out.println(counter + ". " + ((Organization) contact).getName());
            }
            counter++;
        }
    }

    public static void removeContact(Contact contact) {
        book.remove(contact);
    }

    public static ArrayList<Contact> search(String toFind) {
        ArrayList<Contact> foundContacts = new ArrayList<>();

        Pattern pattern = Pattern.compile(toFind, Pattern.CASE_INSENSITIVE);

        for (Contact contact : phoneBook.getBook()) {
            if (contact instanceof Person) {
                Matcher name = pattern.matcher(((Person) contact).getName());
                Matcher surname = pattern.matcher(((Person) contact).getSurname());
                Matcher phoneNumber = pattern.matcher(contact.getPhoneNumber());
                Matcher gender = pattern.matcher(((Person) contact).getGender());
                if (name.find() || surname.find() || phoneNumber.find() || gender.find()) {
                    foundContacts.add(contact);
                }
            }
            if (contact instanceof Organization) {
                Matcher name = pattern.matcher(((Organization) contact).getName());
                Matcher address = pattern.matcher(((Organization) contact).getAddress());
                Matcher phoneNumber = pattern.matcher(contact.getPhoneNumber());
                if (name.find() || address.find() || phoneNumber.find()) {
                    foundContacts.add(contact);
                }
            }
        }
        System.out.println("Found " + foundContacts.size() + " results: ");
        listContacts(foundContacts);
        return foundContacts;
    }

    public static void setBook(ArrayList<Contact> book) {
        phoneBook.book = book;
    }
}
