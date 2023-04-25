import java.io.*;
import java.util.*;



public class Day14 {
    static char[][] grid = new char[0][0];
    public static void main(String[] args) {
        List<String> inputs = new ArrayList<>();
        int x = -1, y = -1;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            while ((line = br.readLine()) != null) {
                line = line.replace(" -> ", ",");
                StringTokenizer st = new StringTokenizer(line, ",");
                while (st.hasMoreTokens()) {
                    x = Math.max(Integer.parseInt(st.nextToken()), x);
                    y = Math.max(Integer.parseInt(st.nextToken()), y);
                }
                inputs.add(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        grid = makeGrid(inputs, x, y);

        // Part A (make sure to comment and uncomment functions as needed)
        // System.out.println(simulateSand(500,0));
        
        // Part B needs +1 to plug the hole where the sand is coming from
        System.out.println(simulateSand(500,0) + 1);
    }

    public static int simulateSand(int x, int y) {
        int count = 0;
        while (fall(x, y)[0] != -1) {
            count++;
        }
        return count;
    }

    // Part B fall() function
    public static int[] fall(int x, int y) {
        if (grid[1][500] != '.' && grid[1][501] != '.' && grid[1][499] != '.') {
            return new int[] {-1};
        }
        if (grid[y+1][x] == '.') {
            return fall(x, y+1);
        }
        if (grid[y+1][x-1] == '.') {
            return fall(x-1, y+1);
        }
        if (grid[y+1][x+1] == '.') {
            return fall(x+1, y+1);
        }
        grid[y][x] = 'O';
        return new int[] {x,y};
    }

    // Part B makeGrid() function
    public static char[][] makeGrid(List<String> inputs, int x, int y) {
        char[][] grid = new char[y+3][x+1000];
        for (char[] arr : grid) {
            Arrays.fill(arr, '.');
        }
        Arrays.fill(grid[grid.length-1], '#');
        for (int i = 0; i < inputs.size(); i++) {
            StringTokenizer st = new StringTokenizer(inputs.get(i),",");
            int x1 = Integer.parseInt(st.nextToken()), y1 = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                grid[y2][x2] = '#';
                if (x1 == x2) {
                    if (y1 < y2) {
                        while (y1 < y2) {
                            grid[y1][x1] = '#';
                            y1++;
                        }
                    } else {
                        while (y1 > y2) {
                            grid[y1][x1] = '#';
                            y1--;
                        }
                    }
                } else {
                    if (x1 < x2) {
                        while (x1 < x2) {
                            grid[y1][x1] = '#';
                            x1++;
                        }
                    } else {
                        while (x1 > x2) {
                            grid[y1][x1] = '#';
                            x1--;
                        }
                    }
                }
            }
        }
        return grid;
    }

    // Part A fall() function
    // public static int[] fall(int x, int y) {
    //     if (y >= grid.length-1 || x >= grid[0].length-1) {
    //         return new int[] {-1};
    //     }
    //     if (grid[y+1][x] == '.') {
    //         return fall(x, y+1);
    //     }
    //     if (grid[y+1][x-1] == '.') {
    //         return fall(x-1, y+1);
    //     }
    //     if (grid[y+1][x+1] == '.') {
    //         return fall(x+1, y+1);
    //     }
    //     grid[y][x] = 'O';
    //     return new int[] {x,y};
    // }

    // Part A makeGrid() function
    // public static char[][] makeGrid(List<String> inputs, int x, int y) {
    //     char[][] grid = new char[y+2][x+1];
    //     for (char[] arr : grid) {
    //         Arrays.fill(arr, '.');
    //     }
    //     for (int i = 0; i < inputs.size(); i++) {
    //         StringTokenizer st = new StringTokenizer(inputs.get(i),",");
    //         int x1 = Integer.parseInt(st.nextToken()), y1 = Integer.parseInt(st.nextToken());
    //         while (st.hasMoreTokens()) {
    //             int x2 = Integer.parseInt(st.nextToken());
    //             int y2 = Integer.parseInt(st.nextToken());
    //             grid[y2][x2] = '#';
    //             if (x1 == x2) {
    //                 if (y1 < y2) {
    //                     while (y1 < y2) {
    //                         grid[y1][x1] = '#';
    //                         y1++;
    //                     }
    //                 } else {
    //                     while (y1 > y2) {
    //                         grid[y1][x1] = '#';
    //                         y1--;
    //                     }
    //                 }
    //             } else {
    //                 if (x1 < x2) {
    //                     while (x1 < x2) {
    //                         grid[y1][x1] = '#';
    //                         x1++;
    //                     }
    //                 } else {
    //                     while (x1 > x2) {
    //                         grid[y1][x1] = '#';
    //                         x1--;
    //                     }
    //                 }
    //             }
    //         }
    //     }
    //     return grid;
    // }
}