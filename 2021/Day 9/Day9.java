import java.io.*;
import java.util.*;

public class Day9
{
    static int[][] inputs = new int[0][0];
    public static void main(String[] args) 
    {
        ArrayList<String> strings = new ArrayList<>();
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            while ((line = br.readLine()) != null)
            {
                strings.add(line);
            }
            inputs = new int[strings.size()][strings.get(0).length()];
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        for (int i = 0; i < strings.size(); i++)
        {
            for (int j = 0; j < strings.get(0).length(); j++)
            {
                inputs[i][j] = Integer.parseInt(String.valueOf(strings.get(i).charAt(j)));
            }
        }
        int[][] answer = new int[inputs.length][inputs[0].length];
        for (int i = 0; i < answer.length; i++)
        {
            for (int j = 0; j < answer[0].length; j++)
            {
                answer[i][j] = -1;
            }
        }
        int numLowPoints = 0;
        int sum = 0;
        for (int row = 0; row < inputs.length; row++)
        {
            for (int col = 0; col < inputs[0].length; col++)
            {
                int[] lowPoint = findLowPoint(row, col, inputs);
                if (answer[lowPoint[0]][lowPoint[1]] == -1)
                {
                    answer[lowPoint[0]][lowPoint[1]] = lowPoint[2];
                    numLowPoints++;
                    sum += lowPoint[2];
                }
            }
        }
        //Part A answer
        System.out.println(sum + numLowPoints);

        ArrayList<Integer[]> lowPointLocations = new ArrayList<>();
        for (int i = 0; i < answer.length; i++)
        {
            for (int j = 0; j < answer[0].length; j++)
            {
                if (answer[i][j] != -1)
                {
                    lowPointLocations.add(new Integer[] {i, j});
                }
            }
        }
        ArrayList<Integer> sizes = new ArrayList<>();
        for (Integer[] location : lowPointLocations)
        {
            sizes.add(findSize(location[1], location[0]));
        }
        Collections.sort(sizes);
        Collections.reverse(sizes);
        //Part B answer
        System.out.println(sizes.get(0) * sizes.get(1) * sizes.get(2));
    }

    private static int findSize(int x, int y)
    {
        if (inRange(x, y) == 9)
        {
            return 0;
        }
        inputs[y][x] = 9;
        return 1 + findSize(x - 1, y) + findSize(x + 1, y) + findSize(x, y - 1) + findSize(x, y + 1);
    }

    private static int inRange(int x, int y)
    {
        return (x < 0 || x >= inputs[0].length || y < 0 || y >= inputs.length) ? 9 : inputs[y][x];
    }

    private static int[] findLowPoint(int row, int col, int[][] inputs)
    {
        if (row == 0 && col == 0)
        {
            int[][] temp = new int[][]{{row + 1, col, inputs[row + 1][col]}, {row, col + 1, inputs[row][col + 1]}};
            int[] min = findMinimum(temp);
            if (inputs[row][col] >= min[2])
            {
                return findLowPoint(min[0], min[1], inputs);
            }
            else
            {
                return new int[] {row, col, inputs[row][col]};
            }
        }
        if (row == inputs.length - 1 && col == 0)
        {
            int[][] temp = new int[][]{{row - 1, col, inputs[row - 1][col]}, {row, col + 1, inputs[row][col + 1]}};
            int[] min = findMinimum(temp);
            if (inputs[row][col] >= min[2])
            {
                return findLowPoint(min[0], min[1], inputs);
            }
            else
            {
                return new int[] {row, col, inputs[row][col]};
            }
        }
        if (row == 0 && col == inputs[0].length - 1)
        {
            int[][] temp = new int[][]{{row + 1, col, inputs[row + 1][col]}, {row, col - 1, inputs[row][col - 1]}};
            int[] min = findMinimum(temp);
            if (inputs[row][col] >= min[2])
            {
                return findLowPoint(min[0], min[1], inputs);
            }
            else
            {
                return new int[] {row, col, inputs[row][col]};
            }
        }
        if (row == inputs.length - 1 && col == inputs[0].length - 1)
        {
            int[][] temp = new int[][]{{row - 1, col, inputs[row - 1][col]}, {row, col - 1, inputs[row][col - 1]}};
            int[] min = findMinimum(temp);
            if (inputs[row][col] >= min[2])
            {
                return findLowPoint(min[0], min[1], inputs);
            }
            else
            {
                return new int[] {row, col, inputs[row][col]};
            }
        }
        if (row == 0)
        {
            int[][] temp = new int[][]{{row + 1, col, inputs[row + 1][col]}, {row, col + 1, inputs[row][col + 1]}, {row, col - 1, inputs[row][col - 1]}};
            int[] min = findMinimum(temp);
            if (inputs[row][col] >= min[2])
            {
                return findLowPoint(min[0], min[1], inputs);
            }
            else
            {
                return new int[] {row, col, inputs[row][col]};
            }
        }
        if (row == inputs.length - 1)
        {
            int[][] temp = new int[][]{{row - 1, col, inputs[row - 1][col]}, {row, col + 1, inputs[row][col + 1]}, {row, col - 1, inputs[row][col - 1]}};
            int[] min = findMinimum(temp);
            if (inputs[row][col] >= min[2])
            {
                return findLowPoint(min[0], min[1], inputs);
            }
            else
            {
                return new int[] {row, col, inputs[row][col]};
            }
        }
        if (col == 0)
        {
            int[][] temp = new int[][]{{row + 1, col, inputs[row + 1][col]}, {row, col + 1, inputs[row][col + 1]}, {row - 1, col, inputs[row - 1][col]}};
            int[] min = findMinimum(temp);
            if (inputs[row][col] >= min[2])
            {
                return findLowPoint(min[0], min[1], inputs);
            }
            else
            {
                return new int[] {row, col, inputs[row][col]};
            }
        }
        if (col == inputs[0].length - 1)
        {
            int[][] temp = new int[][]{{row + 1, col, inputs[row + 1][col]}, {row, col - 1, inputs[row][col - 1]}, {row - 1, col, inputs[row - 1][col]}};
            int[] min = findMinimum(temp);
            if (inputs[row][col] >= min[2])
            {
                return findLowPoint(min[0], min[1], inputs);
            }
            else
            {
                return new int[] {row, col, inputs[row][col]};
            }
        } 
        int[][] temp = new int[][]{{row + 1, col, inputs[row + 1][col]}, {row, col + 1, inputs[row][col + 1]},
                                    {row - 1, col, inputs[row - 1][col]}, {row, col - 1, inputs[row][col - 1]}};
        int[] min = findMinimum(temp);
        if (inputs[row][col] >= min[2])
        {
            return findLowPoint(min[0], min[1], inputs);
        }
        return new int[]{row, col, inputs[row][col]};
    }

    private static int[] findMinimum(int[][] arr)
    {
        int minIndex = 0;
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i][2] <= arr[minIndex][2])
            {
                minIndex = i;
            }
        }
        return arr[minIndex];
    }
}