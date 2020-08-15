package processor;

import java.util.ArrayList;
import java.util.Scanner;

public class Console {

    Scanner sc = new Scanner(System.in);
    String input;

    public void openMenu() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("1. Add matrices");
            System.out.println("2. Multiply matrix to a constant");
            System.out.println("3. Multiply matrices");
            System.out.println("4. Transpose matrix");
            System.out.println("5. Calculate a determinant");
            System.out.println("6. Inverse matrix");
            System.out.println("0. Exit");
            System.out.println();
            System.out.print("Your choice: ");
            input = sc.next();

            switch (input) {
                case "1":
                    System.out.print("Enter size of first matrix: ");
                    int rows1 = sc.nextInt();
                    int columns1 = sc.nextInt();
                    System.out.println("Enter the first matrix:");
                    Matrix m1 = new Matrix(rows1,columns1,matrixFill(rows1,columns1));

                    System.out.print("Enter size of second matrix: ");
                    int rows2 = sc.nextInt();
                    int columns2 = sc.nextInt();
                    System.out.println("Enter the second matrix:");
                    Matrix m2 = new Matrix(rows2,columns2,matrixFill(rows2,columns2));

                    System.out.println();
                    System.out.println("The adding result is:");
                    Matrix result = m1.addMatrix(m2);
                    result.printMatrix();
                    System.out.println();
                    break;

                case "2":
                    System.out.print("Enter size of matrix: ");
                    rows1 = sc.nextInt();
                    columns1 = sc.nextInt();
                    System.out.println("Enter the matrix:");
                    m1 = new Matrix(rows1,columns1,matrixFill(rows1,columns1));

                    System.out.print("Enter the constant");
                    int constant = sc.nextInt();

                    System.out.println();
                    System.out.println("The multiplication result is:");
                    result = m1.multiplyByConstant(constant);
                    result.printMatrix();
                    System.out.println();
                    break;

                case "3":
                    System.out.print("Enter size of first matrix: ");
                    rows1 = sc.nextInt();
                    columns1 = sc.nextInt();
                    System.out.println("Enter the first matrix:");
                    m1 = new Matrix(rows1,columns1,matrixFill(rows1,columns1));

                    System.out.print("Enter size of second matrix: ");
                    rows2 = sc.nextInt();
                    columns2 = sc.nextInt();
                    System.out.println("Enter the second matrix:");
                    m2 = new Matrix(rows2,columns2,matrixFill(rows2,columns2));

                    System.out.println();
                    System.out.println("The multiplication result is:");
                    result = m1.multiplyMatrixByMatrix(m2);
                    result.printMatrix();
                    System.out.println();
                    break;

                case "4":
                    System.out.println();
                    System.out.println("1. Main diagonal");
                    System.out.println("2. Side diagonal");
                    System.out.println("3. Vertical line");
                    System.out.println("4. Horizontal line");
                    System.out.println();
                    System.out.print("Your choice: ");
                    input = sc.next();
                    System.out.println();

                    switch (input){
                        case "1":
                            System.out.print("Enter size of matrix: ");
                            rows1 = sc.nextInt();
                            columns1 = sc.nextInt();
                            System.out.println("Enter the matrix:");
                            m1 = new Matrix(rows1,columns1,matrixFill(rows1,columns1));

                            System.out.println();
                            System.out.println("The result is:");
                            result = m1.transposeMatrixByMainDiagonal();
                            result.printMatrix();
                            break;

                        case "2":
                            System.out.print("Enter size of matrix: ");
                            rows1 = sc.nextInt();
                            columns1 = sc.nextInt();
                            System.out.println("Enter the matrix:");
                            m1 = new Matrix(rows1,columns1,matrixFill(rows1,columns1));

                            System.out.println();
                            System.out.println("The result is:");
                            result = m1.transposeMatrixBySideDiagonal();
                            result.printMatrix();
                            break;

                        case "3":
                            System.out.print("Enter size of matrix: ");
                            rows1 = sc.nextInt();
                            columns1 = sc.nextInt();
                            System.out.println("Enter the matrix:");
                            m1 = new Matrix(rows1,columns1,matrixFill(rows1,columns1));

                            System.out.println();
                            System.out.println("The result is:");
                            result = m1.transposeMatrixByVerticalLine();
                            result.printMatrix();
                            break;

                        case "4":
                            System.out.print("Enter size of matrix: ");
                            rows1 = sc.nextInt();
                            columns1 = sc.nextInt();
                            System.out.println("Enter the matrix:");
                            m1 = new Matrix(rows1,columns1,matrixFill(rows1,columns1));

                            System.out.println();
                            System.out.println("The result is:");
                            result = m1.transposeMatrixByHorizontalLine();
                            result.printMatrix();
                            break;

                        default:
                            System.out.println("Choose correct option!");
                            break;
                    }
                    break;

                case "5":
                    System.out.print("Enter size of matrix: ");
                    rows1 = sc.nextInt();
                    columns1 = sc.nextInt();
                    System.out.println("Enter the matrix:");
                    m1 = new Matrix(rows1,columns1,matrixFill(rows1,columns1));

                    System.out.println();
                    System.out.println("The result is:");
                    System.out.println(m1.matrixDeterminant(m1.getMatrix()));
                    System.out.println();
                    break;

                case "6":
                    System.out.print("Enter size of matrix: ");
                    rows1 = sc.nextInt();
                    columns1 = sc.nextInt();
                    System.out.println("Enter the matrix:");
                    m1 = new Matrix(rows1,columns1,matrixFill(rows1,columns1));
                    double[][] inverted = m1.invertMatrix(m1.getMatrix());
                    m1.setMatrix(inverted);
                    m1.printMatrix();

                    System.out.println();
                    System.out.println("The result is:");

                    System.out.println();
                    break;

                case "0":
                    isRunning = false;
                    break;

                default:
                    System.out.println("Choose correct option!");
                    break;
            }
        }
    }

    public static ArrayList<Double> matrixFill(int rows, int columns) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Double> elements = new ArrayList<>();

        for(int i = 0; i < rows; i++){
            String[] input = sc.nextLine().split(" ");
            for(int j = 0; j < columns; j ++){
                elements.add(Double.parseDouble(input[j]));
            }
        }
        return elements;
    }
}