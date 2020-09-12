package contacts;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Person extends Contact implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String surname;
    private String gender;
    private static LocalDate birthDay;

    public Person(String phoneNumber, String name, String surname, String gender, LocalDate birthDay, TYPE type) {
        super(phoneNumber, type);
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthDay = birthDay;
    }

    public static void createContact() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name: ");
        String nameInput = sc.nextLine();
        System.out.println("Enter the surname: ");
        String surnameInput = sc.nextLine();
        System.out.println("Enter the birth date: ");
        try {
            String birthDateInput = sc.nextLine();
            birthDay = LocalDate.parse(birthDateInput);
        } catch (DateTimeParseException e) {
            System.out.println("Bad birth date!");
        }
        System.out.println("Enter the gender (M, F): ");
        String genderInput = sc.nextLine();
        if (!genderInput.equals("M") && !genderInput.equals("F")) {
            System.out.println("Bad gender!");
            genderInput = "[no data]";
        }
        System.out.println("Enter the number: ");
        String numberInput = sc.nextLine();
        if (!checkNumberValidation(numberInput)) {
            System.out.println("Wrong number format!");
            numberInput = "[no number]";
        }
        Contact contact = new Person(numberInput, nameInput, surnameInput, genderInput, birthDay, TYPE.ORGANIZATION);
        phoneBook.book.add(contact);
    }

    public void displayInfo() {
        System.out.println("Name: " + getName());
        System.out.println("Surname: " + getSurname());
        if (getBirthDay() == null) {
            System.out.println("Birth date: [no data]");
        } else {
            System.out.println("Birth date: " + getBirthDay());
        }
        System.out.println("Gender: " + getGender());
        System.out.println("Number: " + getPhoneNumber());
        System.out.println("Time created: " + getCreationTime().withNano(0).withSecond(0));
        System.out.println("Time last edit: " + getLastEditTime().withNano(0).withSecond(0));
    }

    public void edit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select a field (name, surname, birth, gender, number): ");
        String input = sc.next();
        switch (input) {
            case "name":
                System.out.println("Enter name: ");
                sc.nextLine();
                setName(sc.nextLine());
                break;

            case "surname":
                System.out.println("Enter surname:");
                setSurname(sc.next());
                break;

            case "birth":
                System.out.println("Enter birthday: ");
                setBirthDay(sc.next());
                break;

            case "gender":
                System.out.println("Enter gender: ");
                String genderInput = sc.next();
                if (!genderInput.equals("M") && !genderInput.equals("F")) {
                    System.out.println("Bad gender!");
                } else {
                    setGender(genderInput);
                }
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
        setLastEditTime(LocalDateTime.now());
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
        setLastEditTime(LocalDateTime.now());
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String newDate) {
        try {
            birthDay = LocalDate.parse(newDate);
        } catch (DateTimeParseException e) {
            System.out.println("Bad birth date!");
        }

        setLastEditTime(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name'=" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", gender='" + getGender() + '\'' +
                ", birthDay='" + getBirthDay().toString() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                '}';
    }
}
