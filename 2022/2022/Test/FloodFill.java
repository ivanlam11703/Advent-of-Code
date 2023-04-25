import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FloodFill {

    // Set of visited cells
    private static Set<String> visited = new HashSet<>();
    private static int[][][] grid = null;

    public static void main(String[] args) {
        // Define the grid of cubes
        // int[][][] grid = null;

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            // Read the first line to get the dimensions of the grid
            String[] dimensions = reader.readLine().split(",");
            int width = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);
            int depth = Integer.parseInt(dimensions[2]);

            // Initialize the grid
            grid = new int[width][height][depth];

            // Read the rest of the lines to populate the grid
            String line;
            while ((line = reader.readLine()) != null) {
                String[] coords = line.split(",");
                int x = Integer.parseInt(coords[0]);
                int y = Integer.parseInt(coords[1]);
                int z = Integer.parseInt(coords[2]);
                grid[x][y][z] = 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int surfaceArea = 0;

        // Iterate through each cell in the grid
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                for (int z = 0; z < grid[0][0].length; z++) {
                    // If the cell is a cube and has not been visited
                    if (grid[x][y][z] == 1 && !visited.contains(x + "," + y + "," + z)) {
                        // Flood fill the connected region of cubes starting at this cell
                        surfaceArea += floodFill(x, y, z);
                    }
                }
            }
        }

        // Print the surface area
        System.out.println("Surface area: " + surfaceArea);
    }

    // Flood fill function that finds the surface area of a connected region of cubes
    private static int floodFill(int x, int y, int z) {
        int surfaceArea = 0;

        // Add the cell to the set of visited cells
        visited.add(x + "," + y + "," + z);

        // Check the 6 adjacent cells and flood fill if necessary
        if (x > 0 && grid[x - 1][y][z] == 1 && !visited.contains((x - 1) + "," + y + "," + z)) {
            // Increase surface area and flood fill the adjacent cell
            surfaceArea++;
            surfaceArea += floodFill(x - 1, y, z);
        } else {
            // The adjacent cell is not a cube or has already been visited, so increase the surface area
            surfaceArea++;
        }
        if (x < grid.length - 1 && grid[x + 1][y][z] == 1 && !visited.contains((x + 1) + "," + y + "," + z)) {
            surfaceArea++;
            surfaceArea += floodFill(x + 1, y, z);
        } else {
            surfaceArea++;
        }
        if (y > 0 && grid[x][y - 1][z] == 1 && !visited.contains(x + "," + (y - 1) + "," + z)) {
            surfaceArea++;
            surfaceArea += floodFill(x, y - 1, z);
        } else {
            surfaceArea++;
        }
        if (y < grid[0].length - 1 && grid[x][y + 1][z] == 1 && !visited.contains(x + "," + (y + 1) + "," + z)) {
            surfaceArea++;
            surfaceArea += floodFill(x, y + 1, z);
        } else {
            surfaceArea++;
        }
        if (z > 0 && grid[x][y][z - 1] == 1 && !visited.contains(x + "," + y + "," + (z - 1))) {
            surfaceArea++;
            surfaceArea += floodFill(x, y, z - 1);
        } else {
            surfaceArea++;
        }
        if (z < grid[0][0].length - 1 && grid[x][y][z + 1] == 1 && !visited.contains(x + "," + y +(z + 1))) {
            surfaceArea++;
            surfaceArea += floodFill(x, y, z + 1);
        } else {
            surfaceArea++;
        }

        return surfaceArea;
    }
}