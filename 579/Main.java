import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
 
public class Main {
 
    private static final int maxElementValue = 10000;
 
    public static void main(String[] args) throws IOException {
        int inputLength;
        String line;
        StringBuilder output = new StringBuilder("");
 
        try(BufferedReader br = new BufferedReader(new FileReader("INPUT.TXT"))) {
            inputLength = Integer.parseInt(br.readLine());
            line = br.readLine();
        }
 
        int[] input = new int[inputLength];
        String[] rawInput = line.split("\\s");
        for(int i = 0 ; i < inputLength; i++){
            input[i] = Integer.parseInt(rawInput[i]);
        }
 
        int positiveElementsSum = 0;
        int negativeElementsSum = 0;
 
        Queue<Integer> positiveElementsIndices = new LinkedList<>();
        Queue<Integer> negativeElementsIndices = new LinkedList<>();
 
        for(int i = 0 ; i < inputLength; i++){
            int curElement = input[i];
            if(curElement != 0){
                if(curElement > 0){
                    positiveElementsIndices.add(i);
                    positiveElementsSum += curElement;
                } else {
                    negativeElementsIndices.add(i);
                    negativeElementsSum += curElement;
                }
            }
        }
 
        int subsequenceLength = 0;
 
        if((positiveElementsSum | negativeElementsSum) != 0){
            if(positiveElementsSum > Math.abs(negativeElementsSum)){
                subsequenceLength = positiveElementsIndices.size();
                for(int i = 0 ; i < subsequenceLength ; i++){
                    output.append(positiveElementsIndices.poll() + 1);
                    output.append(" ");
                }
            } else {
                subsequenceLength = negativeElementsIndices.size();
                for(int i = 0 ; i < subsequenceLength ; i++){
                    output.append(negativeElementsIndices.poll() + 1);
                    output.append(" ");
                }
            }
 
            output.deleteCharAt(output.length() - 1);
        } else {
            try(BufferedWriter bw = new BufferedWriter(new FileWriter("OUTPUT.TXT"))) {
                bw.write(String.valueOf(0));
            }
        }
 
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("OUTPUT.TXT"))) {
            bw.write(String.valueOf(subsequenceLength));
            bw.newLine();
            bw.write(output.toString());
        }
    }
}
