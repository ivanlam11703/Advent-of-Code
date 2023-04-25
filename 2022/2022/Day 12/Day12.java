import java.io.*;
import java.util.*;

public class Day12 {
    static char[][] grid = new char[0][0];
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
        int[] start = new int[2];
        int[] end = new int[2];
        ArrayList<int[]> toCheck = new ArrayList<>();
        grid = new char[inputs.size()][inputs.get(0).length()];
        for (int i = 0; i < inputs.size(); i++) {
            for (int j = 0; j < inputs.get(i).length(); j++) {
                char c = inputs.get(i).charAt(j);
                grid[i][j] = c;
                if (c == 'a') {
                    int[] temp = {i,j};
                    toCheck.add(temp);
                }
                if (c == 'S') {
                    start[0] = i;
                    start[1] = j;
                    grid[i][j] = 'a';
                    toCheck.add(start.clone());
                }
                if (c == 'E') {
                    end[0] = i;
                    end[1] = j;
                    grid[i][j] = 'z';
                }
            }
        }

        // Part A - comment out for loop and reassignment of start coordinates
        // also get rid of Math.min() and uncomment println within the if statement
        // Part B functions as code stands currently (it is pretty slow though)
        long minDistance = Long.MAX_VALUE;
        for (int z = 0; z < toCheck.size(); z++) {
            start[0] = toCheck.get(z)[0];
            start[1] = toCheck.get(z)[1];
            PriorityQueue<Node> weight = new PriorityQueue<>(new NodeComparator());
            Node[][] travelCost = new Node[grid.length][grid[0].length];
            int[][][] parent = new int[grid.length][grid[0].length][2];
            for (int i = 0; i < travelCost.length; i++) {
                for (int j = 0; j < travelCost[i].length; j++) {
                    int[][] temp = validMoves(i,j);
                    travelCost[i][j] = new Node(i, j , Long.MAX_VALUE, temp.length);
                    for (int x = 0; x < temp.length; x++) {
                        travelCost[i][j].validMoves[x] = temp[x].clone();
                    }
                    parent[i][j][0] = -1;
                }
            }
            parent[start[0]][start[1]][0] = start[0];
            parent[start[0]][start[1]][1] = start[1];
            int[][] temp = validMoves(start[0],start[1]);
            travelCost[start[0]][start[1]] = new Node(start[0], start[1] , 0, temp.length);
            for (int x = 0; x < temp.length; x++) {
                travelCost[start[0]][start[1]].validMoves[x] = temp[x].clone();
            }

            for (int i = 0; i < travelCost.length; i++) {
                for (int j = 0; j < travelCost.length; j++) {
                    weight.add(travelCost[i][j]);
                }
            }
            while (weight.size() > 0)
            {
                Node v = weight.poll();
                int[] vLocation = v.location;
                if (parent[vLocation[0]][vLocation[1]][0] == -1) break;
                else if (Arrays.equals(vLocation, end)) break;
                for (int[] move : v.validMoves)
                {
                    int xCoord = vLocation[0];
                    int yCoord = vLocation[1];
                    Node u = travelCost[xCoord + move[0]][yCoord + move[1]];
                    if (v.weight + grid[xCoord + move[0]][yCoord + move[1]] < u.weight)
                    {
                        weight.remove(u);
                        u.weight = v.weight + 1;
                        weight.add(u);
                        travelCost[xCoord + move[0]][yCoord + move[1]] = u;
                        parent[xCoord + move[0]][yCoord + move[1]][0] = xCoord;
                        parent[xCoord + move[0]][yCoord + move[1]][1] = yCoord;
                    }
                }
            }
            if (parent[end[0]][end[1]][0] != -1) {
                // System.out.println(travelCost[end[0]][end[1]].weight);
                minDistance = Math.min(travelCost[end[0]][end[1]].weight, minDistance);
            }
        }
        System.out.println(minDistance);
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[x].length;
    }

    public static int[][] validMoves(int x, int y) {
        int[][] moves = {{0,1},{0,-1},{1,0},{-1,0}};
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < moves.length; i++) {
            if (inRange(x+moves[i][0], y+ moves[i][1])) {
                char c = grid[x+moves[i][0]][y+moves[i][1]];
                int temp = grid[x][y] - c;
                if (temp >= -1) {
                    list.add(i);
                }
            }
        }
        int[][] answer = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = moves[list.get(i)];
        }
        return answer;
    }
}