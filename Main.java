package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        File file = new File(args[0]);
        String text = "";

        int wordsInText = 0;
        int sentencesInText;
        int charactersInText = 0;
        int syllables = 0;
        int polysyllables = 0;


        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                text += sc.nextLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + file.getName());
        }

        String[] sentences = text.split("[\\.|\\?|!][.* ]");
        sentencesInText = sentences.length;

        for (int i = 0; i < sentences.length; i++) {
            String[] wordsInSentence = sentences[i].split("[\\s\\h]+");
            wordsInText += wordsInSentence.length;


            for (int j = 0; j < wordsInSentence.length; j++) {
                char[] charactersInSentence = wordsInSentence[j].toCharArray();
                charactersInText += charactersInSentence.length;
                if(countWithRegex(wordsInSentence[j]) > 2){
                    polysyllables++;
                }
                syllables += countWithRegex(wordsInSentence[j]);

            }
        }
        Scanner scanner = new Scanner(System.in);

        charactersInText += sentencesInText - 1;
        System.out.println(text);
        System.out.println();
        System.out.println("Words: " + wordsInText);
        System.out.println("Sentences: " + sentencesInText);
        System.out.println("Characters: " + charactersInText);
        System.out.println("Syllables: " + syllables );
        System.out.println("Polysyllables: " + polysyllables);
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String system  = scanner.nextLine();
        System.out.println();

        int ageARI = checkAge(ARI(charactersInText,wordsInText,sentencesInText));
        int ageFK = checkAge(FleschKincaid(wordsInText,sentencesInText,syllables));
        int ageSMOG = checkAge(SMOG(polysyllables,sentencesInText));
        int ageCl = checkAge(CL(charactersInText,wordsInText,sentencesInText));

        switch (system){
            case "ARI":
                System.out.println("Automated Readability Index: " + ARI(charactersInText,wordsInText,sentencesInText) + " (about " + ageARI + " year olds)." );
                break;
            case "FK":
                System.out.println("Flesch–Kincaid readability tests: " + FleschKincaid(wordsInText,sentencesInText,syllables) + " (about " + ageFK + " year olds)." );
                break;
            case "SMOG":
                System.out.println("Simple Measure of Gobbledygook: " + SMOG(polysyllables,sentencesInText) + " (about " + ageSMOG + " year olds).");
                break;
            case "CL":
                System.out.println("Coleman–Liau index: " + CL(charactersInText,wordsInText,sentencesInText) + " (about " + ageCl + " year olds).");
                break;
            case "all":
                System.out.println("Automated Readability Index: " + ARI(charactersInText,wordsInText,sentencesInText) + " (about " + ageARI + " year olds)." );
                System.out.println("Flesch–Kincaid readability tests: " + FleschKincaid(wordsInText,sentencesInText,syllables) + " (about " + ageFK + " year olds)." );
                System.out.println("Simple Measure of Gobbledygook: " + SMOG(polysyllables,sentencesInText) + " (about " + ageSMOG + " year olds).");
                System.out.println("Coleman–Liau index: " + CL(charactersInText,wordsInText,sentencesInText) + " (about " + ageCl + " year olds).");
                double averageAge = (double)(ageARI + ageFK + ageSMOG + ageCl)/4;
                System.out.println();
                System.out.println("This text should be understood in average by " + averageAge + " year olds");
                break;
        }


    }

    private static int countWithRegex(String word) {
        String i = "(?i)[aiou][aeiou]*|e[aeiou]*(?!d?\\b)";
        Matcher m = Pattern.compile(i).matcher(word);
        int count = 0;

        while (m.find()) {
            count++;
        }
        return Math.max(count, 1);
    }

    private static double FleschKincaid(int words, int sentences, int syllables){
        double firstPart = 0.39 * (double)(words/sentences);
        double secondPart = 11.8 * (float)syllables/words;

        double result = firstPart + secondPart - 15.59;
        return (double)Math.round(result*100)/100;
    }

    private static double ARI(int characters, int words, int sentences){

        double result = 4.71*((float)characters/(float)words)+0.5*(double)((float)words/(float)sentences)-21.43;
        return (double)Math.round(result*100)/100;
    }

    private static double SMOG(int polysyllables, int sentences){
        double result  = 0;
        result = (Math.sqrt(polysyllables*(double)(30/sentences)) + 3.1291);
        return (double)Math.round(result*100)/100;
    }

    private static double CL(int characters,int words, int sentences){
        double L = ((float)characters/(float)words)*100;
        double S = ((float)sentences/(float)words)*100;
        double result = 0.0588*L - 0.296 * S -15.8;

        return (double)Math.round(result*100)/100;
    }

    private static int checkAge(double score){
        int age = 0;
        if(score < 1){
            age = 6;
        } else if(score >= 1 && score < 2){
            age = 7;
        } else if(score >= 2 && score < 3){
            age = 9;
        } else if(score >= 3 && score < 4){
            age = 10;
        } else if(score >= 4 && score < 5){
            age = 11;
        } else if(score >= 5 && score < 6){
            age = 12;
        } else if(score >= 6 && score < 7){
            age = 13;
        } else if(score >= 7 && score < 8){
            age = 14;
        } else if(score >= 8 && score < 9){
            age = 15;
        } else if(score >= 9 && score < 10){
            age = 16;
        } else if(score >= 10 && score < 11){
            age = 17;
        } else if(score >= 11 && score < 12){
            age = 18;
        } else if(score >= 12 && score < 13){
            age = 24;
        } else if(score >= 13){
            age = 25;
        }
        return age;
    }
}
