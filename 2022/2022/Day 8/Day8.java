import java.util.*;
import java.io.*;

public class Day8 {
    public static int[][] trees;
    public static void main(String[] args) {
        List<String> inputs = new ArrayList<>();
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

        trees = new int[inputs.size()][inputs.get(0).length()];
        for (int i = 0; i < inputs.size(); i++) {
            for (int j = 0; j <inputs.get(i).length(); j++) {
                trees[i][j] = Integer.parseInt(String.valueOf(inputs.get(i).charAt(j)));
            }
        }

        // Part A
        // int count = 0;
        // int[][] moves = {{1,0},{0,1},{-1,0},{0,-1}};
        // for (int i = 0; i < trees.length; i++) {
        //     for (int j = 0; j < trees[i].length; j++) {
        //         for (int[] move : moves) {
        //             int currX = i, currY = j;
        //             currX += move[0];
        //             currY += move[1];
        //             while (inRange(currX, currY) && trees[i][j] > trees[currX][currY]) {
        //                 currX += move[0];
        //                 currY += move[1];
        //             }
        //             if (!inRange(currX, currY)) {
        //                 count++;
        //                 break;
        //             }
        //         }
        //     }
        // }
        // System.out.println(count);

        // Part B
        int maxScenicScore = 1;
        int[][] moves = {{1,0},{0,1},{-1,0},{0,-1}};
        for (int i = 0; i < trees.length; i++) {
            for (int j = 0; j < trees[i].length; j++) {
                int currScenicScore = 1;
                for (int[] move : moves) {
                    int currX = i, currY = j, count = 0;
                    currX += move[0];
                    currY += move[1];
                    while (inRange(currX, currY) && trees[i][j] > trees[currX][currY]) {
                        currX += move[0];
                        currY += move[1];
                        count++;
                    }
                    if (!inRange(currX, currY)) {
                        currScenicScore *= count;
                        continue;
                    }
                    count++;
                    currScenicScore *= count;
                }
                maxScenicScore = Math.max(maxScenicScore, currScenicScore);
            }
        }
        System.out.println(maxScenicScore);
    }

    public static boolean inRange(int x, int y) {
        return (x >= 0 && x < trees.length) && (y >= 0 && y < trees[0].length);
    }
}