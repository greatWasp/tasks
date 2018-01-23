import java.io.*;
import java.util.Arrays;

public class Main {

    static int m;
    static int n;
    static int a;
    static int b;
    static int p;
    static int[][][] matrices;

    public static void main(String[] args) throws IOException {

        String[] numbersBuffer;

        try(BufferedReader br = new BufferedReader(new FileReader("INPUT.TXT"))){
            numbersBuffer = br.readLine().split(" ");
            m = Integer.parseInt(numbersBuffer[0]);
            n = Integer.parseInt(numbersBuffer[1]);

            matrices = new int[m][n][n];

            numbersBuffer = br.readLine().split(" ");
            a = Integer.parseInt(numbersBuffer[0]);
            b = Integer.parseInt(numbersBuffer[1]);

            p = Integer.parseInt(br.readLine());

            for(int i = 0 ; i < m ; i++){
                br.readLine();
                for(int j = 0 ; j < n ; j++){
                    numbersBuffer = br.readLine().split(" ");
                    for(int k = 0 ; k < n ; k++){
                        matrices[i][j][k] = Integer.parseInt(numbersBuffer[k]);
                    }
                }
            }
        }

	int mappedRowCoord = a - 1;
        int mappedColCoord = b - 1;

        if(m == 1){
            try(BufferedWriter bw = new BufferedWriter(new FileWriter("OUTPUT.TXT"))){
                bw.write(String.valueOf(matrices[0][mappedRowCoord][mappedColCoord]));
            }
            return;
        }

        int[][] finalResultMatrix = new int[n][n];
        int[][] auxResultMatrix = new int[n][n];

        multiplyRowAndCol(finalResultMatrix, matrices[0], matrices[1], mappedRowCoord, mappedColCoord);

        if(m > 2){
            for(int i = 2 ; i < m - 1 ; i++){
                for(int j = 0 ; j < n ; j++){
                    auxResultMatrix[j] = Arrays.copyOf(finalResultMatrix[j], n);
                }
                multiplyRowAndCol(finalResultMatrix, auxResultMatrix, matrices[i], mappedRowCoord, mappedColCoord);
            }

            for(int j = 0 ; j < n ; j++){
                auxResultMatrix[j] = Arrays.copyOf(finalResultMatrix[j], n);
            }

            try(BufferedWriter bw = new BufferedWriter(new FileWriter("OUTPUT.TXT"))){
                bw.write(String.valueOf(getMultiplicationResultForIndex(auxResultMatrix, matrices[m - 1], mappedRowCoord, mappedColCoord)));
            }
        } else {
            try(BufferedWriter bw = new BufferedWriter(new FileWriter("OUTPUT.TXT"))){
                bw.write(String.valueOf(finalResultMatrix[mappedRowCoord][mappedColCoord]));
            }
        }
    }

    static int getMultiplicationResultForIndex(int[][] matrix1, int[][] matrix2, int row, int col){
        int result = 0;
        for(int i = 0 ; i < n; i++){
            result += matrix1[row][i] * matrix2[i][col];
        }
        return result % p;
    }

    static void multiplyRowAndCol(int[][] result, int[][] matrix1, int[][] matrix2, int row, int col){
        for(int i = 0; i < n ; i++){
            result[row][i] = getMultiplicationResultForIndex(matrix1, matrix2, row, i);
            result[i][col] = getMultiplicationResultForIndex(matrix1, matrix2, i, col);
        }
    }
}
