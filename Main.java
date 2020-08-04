package encryptdecrypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String dataFromData = "";
        String operation = "enc";
        int key = 0;
        String data = "";
        String pathOut = "";
        String pathIn = "";
        String alg = "shift";
        boolean isData = false;
        boolean isFile = false;

        for(int i = 0; i < args.length; i++){
            if(args[i].equals("-mode")){
                operation = args[i+1];
            }
            if(args[i].equals("-key")){
                key = Integer.parseInt(args[i+1]);
            }
            if(args[i].equals("-data")){
                dataFromData = args[i+1];
                isData = true;
            }
            if(args[i].equals("-in")){
                pathIn = args[i+1];
                isFile = true;
            }
            if(args[i].equals("-out")){
                pathOut = args[i+1];
            }
            if(args[i].equals("-alg")){
                alg = args[i+1];
            }
        }
        
        File f1 = new File(pathIn);
        File f2 = new File(pathOut);

        if(isFile){
            try(Scanner sc = new Scanner(f1)){
                while (sc.hasNext()){
                    data += sc.nextLine();
                }

            } catch (IOException e){
                System.out.println("Error");
            }
        } else {
            data = dataFromData;
        }

        char[] password = data.toCharArray();

        switch (operation) {
            case "enc":
                if(alg.equals("unicode")){
                    encryptUni(password,key);
                } else if(alg.equals("shift")){
                    encryptShift(password,key);
                }
                System.out.println(printArray(password));
                break;

            case "dec":
                if(alg.equals("unicode")){
                    decryptUni(password,key);
                } else if(alg.equals("shift")){
                    decryptShift(password,key);
                }
                System.out.println(printArray(password));
                break;
        }
        try(FileWriter writer = new FileWriter(f2)){
            writer.write(printArray(password));
        } catch (IOException e){
            System.out.println("Error");



        }
    }

    static String printArray(char[] arr){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++){
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    static void encryptUni(char[] arr, int key){
        for(int i = 0; i < arr.length; i++) {
            arr[i] += key;
        }
    }

    static void decryptUni(char[] arr, int key){
        for(int i = 0; i < arr.length; i++) {
            arr[i] -= key;
        }
    }

    static void encryptShift(char[] arr, int key) {
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != ' '){
                if((arr[i] + key > 96) && (arr[i] + key) < 123){
                    arr[i] += key;
                } else if ((arr[i] + key > 64) && (arr[i] + key) < 91){
                    arr[i] += key;
                } else {
                    arr[i] += key - 26;
                }
            }
        }
    }

    static void decryptShift(char[] arr, int key){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != ' '){
                if((arr[i] + key > 96) && (arr[i] + key) < 123){
                    arr[i] -= key;
                    if(arr[i] < 97) {
                        arr[i] = (char)(arr[i]-97+123);
                    }
                } else if ((arr[i] + key > 64) && (arr[i] + key) < 91){
                    arr[i] -= key;
                    if (arr[i] < 'A') {
                        arr[i] = (char) (arr[i]-65+91);
                    }
                }
            }
        }
    }
}
