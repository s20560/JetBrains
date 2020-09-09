package flashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Console {

    Scanner sc = new Scanner(System.in);
    File file;
    String input;

    public void openConsole(String importFile, String exportFile) {

        if (!importFile.equals("")) {
            file = new File(importFile);
            int counter = 0;
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNext()) {
                    String[] loadedInput = scanner.nextLine().split("#");
                    if (!Cards.getCards().containsKey(loadedInput[0]) && !Cards.getCards().containsValue(loadedInput[1])) {
                        Cards.createCard(loadedInput[0], loadedInput[1], Integer.parseInt(loadedInput[2]));
                    } else {
                        Cards.updateCard(loadedInput[0], loadedInput[1]);
                    }
                    counter++;
                }
                System.out.println(counter + " cards have been loaded.");

            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
        }
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            input = sc.nextLine();
            System.out.println();

            switch (input) {
                case "add":
                    System.out.println("The card:");
                    String question = sc.nextLine();
                    if (Cards.getCards().containsKey(question)) {
                        System.out.println("The card \"" + question + "\" already exists.");
                    } else {
                        System.out.println("The definition of the card:");
                        String answer = sc.nextLine();
                        if (Cards.getCards().containsValue(answer)) {
                            System.out.println("The definition \"" + answer + "\" already exists.");
                        } else {
                            Cards.createCard(question, answer, 0);
                            System.out.println("The pair (\"" + question + "\":\"" + answer + "\") has been added.");
                        }
                    }
                    break;

                case "remove":
                    System.out.println("The card:");
                    question = sc.nextLine();
                    if (Cards.getCards().containsKey(question)) {
                        Cards.removeCard(question);
                        System.out.println("The card has been removed");
                    } else {
                        System.out.println("Can't remove \"" + question + "\": there is no such card.");
                    }
                    break;

                case "import":
                    System.out.println("File name:");
                    String name = sc.nextLine();
                    file = new File(name);
                    int counter = 0;

                    try (Scanner scanner = new Scanner(file)) {
                        while (scanner.hasNext()) {
                            String[] loadedInput = scanner.nextLine().split("#");
                            if (!Cards.getCards().containsKey(loadedInput[0]) && !Cards.getCards().containsValue(loadedInput[1])) {
                                Cards.createCard(loadedInput[0], loadedInput[1], Integer.parseInt(loadedInput[2]));
                            } else {
                                Cards.updateCard(loadedInput[0], loadedInput[1]);
                            }
                            counter++;
                        }
                        System.out.println(counter + " cards have been loaded.");

                    } catch (FileNotFoundException e) {
                        System.out.println("File not found.");
                    }
                    break;

                case "export":
                    System.out.println("File name:");
                    name = sc.nextLine();
                    file = new File(name);
                    counter = 0;

                    try (FileWriter fileWriter = new FileWriter(file)) {
                        for (var entry : Cards.getErrors().entrySet()) {
                            fileWriter.write(entry.getKey().question + "#" + entry.getKey().answer + "#" + entry.getValue() + "\r\n");
                            counter++;
                        }
                        System.out.println(counter + " cards have been saved.");

                    } catch (IOException e) {
                        System.out.println("Can't save the file.");
                    }
                    break;

                case "ask":
                    Random rd = new Random();
                    List<String> keys = new ArrayList<>(Cards.getCards().keySet());

                    System.out.println("How many times to ask?");
                    int numberOfQuestions = sc.nextInt();
                    sc.nextLine();

                    for (int i = 0; i < numberOfQuestions; i++) {
                        String randomKey = keys.get(rd.nextInt(keys.size()));

                        System.out.println("Print the definition of \"" + randomKey +"\":");
                        String answer = sc.nextLine();
                        Cards.checkAnswer(randomKey,answer);
                    }
                    break;

                case "log":
                    System.out.println("File name:");
                    name = sc.nextLine();
                    file = new File(name);
                    try (FileWriter fileWriter = new FileWriter(file)){
                        fileWriter.write("poope");
                        System.out.println("The log has been saved.");

                    } catch (IOException e) {
                        System.out.println("Can't save the file.");
                    }
                    break;

                case "hardest card":
                    ArrayList<String> maxNames = new ArrayList<>();
                    int max = 0;
                    String maxName = "";
                    for (var entry : Cards.getErrors().entrySet()) {
                        if (entry.getValue() > max) {
                            max = entry.getValue();
                            maxName = entry.getKey().question;
                            maxNames.clear();
                        }
                        if (entry.getValue() > 0 && entry.getValue() == max) {
                            maxNames.add(entry.getKey().question);
                        }
                    }
                    if (max == 0) {
                        System.out.println("There are no cards with errors");
                    } else if (maxNames.size() == 1) {
                        System.out.println("The hardest card is \"" + maxName + "\". You have " + max + " errors answering it.");
                    } else {
                        System.out.print("The hardest cards are ");
                        for (String s : maxNames) {
                            System.out.print("\""+ s +"\", ");
                        }
                        System.out.print(". You have " + max + " errors answering them.\n");
                    }
                    break;

                case "reset stats":
                    boolean moreThanZero = false;
                    for (var entry : Cards.getErrors().entrySet()) {
                        if (entry.getValue() > 0) {
                            moreThanZero = true;
                            break;
                        }
                    }
                    if (moreThanZero) {
                        for (var entry : Cards.getErrors().entrySet()) {
                            entry.setValue(0);
                        }
                        System.out.println("Card statistics has been reset.");
                    } else {
                        System.out.println("There are no cards with errors.");
                    }
                    break;

                case "exit":
                    System.out.println("Bye bye!");
                    counter = 0;
                    if (!exportFile.equals("")) {
                        file = new File(exportFile);
                        try (FileWriter fileWriter = new FileWriter(file)) {
                            for (var entry : Cards.getErrors().entrySet()) {
                                fileWriter.write(entry.getKey().question + "#" + entry.getKey().answer + "#" + entry.getValue() + "\r\n");
                                counter++;
                            }
                            System.out.println(counter + " cards have been saved.");
                        } catch (IOException e) {
                            System.out.println("Can't save the file.");
                        }
                    }
                    isRunning = false;
                    break;
            }
        }
    }
}
