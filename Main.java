package contacts;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String fileName = "contacts.data";
        ArrayList<Contact> loadedPhoneBook = new ArrayList<>();
        try {
            loadedPhoneBook = (ArrayList<Contact>) SerializationUtils.deserialize(fileName);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        phoneBook.setBook(loadedPhoneBook);

        Menu menu = new Menu();
        menu.openMenu(fileName);
    }
}
