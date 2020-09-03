package phonebook;

import java.io.File;
import java.util.Scanner;

public class ReadFiles {

    public static String[] ReadDirectory() {
        File directory = new File("C:\\Users\\Laska\\Desktop\\directory.txt");
        String[] data = new String[0];

        StringBuilder sb = new StringBuilder();
        try (Scanner scanner = new Scanner(directory)) {
            while (scanner.hasNextLine()) {
                scanner.nextInt();
                sb.append(scanner.nextLine().substring(1)).append("\n");
            }
            data = sb.toString().split("\\n");
        } catch (Exception e) {
            System.out.print("Could not read Find successfully");
            e.printStackTrace();
        }

        return data;
    }

    public static String[] ReadFind() {
        File find = new File("C:\\Users\\Laska\\Desktop\\find.txt");
        String[] data = new String[0];

        StringBuilder sb = new StringBuilder();
        try (Scanner scanner = new Scanner(find)) {
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append("\n");
            }
            data = sb.toString().split("\\n");
        } catch (Exception e) {
            System.out.print("Could not read Find successfully");
            e.printStackTrace();
        }

        return data;
    }
}
