package hashcode;

import java.io.BufferedWriter;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Path path = Paths.get("./input.txt");
        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            String[] secondLine;

            String[] firstLine = lines.get(0).split(" ");
            int totalSlices = Integer.parseInt(firstLine[0]);

            String secondLineVals = lines.get(1);
            secondLine = secondLineVals.split(" ");

            int sum = 0;
            String ind = "";
            ArrayList<Integer> arrOfSum = new ArrayList<>();
            ArrayList<String> index = new ArrayList<>();
            int opsize = (int)Math.pow(2, secondLine.length);

            for (int counter = 1; counter < opsize; counter++){
                for (int j = 0; j < secondLine.length; j++){
                /* Check if jth bit in the counter is set
                    If set then print jth element from arr[] */
                    if (BigInteger.valueOf(counter).testBit(j)) {
                        sum += Integer.parseInt(secondLine[j]);
                        ind += String.valueOf(j);
                    }
                }
                if (sum <= totalSlices) {
                    arrOfSum.add(sum);
                    index.add(ind);
                    ind = "";
                }
                sum = 0;
            }

            int max = 0;
            int maxindex = 0;
            for (int i = 0; i < arrOfSum.size(); i++) {
                if (arrOfSum.get(i) > max){
                    max = arrOfSum.get(i);
                    maxindex = arrOfSum.indexOf(max);
                }
            }
            int len = index.get(maxindex).length();
            String[] arrOfIndex = index.get(maxindex).split("");
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < len; i++) {
                str.append(arrOfIndex[i]).append(" ");
            }

            Path path1 = Paths.get("./output.txt");
            try(BufferedWriter bw = Files.newBufferedWriter(path1, Charset.defaultCharset())){
                bw.write(String.valueOf(len));
                bw.write("\n");
                bw.write(String.valueOf(str));
            }catch(Exception e){
                System.err.println("Exception : " + e.getMessage());
            }
            System.out.println(BigInteger.valueOf(10).testBit(2));
        }
        catch (Exception e){
            System.err.println("Exception : " + e.getMessage());
        }
    }
}
