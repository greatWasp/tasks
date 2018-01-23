import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        String answer = "NO";

        char[] s;
        char[] t;

        try(BufferedReader br = new BufferedReader(new FileReader("INPUT.TXT"))){
            s = br.readLine().toCharArray();
            t = br.readLine().toCharArray();
        }

        if(s.length >= t.length && !Arrays.equals(s, t)){
            try(BufferedWriter bw = new BufferedWriter(new FileWriter("OUTPUT.TXT"))){
                bw.write("NO");
            }
        }

        int sLength = s.length;
        int tLength = t.length;
        int curSpos = 0;
        int curTpos = 0;

        while((sLength - curSpos) <= (tLength - curTpos)){
            if(s[curSpos] == t[curTpos]){
                curSpos++;
                curTpos++;
            } else {
                curTpos++;
            }
            if(curSpos == sLength){
                answer = "YES";
                break;
            }
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("OUTPUT.TXT"))){
            bw.write(answer);
        }
    }
}
