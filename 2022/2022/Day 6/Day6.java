import java.io.*;
import java.util.*;

public class Day6 {
    public static void main(String[] args) {
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            line = br.readLine();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        // Part A
        for (int i = 0; i < line.length()-3; i++) {
            String substr = line.substring(i, i+4);
            if (substr.chars().distinct().count() == 4) {
                System.out.println(i+4);
                break;
            }
        }

        // Part B (changed line.length()-3 => line.length()-13 & 4 => 14)
        for (int i = 0; i < line.length()-13; i++) {
            String substr = line.substring(i, i + 14);
            if (substr.chars().distinct().count() == 14) {
                System.out.println(i+14);
                break;
            }
        }
        System.out.println(line);
        System.out.println(detectFirstStartOfPacketMarker(line));
    }

    public static int detectFirstStartOfPacketMarker(String buffer) {
        // Keep track of the last four characters that have been processed
        String lastFour = "";
    
        // Keep track of the position of the first start-of-packet marker
        // that has been seen so far
        int markerPos = -1;
    
        // Process each character in the buffer one at a time
        for (int i = 0; i < buffer.length(); i++) {
            // Add the next character to the end of the lastFour string
            if (lastFour.length() > 0) {
                lastFour = lastFour.substring(1) + String.valueOf(buffer.charAt(i));
            } else {
                lastFour = String.valueOf(buffer.charAt(i));
            }
    
            // If the last four characters are all different, then we have
            // found a start-of-packet marker
            if (lastFour.chars().distinct().count() == 4) {
                // Update the position of the first start-of-packet marker
                // that has been seen so far
                if (markerPos == -1 || i < markerPos) {
                    markerPos = i;
                }
            }
        }
    
        // Return the position of the first start-of-packet marker that
        // was seen
        return markerPos + 1;
    }
}