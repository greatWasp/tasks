import java.io.*;
import java.util.regex.Pattern;

public class Main {

    static int[] numbers = new int[10001];

    public static void main(String[] args) throws IOException {

        generateSequence();

        int N;

        try(BufferedReader br = new BufferedReader(new FileReader("INPUT.TXT"))){
            N = Integer.parseInt(br.readLine());
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("OUTPUT.TXT"))){
            bw.write(String.valueOf(numbers[N]));
        }
    }

    public static void generateSequence() {
        for(int i = 1, z = 0 ; i <= 26057 ; i++) {
            if(Pattern.matches("^(?!.*(.).*\\1)\\d+$", String.valueOf(i))){
                numbers[++z] = i;
            }
        }
    }
}
