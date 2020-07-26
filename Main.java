package converter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        try{
            int sourceRadix = scan.nextByte();
            String num = scan.next();
            int targetRadix = scan.nextByte();

            if(sourceRadix <= 0 || sourceRadix > 36){
                System.out.println("error");
                System.exit(0);
            }
            if(targetRadix <= 0 || targetRadix > 36){
                System.out.println("error");
                System.exit(0);
            }


            String[] div = num.split("\\.");
            String s1 = intPartCon(sourceRadix, div[0], targetRadix);
            String s2 =  div.length == 1
                    ? frcPartCon(sourceRadix, "0", targetRadix)
                    : frcPartCon(sourceRadix, div[1], targetRadix);
            num = s1 + "." + s2;
            System.out.println(num);

        }catch (Exception e){
            System.out.println("error");
        }


    }

    private static String intPartCon(int sourceRadix, String num, int targetRadix) {
        int n = sourceRadix == 1 ? num.length() : Integer.parseInt(num, sourceRadix);
        return targetRadix == 1 ? "1".repeat(n) : Integer.toString(n, targetRadix);

    }

    private static String frcPartCon(int sourceRadix, String s, int targetRadix) {
        double num10 = 0;
        StringBuilder n = new StringBuilder();

        if (sourceRadix != 1)
            for (int i = 0; i < s.length(); i++) {
                num10 += Integer.parseInt(s.charAt(i) + "", sourceRadix) / Math.pow(sourceRadix, i + 1);
            }
        for (int i = 0; i < 5; i++) {
            int a = (int) (num10 * targetRadix);
            n.append(Integer.toString(a, targetRadix));
            num10 *= targetRadix; num10 -= a;
        }
        return n.toString();
    }
}

/*

 */