import java.io.*;
import java.util.*;

public class Day4
{
    public static void main(String[] args) 
    {
        List<String> inputs = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            while((line = br.readLine()) != null) {
                line = line.replace(',', ' ');
                line = line.replace('-', ' ');
                inputs.add(line);
            }
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        int answer = 0;
        for (int i = 0; i < inputs.size(); i++) {
            StringTokenizer st = new StringTokenizer(inputs.get(i));
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            if (first > second) {
                int hold = second;
                second = first;
                first = hold;
            }
            values.add(first);
            values.add(second);
            first = Integer.parseInt(st.nextToken());
            second = Integer.parseInt(st.nextToken());
            if (first > second) {
                int hold = second;
                second = first;
                first = hold;
            }
            values.add(first);
            values.add(second);
        }
        
        // Part A
        // for (int i = 0; i < values.size(); i+=4) {
        //     if (values.get(i) <= values.get(i+2) && values.get(i+1) >= values.get(i+3)) {
        //         answer++;
        //     } else if (values.get(i) >= values.get(i+2) && values.get(i+1) <= values.get(i+3)) {
        //         answer++;
        //     }
        // }

        // Part B
        for (int i = 0; i < values.size(); i+=4) {
            int x1 = values.get(i), y1 = values.get(i+1), x2 = values.get(i+2), y2 = values.get(i+3);
            if(x1 > x2) {
                int temp = x1;
                x1 = x2;
                x2 = temp;
                temp = y1;
                y1 = y2;
                y2 = temp;
            }
            if (x2 <= y1) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}