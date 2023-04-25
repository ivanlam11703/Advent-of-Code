import java.io.*;
import java.util.*;

public class Day9 {
    public static void main(String[] args) {
        List<String> inputs = new ArrayList<>();
        try {
            BufferedReader br =new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            while ((line = br.readLine()) != null) {
                inputs.add(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        char[][] grid = new char[10000][10000];
        for (char[] c : grid) {
            Arrays.fill(c, '.');
        }

        // Part B
        List<Integer[]> ropes = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            Integer[] temp = new Integer[]{(grid.length-1)/2,(grid[0].length-1)/2};
            ropes.add(temp);
        }
        int[][] moves = {{0,0},{1,0},{0,1},{-1,0},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};
        for (String s : inputs) {
            StringTokenizer st = new StringTokenizer(s);
            char direction = st.nextToken().charAt(0);
            int distance = Integer.parseInt(st.nextToken());

            for (int i = 0; i < distance; i++) {
                grid[ropes.get(9)[0]][ropes.get(9)[1]] = '#';
                if (direction == 'L') {
                    ropes.get(0)[1]--;
                } else if (direction == 'R') {
                    ropes.get(0)[1]++;
                } else if (direction == 'U') {
                    ropes.get(0)[0]--;
                } else {
                    ropes.get(0)[0]++;
                }
                for (int j = 1; j < ropes.size(); j++) {
                    boolean foundLastKnot = false;
                    for (int[] move : moves) {
                        int tempX = ropes.get(j)[0] + move[0];
                        int tempY = ropes.get(j)[1] + move[1];
                        if (tempX == ropes.get(j-1)[0] && tempY == ropes.get(j-1)[1]) {
                            foundLastKnot = true;
                            break;
                        }
                    }
                    if (!foundLastKnot) {
                        int tempX = ropes.get(j)[0];
                        int tempY = ropes.get(j)[1];
                        int oldX = ropes.get(j-1)[0];
                        int oldY = ropes.get(j-1)[1];
                        int xMoved = oldX - tempX;
                        int yMoved = oldY - tempY;
                        if ((xMoved != 1 && xMoved != -1) && (yMoved != 1 && yMoved != -1)) {
                            ropes.get(j)[0] += xMoved / 2;
                            ropes.get(j)[1] += yMoved / 2;
                        } else {
                            if (xMoved != 2 && xMoved != -2) {
                                ropes.get(j)[0] += xMoved;
                                ropes.get(j)[1] += yMoved/2; 
                            } else {
                                ropes.get(j)[0] += xMoved/2;
                                ropes.get(j)[1] += yMoved;
                            }
                        }
                    }
                }
            }
        }

        grid[ropes.get(9)[0]][ropes.get(9)[1]] = '#';
        int count = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] == '#') {
                    count++;
                }
            }
        }
        System.out.println(count);

        // Part A
        // int headX = (grid.length-1)/2, headY = (grid[0].length-1)/2, tailX = headX, tailY = headY, oldHeadX = headX, oldHeadY=headY;
        // int[][] moves = {{0,0},{1,0},{0,1},{-1,0},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};
        // for (String s : inputs) {
        //     StringTokenizer st = new StringTokenizer(s);
        //     char direction = st.nextToken().charAt(0);
        //     int distance = Integer.parseInt(st.nextToken());
        //     for (int i = 0; i < distance; i++) {
        //         grid[tailX][tailY] = '#';
        //         oldHeadX = headX;
        //         oldHeadY = headY;
                // if (direction == 'L') {
                //     headY--;
                // } else if (direction == 'R') {
                //     headY++;
                // } else if (direction == 'U') {
                //     headX--;
                // } else {
                //     headX++;
                // }
        //         boolean foundHead = false;
        //         for (int[] move : moves) {
        //             int tempTailX = tailX + move[0];
        //             int tempTailY = tailY + move[1];
        //             if (tempTailX == headX && tempTailY == headY) {
        //                 foundHead = true;
        //                 break;
        //             }
        //         }
        //         if (!foundHead) {
        //             tailX = oldHeadX;
        //             tailY = oldHeadY;
        //         }
        //     }
        // }
        // grid[tailX][tailY] = '#';
        // int count = 0;
        // for (int x = 0; x < grid.length; x++) {
        //     for (int y = 0; y < grid[x].length; y++) {
        //         if (grid[x][y] == '#') {
        //             count++;
        //         }
        //     }
        // }
        // System.out.println(count);
    }
}