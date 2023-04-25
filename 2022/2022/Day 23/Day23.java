import java.io.*;
import java.util.*;

public class Day23 {
    static int[][] northMoves = new int[][]{{-1,0},{-1,-1},{-1,1}};
    static int[][] southMoves = new int[][]{{1,0},{1,1},{1,-1}};
    static int[][] westMoves = new int[][]{{0,-1},{1,-1},{-1,-1}};
    static int[][] eastMoves = new int[][]{{0,1},{-1,1},{1,1}};
    static int[][] adjacent = new int[][]{{-1,0},{-1,-1},{-1,1},{1,0},{1,1},{1,-1},{0,1},{0,-1}};
    static char[][] grid = new char[0][0];
    static List<Elf> elves = new ArrayList<>();
    static LinkedList<String> instructions = new LinkedList<>();
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
        instructions.add("north");
        instructions.add("south");
        instructions.add("west");
        instructions.add("east");
        grid = new char[inputs.size()+1000][inputs.get(0).length()+1000];
        for (char[] c : grid) {
            Arrays.fill(c,'.');
        }
        for (int i = 0; i < inputs.size(); i++) {
            for (int j = 0; j < inputs.get(i).length(); j++) {
                char c = inputs.get(i).charAt(j);
                grid[i+500][j+500] = c;
                if (c == '#') {
                    elves.add(new Elf(i+500,j+500));
                }
            }
        }

        // Part B
        int count = 1;
        while (!simulateRound()) {
            count++;
        }
        System.out.println(count);

        // Part A
        // for (int i = 0; i < 10; i++){
        //     simulateRound();
        // }
        // grid = trimGrid();
        // int count = 0;
        // for (int i = 0; i < grid.length; i++) {
        //     for (int j = 0; j < grid[i].length; j++) {
        //         if (grid[i][j] == '.') {
        //             count++;
        //         }
        //     }
        // }
        // System.out.println(count);
    }

    static boolean simulateRound() {
        Map<int[], Elf> proposedMoves = new HashMap<>();
        List<Elf> needsMoving = new ArrayList<>();
        for (Elf e : elves) {
            for (int[] move : adjacent) {
                if (grid[e.location[0]+move[0]][e.location[1]+move[1]] == '#') {
                    needsMoving.add(e);
                    break;
                }
            }
        }
        if (needsMoving.size() == 0) {
            return true;
        }
        for (Elf e : needsMoving) {
            @SuppressWarnings("unchecked")LinkedList<String> currentOrder = (LinkedList<String>)instructions.clone();
            String instruction = currentOrder.pollFirst();
            boolean foundLocation = false;
            while (instruction != null && !foundLocation) {
                int[] proposedMove = null;
                boolean noElf = true;
                if (instruction.equals("north")) {
                    for (int[] move : northMoves) {
                        if (grid[e.location[0]+move[0]][e.location[1]+move[1]] == '#') {
                            instruction = currentOrder.pollFirst();
                            noElf = false;
                            break;
                        }
                    }
                    if (!noElf) continue;
                    proposedMove = new int[]{e.location[0] - 1, e.location[1]};
                    foundLocation = true;
                } else if (instruction.equals("south")) {
                    for (int[] move : southMoves) {
                        if (grid[e.location[0]+move[0]][e.location[1]+move[1]] == '#') {
                            instruction = currentOrder.pollFirst();
                            noElf = false;
                            break;
                        }
                    }
                    if (!noElf) continue;
                    proposedMove = new int[]{e.location[0] + 1, e.location[1]};
                    foundLocation = true;
                } else if (instruction.equals("west")) {
                    for (int[] move : westMoves) {
                        if (grid[e.location[0]+move[0]][e.location[1]+move[1]] == '#') {
                            instruction = currentOrder.pollFirst();
                            noElf = false;
                            break;
                        }
                    }
                    if (!noElf) continue;
                    proposedMove = new int[]{e.location[0], e.location[1] - 1};
                    foundLocation = true;
                } else if (instruction.equals("east")) {
                    for (int[] move : eastMoves) {
                        if (grid[e.location[0]+move[0]][e.location[1]+move[1]] == '#') {
                            instruction = currentOrder.pollFirst();
                            noElf = false;
                            break;
                        }
                    }
                    if (!noElf) continue;
                    proposedMove = new int[]{e.location[0], e.location[1] + 1};
                    foundLocation = true;
                }
                if (!isInList(proposedMoves.keySet(), proposedMove)) {
                    proposedMoves.put(proposedMove,e);
                    e.proposedLocation[0] = proposedMove[0];
                    e.proposedLocation[1] = proposedMove[1];
                } else {
                    int[] key = new int[0];
                    for (int[] k : proposedMoves.keySet()) {
                        if (Arrays.equals(k, proposedMove)) {
                            key = k;
                            break;
                        }
                    }
                    Elf tempElf = proposedMoves.get(key);
                    tempElf.proposedLocation[0] = tempElf.location[0];
                    tempElf.proposedLocation[1] = tempElf.location[1];
                }
            }
        }
        for (Elf e : needsMoving) {
            grid[e.location[0]][e.location[1]] = '.';
            grid[e.proposedLocation[0]][e.proposedLocation[1]] = '#';
            e.location[0] = e.proposedLocation[0];
            e.location[1] = e.proposedLocation[1];
        }
        instructions.add(instructions.pollFirst());
        return false;
    }

    static boolean isInList(Set<int[]> list, int[] candidate) {
        return list.stream().anyMatch(a -> Arrays.equals(a, candidate));
    }

    static void showGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    static char[][] trimGrid() {
        int minRow = Integer.MAX_VALUE;
        int maxRow = Integer.MIN_VALUE;
        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;
        for (int i = 0; i < grid.length; i++) {
          for (int j = 0; j < grid[0].length; j++) {
            if (grid[i][j] == '#') {
              minRow = Math.min(minRow, i);
              maxRow = Math.max(maxRow, i);
              minCol = Math.min(minCol, j);
              maxCol = Math.max(maxCol, j);
            }
          }
        }
        int numRows = maxRow - minRow + 1;
        int numCols = maxCol - minCol + 1;
        char[][] trimmedGrid = new char[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
          for (int j = 0; j < numCols; j++) {
            trimmedGrid[i][j] = grid[minRow + i][minCol + j];
          }
        }
        return trimmedGrid;
      }
}