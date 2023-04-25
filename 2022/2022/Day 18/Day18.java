import java.util.*;
import java.io.*;

public class Day18 {
    public static void main(String[] args) {
        List<String> inputs = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("testinput.txt")));
            String line = "";
            while ((line = br.readLine()) != null) {
                line = line.replace(",", " ");
                inputs.add(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        int count = 0;
        for (String s : inputs) {
            StringTokenizer st = new StringTokenizer(s);
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
        }

        // Part B
        

        // Part A
        // if (!inputs.contains((x + 1) + " " + y + " " + z)) {
        //     count++;
        // }
        // if (!inputs.contains((x - 1) + " " + y + " " + z)) {
        //     count++;
        // }
        // if (!inputs.contains(x + " " + (y + 1) + " " + z)) {
        //     count++;
        // }
        // if (!inputs.contains(x + " " + (y - 1) + " " + z)) {
        //     count++;
        // }
        // if (!inputs.contains(x + " " + y + " " + (z + 1))) {
        //     count++;
        // }
        // if (!inputs.contains(x + " " + y + " " + (z - 1))) {
        //     count++;
        // }
        // System.out.println(count);
    }

    static int floodFill(int[][][] grid, int x, int y, int z) {
        int[] X_OFFSETS = { -1, 0, 1, 0 };
        int[] Y_OFFSETS = { 0, 1, 0, -1 };
        int[] Z_OFFSETS = { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, -1 };
        int surfaceArea = 0;

        if (grid[x][y][z] == 1) {
            return surfaceArea;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { x, y, z });
        grid[x][y][z] = 1;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currX = curr[0];
            int currY = curr[1];
            int currZ = curr[2];

            // Check the six faces of the current cube
            for (int i = 0; i < X_OFFSETS.length; i++) {
                int newX = currX + X_OFFSETS[i];
                int newY = currY + Y_OFFSETS[i];
                int newZ = currZ + Z_OFFSETS[i];
                if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && newZ >= 0 && newZ < grid[0][0].length && grid[newX][newY][newZ] == 0) {
                    surfaceArea++;
                    grid[newX][newY][newZ] = 1;
                    queue.offer(new int[] { newX, newY, newZ });
                }
            }
        }

        return surfaceArea;
    }
}