package flashcards;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cards {

    private static Map<String, String> cards = new LinkedHashMap<>();
    private static Map<Cards, Integer> errors = new LinkedHashMap<>();
    String question;
    String answer;
    int counter = 0;

    public Cards(String question, String answer, int counter) {
        this.question = question;
        this.answer = answer;
        this.counter = counter;
    }

    public static void createCard(String key, String value, int errorCount) {
        cards.put(key, value);
        Cards card = new Cards(key, value, errorCount);
        errors.put(card, card.counter);
    }

    public static void removeCard(String key) {
        cards.remove(key);
        errors.remove(getCardByQuestion(key));
    }

    public static void updateCard(String key, String value) {
        cards.replace(key, value);
    }

    public static void checkAnswer(String question, String answer) {
        for (var entry : errors.entrySet()) {
            if (entry.getKey().question.equals(question)) {
                if (entry.getKey().answer.equals(answer)) {
                    System.out.println("Correct!");
                    break;
                } else if (cards.containsValue(answer)) {
                    System.out.println("Wrong. The right answer is \"" + entry.getKey().answer + "\", but your definition is correct" +
                            " for \"" + getKeyByValue(answer)  +"\".");
                    entry.setValue(entry.getValue() + 1);

                } else {
                    System.out.println("Wrong. The right answer is \"" + entry.getKey().answer + "\".");
                    entry.setValue(entry.getValue() + 1);
                }
            }
        }
    }

    public static Map<String, String> getCards() {
        return cards;
    }

    public static Map<Cards, Integer> getErrors() {
        return errors;
    }

    private static String getKeyByValue(String value) {
        String key = null;

        for (var entry : cards.entrySet()) {
            if (entry.getValue().equals(value)) {
                key = entry.getKey();
            }
        }
        return key;
    }

    private static Cards getCardByQuestion(String key) {
        Cards card = null;
        for (var entry : errors.keySet()) {
            if (entry.question.equals(key)) {
                card = entry;
            }
        }
        return card;
    }
}