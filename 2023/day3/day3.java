import java.util.*;
import java.io.*;

public class day3 {

    public static List<String> inputs = new ArrayList<>();
    public static List<Integer> numbers = new ArrayList<>();
    static int pt1sum = 0;
    static int pt2sum = 0;

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            while ((line = br.readLine()) != null) {
                inputs.add(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println(pt1());
        System.out.println(pt2());
    }

    public static boolean outOfRange(int x, int y) {
        return (x < 0 || x >= inputs.get(0).length() || y < 0 || y >= inputs.size());
    }

    public static boolean hasSymbol(int index, int startIndex) {
        int[][] moves = new int[][]{{0,1}, {1,1}, {1,0}, {1,-1}, {-1, 1}, {-1,-1}, {0,-1}, {-1,0}};
        for (int[] move : moves) {
            int tempX = index + move[0];
            int tempY = startIndex + move[1];
            if (!outOfRange(tempX,tempY)) {
                char c = inputs.get(tempX).charAt(tempY);
                if(c != '.' && !Character.isDigit(c)) {
                    return true;
                } 
            }
        }
        return false;
    }

    public static boolean hasAsterisk(int index, int startIndex) {
        int[][] moves = new int[][]{{0,1}, {1,1}, {1,0}, {1,-1}, {-1, 1}, {-1,-1}, {0,-1}, {-1,0}};
        for (int[] move : moves) {
            int tempX = index + move[0];
            int tempY = startIndex + move[1];
            if (!outOfRange(tempX,tempY)) {
                char c = inputs.get(tempX).charAt(tempY);
                if(c == '*') {
                    return true;
                } 
            }
        }
        return false;
    }

        public static int[] getAsteriskIndex(int index, int startIndex) {
        int[][] moves = new int[][]{{0,1}, {1,1}, {1,0}, {1,-1}, {-1, 1}, {-1,-1}, {0,-1}, {-1,0}};
        for (int[] move : moves) {
            int tempX = index + move[0];
            int tempY = startIndex + move[1];
            if (!outOfRange(tempX,tempY)) {
                char c = inputs.get(tempX).charAt(tempY);
                if(c == '*') {
                    return new int[] {tempX, tempY};
                } 
            }
        }
        return new int[] {-1,-1};
    }

    public static int pt1() {
        for (int i = 0; i < inputs.size(); i++) {
            String temp = inputs.get(i);
            for (int j = 0; j < temp.length(); j++) {
                String currentNumber = "";
                boolean validNumber = false;
                if (temp.charAt(j) == '.') {
                    continue;
                } else {
                    while (!outOfRange(i, j) && Character.isDigit(temp.charAt((j)))) {
                        if (!validNumber) {
                            validNumber = hasSymbol(i, j);
                        }
                        currentNumber += temp.charAt(j);
                        j++;
                    }
                    if (validNumber) {
                        pt1sum += Integer.parseInt(currentNumber);
                    }
                }
            }
        }
        return pt1sum;
    }

    public static int pt2() {
        HashMap<String, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < inputs.size(); i++) {
            String temp = inputs.get(i);
            for (int j = 0; j < temp.length(); j++) {
                String key = "";
                int[] keyIndex = new int[]{};
                String currentNumber = "";
                boolean validNumber = false;
                if (temp.charAt(j) == '.') {
                    continue;
                } else {
                    while (!outOfRange(i, j) && Character.isDigit(temp.charAt((j)))) {
                        if (!validNumber) {
                            validNumber = hasAsterisk(i, j);
                            if (validNumber) {
                                keyIndex = getAsteriskIndex(i, j);
                            }
                        }
                        currentNumber += temp.charAt(j);
                        j++;
                    }
                    if (validNumber) {
                        key = Integer.toBinaryString(keyIndex[0]) + Integer.toString(keyIndex[1]);

                        if (!map.containsKey(key)) {
                            map.put(key, new ArrayList<>());
                        }
                        map.get(key).add(Integer.parseInt(currentNumber));
                    }
                }
            }
        }
        for (String key : map.keySet()) {
            if (map.get(key).size() == 2) {
                pt2sum += map.get(key).get(0) * map.get(key).get(1);
            }
        }
        return pt2sum;
    }
}