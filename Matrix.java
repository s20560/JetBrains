package processor;

import java.util.ArrayList;

public class Matrix {

    private int rows;
    private int columns;
    ArrayList<Double> elements = new ArrayList<>();
    private double[][] matrix;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        matrix = new double[rows][columns];
    }

    public Matrix(int rows, int columns, ArrayList<Double> elements) {
        this.rows = rows;
        this.columns = columns;
        this.elements = elements;
        matrix = new double[rows][columns];

        if(elements.size() == rows * columns) {
            int element = 0;
            for(int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    matrix[i][j] = elements.get(element);
                    element++;
                }
            }
        } else {
            System.out.println("Not enough elements passed");
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public Matrix addMatrix(Matrix matrixToAdd) {
        Matrix sum = new Matrix(rows, columns);
        if(rows == matrixToAdd.rows && columns == matrixToAdd.columns) {
            for(int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    sum.matrix[i][j] = matrix[i][j] + matrixToAdd.matrix[i][j];
                }
            }
        } else {
            System.out.println("ERROR");
        }
        return sum;
    }

    public Matrix multiplyByConstant(double constant) {
        Matrix result = new Matrix(rows, columns);
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++) {
                result.matrix[i][j] = matrix[i][j] * constant;
            }
        }
        return result;
    }

    public Matrix multiplyMatrixByMatrix(Matrix matrixToMultiply) {
        Matrix result = new Matrix(rows, matrixToMultiply.columns);
        int sum = 0;
        if(columns == matrixToMultiply.rows) {
            for(int i = 0; i < matrix.length; i++) {
                for(int j = 0; j < matrixToMultiply.columns; j++) {
                    for(int k = 0; k < matrix[i].length; k++){
                       result.matrix[i][j] += matrix[i][k] * matrixToMultiply.matrix[k][j];
                    }
                }
            }
        } else {
            System.out.println("Amount of columns of first matrix must be equal to rows of second matrix!");
        }
        return result;
    }

    public Matrix transposeMatrixByMainDiagonal() {
        Matrix result = new Matrix(columns, rows);
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++) {
                result.matrix[i][j] = matrix[j][i];
            }
        }
        return result;
    }

    public Matrix transposeMatrixBySideDiagonal() {
        Matrix result = new Matrix(columns, rows);
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++) {
                result.matrix[i][j] = matrix[columns - 1 - j][rows - 1 - i];
            }
        }
        return result;
    }

    public Matrix transposeMatrixByVerticalLine() {
        Matrix result = new Matrix(rows, columns);
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++) {
                result.matrix[i][j] = matrix[i][rows - 1 - j];
            }
        }
        return result;
    }

    public Matrix transposeMatrixByHorizontalLine() {
        Matrix result = new Matrix(rows, columns);
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++) {
                result.matrix[i][j] = matrix[columns - 1 - i][j];
            }
        }
        return result;
    }

    public double matrixDeterminant (double[][] matrix) {
        double[][] temporary;
        double result = 0;

        if(matrix.length == matrix[0].length) {
            if (matrix.length == 1) {
                result = matrix[0][0];
                return result;
            }
            if (matrix.length == 2) {
                result = ((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]));
                return result;
            }
            for (int i = 0; i < matrix[0].length; i++) {
                temporary = new double[matrix.length - 1][matrix[0].length - 1];
                for (int j = 1; j < matrix.length; j++) {
                    for (int k = 0; k < matrix[0].length; k++) {
                        if (k < i) {
                            temporary[j - 1][k] = matrix[j][k];
                        } else if (k > i) {
                            temporary[j - 1][k - 1] = matrix[j][k];
                        }
                    }
                }
                result += matrix[0][i] * Math.pow (-1, i) * matrixDeterminant (temporary);
            }
        } else {
            System.out.println("The matrix must be a square!");
        }
        return result;
    }

    public double[][] invertMatrix(double a[][]) {
        int n = a.length;
        double[][] x = new double[n][n];
        double[][] b = new double[n][n];
        int[] index = new int[n];
        for (int i=0; i<n; ++i)
            b[i][i] = 1;

        gaussian(a, index);

        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k]
                            -= a[index[j]][i]*b[index[i]][k];

        for (int i=0; i<n; ++i) {
            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k) {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

    public void gaussian(double[][] a, int[] index) {
        int n = index.length;
        double[] c = new double[n];

        for (int i=0; i<n; ++i)
            index[i] = i;

        for (int i=0; i<n; ++i) {
            double c1 = 0;
            for (int j=0; j<n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }
        int k = 0;
        for (int j=0; j<n-1; ++j) {
            double pi1 = 0;
            for (int i=j; i<n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i) {
                double pj = a[index[i]][j]/a[index[j]][j];
                a[index[i]][j] = pj;
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= pj*a[index[j]][l];
            }
        }
    }

    public void printMatrix() {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                System.out.print((double)Math.round(matrix[i][j]*100)/100 + " ");
            }
            System.out.println();
        }
    }
}