package flashcards;

public class Main {
    public static void main(String[] args) {

        String importFile = "";
        String exportFile = "";

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-import")){
                importFile = args[i + 1];
            } else if (args[i].equals("-export")) {
                exportFile = args[i + 1];
            }
        }

        Console console = new Console();
        console.openConsole(importFile, exportFile);
    }
}
