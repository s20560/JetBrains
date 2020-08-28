package sorting;

import java.io.IOException;

public class Main {
    public static void main(final String[] args) throws IOException {

        String dataType = "";
        String sortType = "natural";

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-sortingType")) {
                try {
                    sortType = args[i + 1];
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("No sorting type defined!");
                }
            } else if (args[i].equals("-dataType")) {
                try {
                    dataType = args[i + 1];
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("No data type defined!");
                }
            }
        }
        
        Console console = new Console();
        console.OpenConsole(dataType, sortType);
    }
}