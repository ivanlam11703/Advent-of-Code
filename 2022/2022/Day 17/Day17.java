import java.io.*;
import java.util.*;

public class Day17 {
    static LinkedList<char[]> chamber = new LinkedList<>();
    static LinkedList<String> rocks = new LinkedList<>();
    static String instruction = "";
    static int highestPoint = 0;
    static int index = 0;
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            instruction = br.readLine();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        rocks.add("rock1");
        rocks.add("rock2");
        rocks.add("rock3");
        rocks.add("rock4");
        rocks.add("rock5");

        initalizeChamber();
        for (int i = 0; i < 2013; i++) {
            dropRock();
            // showChamber();
            findHighestPoint();
        }
        // showChamber();
        System.out.println(highestPoint);
    }

    static void dropRock() {
        char[] temp = new char[9];
        Arrays.fill(temp, '.');
        temp[0] = temp [8] = '|';
        int x = 0;
        while (Arrays.equals(chamber.get(x),temp)) {
            chamber.remove(x);
        }
        // showChamber();
        spawnEmptySpace();
        String rockNum = rocks.pollFirst();
        rocks.add(rockNum);
        if (rockNum.equals("rock1")) {
            char[] rock = new char[9];
            Arrays.fill(rock,'.');
            rock[0] = rock[8] = '|';
            int rockLeft = 3;
            int rockRight = 6;
            int rockDepth = 0;
            rock[3] = rock[4] = rock[5] = rock[6] = '@';
            chamber.addFirst(rock);
            char[] nextLevel = chamber.get(rockDepth+1).clone();
            // showChamber();
            while (true) {
                index %= instruction.length();
                // System.out.println(instruction.charAt(index));
                if (instruction.charAt(index) == '>') {
                    if (chamber.get(rockDepth)[7] != '@' && chamber.get(rockDepth)[rockRight+1] != '#') {
                        chamber.get(rockDepth)[rockLeft] = '.';
                        rockLeft++;
                        rockRight++;
                        chamber.get(rockDepth)[rockRight] = '@';
                    } 
                } else {
                    if (chamber.get(rockDepth)[1] != '@' && chamber.get(rockDepth)[rockLeft-1] != '#') {
                        chamber.get(rockDepth)[rockRight] = '.';
                        rockRight--;
                        rockLeft--;
                        chamber.get(rockDepth)[rockLeft] = '@';
                    }
                }
                if (nextLevel[rockLeft] == '-' || nextLevel[rockLeft] == '#' || 
                nextLevel[rockLeft+1] == '-' || nextLevel[rockLeft+1] == '#' ||
                nextLevel[rockLeft+2] == '-' || nextLevel[rockLeft+2] == '#' ||
                nextLevel[rockRight] == '-' || nextLevel[rockRight] == '#') {
                    break;
                }
                for (int i = rockLeft; i <= rockRight; i++) {
                    chamber.get(rockDepth)[i] = '.';
                    chamber.get(rockDepth+1)[i] = '@';
                }
                rockDepth++;
                index++;
                nextLevel = chamber.get(rockDepth+1).clone();
                // showChamber();
            }
            for (int i = rockLeft; i <= rockRight; i++) {
                chamber.get(rockDepth)[i] = '#';
            }
            index++;
        } else if (rockNum.equals("rock2")) {
            int rockLeft = 3;
            int rockRight = 5;
            int rockBottom = 4;
            int rockDepth = 2;
            char[] rock = new char[9];
            Arrays.fill(rock, '.');
            rock[0] = rock[8] = '|';
            rock[4] = '@';
            chamber.addFirst(rock);
            rock = new char[9];
            Arrays.fill(rock,'.');
            rock[0] = rock[8] = '|';
            rock[3] = rock[4] = rock[5] = '@';
            chamber.addFirst(rock);
            rock = new char[9];
            Arrays.fill(rock,'.');
            rock[0] = rock[8] = '|';
            rock[4] = '@';
            chamber.addFirst(rock);
            char[] middleLevel = chamber.get(rockDepth).clone();
            char[] bottomLevel = chamber.get(rockDepth+1).clone();
            // showChamber();
            while (true) {
                index %= instruction.length();
                // System.out.println(instruction.charAt(index));
                if (instruction.charAt(index) == '>') {
                    if (chamber.get(rockDepth-1)[7] != '@' && chamber.get(rockDepth-1)[rockRight+1] != '#' && 
                        chamber.get(rockDepth)[rockBottom+1] != '#' && chamber.get(rockDepth-2)[rockBottom+1] != '#') {
                        chamber.get(rockDepth-1)[rockLeft] = '.';
                        chamber.get(rockDepth)[rockBottom] = '.';
                        chamber.get(rockDepth-2)[rockBottom] = '.';
                        rockLeft++;
                        rockRight++;
                        rockBottom++;
                        chamber.get(rockDepth)[rockBottom] = '@';
                        chamber.get(rockDepth-1)[rockRight] = '@';
                        chamber.get(rockDepth-2)[rockBottom] = '@';
                    } 
                } else {
                    if (chamber.get(rockDepth-1)[1] != '@' && chamber.get(rockDepth-1)[rockLeft-1] != '#' && 
                    chamber.get(rockDepth)[rockBottom-1] != '#' && chamber.get(rockDepth-2)[rockBottom-1] != '#') {
                        chamber.get(rockDepth-1)[rockRight] = '.';
                        chamber.get(rockDepth)[rockBottom] = '.';
                        chamber.get(rockDepth-2)[rockBottom] = '.';
                        rockRight--;
                        rockLeft--;
                        rockBottom--;
                        chamber.get(rockDepth)[rockBottom] = '@';
                        chamber.get(rockDepth-1)[rockLeft] = '@';
                        chamber.get(rockDepth-2)[rockBottom] = '@';
                    }
                }
                if (bottomLevel[rockBottom] == '-' || bottomLevel[rockBottom] == '#' ||
                    middleLevel[rockLeft] == '#' || middleLevel[rockRight] == '#') {
                    break;
                }
                chamber.get(rockDepth)[rockBottom] = '.';
                chamber.get(rockDepth+1)[rockBottom] = '@';
                for (int i = rockLeft; i <= rockRight; i++) {
                    chamber.get(rockDepth-1)[i] = '.';
                    chamber.get(rockDepth)[i]= '@';
                }
                chamber.get(rockDepth-2)[rockBottom] = '.';
                chamber.get(rockDepth-1)[rockBottom] = '@';
                rockDepth++;
                index++;
                middleLevel = chamber.get(rockDepth).clone();
                bottomLevel = chamber.get(rockDepth+1).clone();
                // showChamber();
            }
            chamber.get(rockDepth)[rockBottom] = '#';
            chamber.get(rockDepth-2)[rockBottom] = '#';
            for (int i = rockLeft; i <= rockRight; i++) {
                chamber.get(rockDepth-1)[i] = '#';
            }
            index++;
        } else if (rockNum.equals("rock3")) {
            char[] rock = new char[9];
            Arrays.fill(rock,'.');
            rock[0] = rock[8] = '|';
            int rockLeft = 3;
            int rockRight = 5;
            int rockDepth = 2;
            rock[3] = rock[4] = rock[5] = '@';
            chamber.addFirst(rock);
            for (int i = 0; i < 2; i++) {
                rock = new char[9];
                Arrays.fill(rock,'.');
                rock[0] = rock[8] = '|';
                rock[5] = '@';
                chamber.addFirst(rock);
            }
            
            // rock = new char[9];
            // Arrays.fill(rock,'.');
            // rock[0] = rock[8] = '|';
            // rock[5] = '@';
            // chamber.addFirst(rock);
            char[] bottomLevel = chamber.get(rockDepth+1).clone();
            // showChamber();
            while (true) {
                index %= instruction.length();
                // System.out.println(instruction.charAt(index));
                if (instruction.charAt(index) == '>') {
                    if (chamber.get(rockDepth)[7] != '@' && chamber.get(rockDepth)[rockRight+1] != '#' && 
                    chamber.get(rockDepth-1)[rockRight+1] != '#' && chamber.get(rockDepth-2)[rockRight+1] != '#') {
                        chamber.get(rockDepth)[rockLeft] = '.';
                        chamber.get(rockDepth-1)[rockRight] = '.';
                        chamber.get(rockDepth-2)[rockRight] = '.';
                        rockLeft++;
                        rockRight++;
                        chamber.get(rockDepth-1)[rockRight] = '@';
                        chamber.get(rockDepth-2)[rockRight] = '@';
                        chamber.get(rockDepth)[rockRight] = '@';
                    } 
                } else {
                    if (chamber.get(rockDepth)[1] != '@' && chamber.get(rockDepth)[rockLeft-1] != '#' && 
                    chamber.get(rockDepth-1)[rockRight-1] != '#' && chamber.get(rockDepth-2)[rockRight+1] != '#') {
                        chamber.get(rockDepth)[rockRight] = '.';
                        chamber.get(rockDepth-1)[rockRight] = '.';
                        chamber.get(rockDepth-2)[rockRight] = '.';
                        rockRight--;
                        rockLeft--;
                        chamber.get(rockDepth-1)[rockRight] = '@';
                        chamber.get(rockDepth-2)[rockRight] = '@';
                        chamber.get(rockDepth)[rockLeft] = '@';
                    }
                }
                if (bottomLevel[rockLeft] == '-' || bottomLevel[rockLeft] == '#' || 
                bottomLevel[rockLeft+1] == '-' || bottomLevel[rockLeft+1] == '#' ||
                bottomLevel[rockRight] == '-' || bottomLevel[rockRight] == '#') {
                    break;
                }
                chamber.get(rockDepth-1)[rockRight] = '.';
                chamber.get(rockDepth-2)[rockRight] = '.';
                for (int i = rockLeft; i <= rockRight; i++) {
                    chamber.get(rockDepth)[i] = '.';
                    chamber.get(rockDepth+1)[i] = '@';
                }
                chamber.get(rockDepth)[rockRight] = '@';
                chamber.get(rockDepth-1)[rockRight] = '@';
                rockDepth++;
                index++;
                bottomLevel = chamber.get(rockDepth+1).clone();
                // showChamber();
            }
            for (int i = rockLeft; i <= rockRight; i++) {
                chamber.get(rockDepth)[i] = '#';
            }
            chamber.get(rockDepth-1)[rockRight] = '#';
            chamber.get(rockDepth-2)[rockRight] = '#';
            index++;
        } else if (rockNum.equals("rock4")) {
            for (int i = 0; i < 4; i++) {
                char[] rock = new char[9];
                Arrays.fill(rock,'.');
                rock[0] = rock[8] = '|';
                rock[3] = '@';
                chamber.addFirst(rock);
            }
            int rockPos = 3;
            int rockDepth = 3;
            char[] bottomLevel = chamber.get(rockDepth+1).clone();
            // showChamber();
            while (true) {
                index %= instruction.length();
                // System.out.println(instruction.charAt(index));
                if (instruction.charAt(index) == '>') {
                    if (chamber.get(rockDepth)[7] != '@' && chamber.get(rockDepth)[rockPos+1] != '#' &&
                        chamber.get(rockDepth-1)[rockPos+1] != '#' && chamber.get(rockDepth-2)[rockPos+1] != '#' &&
                        chamber.get(rockDepth-3)[rockPos+1] != '#') {
                        for (int i = 0; i < 4; i++) {
                            chamber.get(rockDepth-i)[rockPos] = '.';
                        }
                        rockPos++;
                        for (int i = 0; i < 4; i++) {
                            chamber.get(rockDepth-i)[rockPos] = '@';
                        }
                    } 
                } else {
                    if (chamber.get(rockDepth)[1] != '@' && chamber.get(rockDepth)[rockPos-1] != '#' &&
                        chamber.get(rockDepth-1)[rockPos-1] != '#' && chamber.get(rockDepth-2)[rockPos-1] != '#' &&
                        chamber.get(rockDepth-3)[rockPos-1] != '#') {
                            for (int i = 0; i < 4; i++) {
                                chamber.get(rockDepth-i)[rockPos] = '.';
                            }
                            rockPos--;
                            for (int i = 0; i < 4; i++) {
                                chamber.get(rockDepth-i)[rockPos] = '@';
                            }
                    }
                }
                if (bottomLevel[rockPos] == '-' || bottomLevel[rockPos] == '#') {
                    break;
                }
                chamber.get(rockDepth-3)[rockPos] = '.';
                chamber.get(rockDepth+1)[rockPos]= '@';
                // for (int i = rockLeft; i <= rockRight; i++) {
                //     chamber.get(rockDepth)[i] = '.';
                //     chamber.get(rockDepth+1)[i] = '@';
                // }
                rockDepth++;
                index++;
                bottomLevel = chamber.get(rockDepth+1).clone();
                // showChamber();
            }
            // for (int i = rockLeft; i <= rockRight; i++) {
            //     chamber.get(rockDepth)[i] = '#';
            // }
            for (int i = 0; i < 4; i++) {
                chamber.get(rockDepth-i)[rockPos] = '#';
            }
            index++;
        } else if (rockNum.equals("rock5")) {
            for (int i = 0; i < 2; i++) {
                char[] rock = new char[9];
                Arrays.fill(rock,'.');
                rock[0] = rock[8] = '|';
                rock[3] = rock[4] = '@';
                chamber.addFirst(rock);
            }
            int rockLeft = 3;
            int rockRight = 4;
            int rockDepth = 1;
            char[] bottomLevel = chamber.get(rockDepth+1).clone();
            // showChamber();
            while (true) {
                index %= instruction.length();
                // System.out.println(instruction.charAt(index));
                if (instruction.charAt(index) == '>') {
                    if (chamber.get(rockDepth)[7] != '@' && chamber.get(rockDepth)[rockRight+1] != '#' && 
                        chamber.get(rockDepth-1)[rockRight+1] != '#') {
                        chamber.get(rockDepth-1)[rockLeft] = '.';
                        chamber.get(rockDepth)[rockLeft] = '.';
                        rockLeft++;
                        rockRight++;
                        chamber.get(rockDepth-1)[rockRight] = '@';
                        chamber.get(rockDepth)[rockRight] = '@';
                    } 
                } else {
                    if (chamber.get(rockDepth)[1] != '@' && chamber.get(rockDepth)[rockLeft-1] != '#' &&
                        chamber.get(rockDepth-1)[rockLeft-1] != '#') {
                        chamber.get(rockDepth)[rockRight] = '.';
                        chamber.get(rockDepth-1)[rockRight] = '.';
                        rockRight--;
                        rockLeft--;
                        chamber.get(rockDepth-1)[rockLeft] = '@';
                        chamber.get(rockDepth)[rockLeft] = '@';
                    }
                }
                if (bottomLevel[rockLeft] == '-' || bottomLevel[rockLeft] == '#' || 
                    bottomLevel[rockRight] == '-' || bottomLevel[rockRight] == '#') {
                    break;
                }
                for (int i = rockLeft; i <= rockRight; i++) {
                    chamber.get(rockDepth-1)[i] = '.';
                    chamber.get(rockDepth+1)[i] = '@';
                }
                rockDepth++;
                index++;
                bottomLevel = chamber.get(rockDepth+1).clone();
                // showChamber();
            }
            for (int i = rockLeft; i <= rockRight; i++) {
                chamber.get(rockDepth-1)[i] = '#';
                chamber.get(rockDepth)[i] = '#';
            }
            index++;
        }
    }

    static void findHighestPoint() {
        for (int i = 0; i < chamber.size(); i++) {
            for (int j = 0; j < chamber.get(i).length; j++) {
                if (chamber.get(i)[j] == '#') {
                    highestPoint = Math.max(highestPoint,chamber.size()-i-1);
                    return;
                }
            }
        }
    }

    static void initalizeChamber() {
        char[] floor = new char[9];
        Arrays.fill(floor, '-');
        floor[0] = floor[8] = '+';
        chamber.add(floor);
    }

    static void spawnEmptySpace() {
        for (int i = 0; i < 3; i++) {
            char[] emptySpace = new char[9];
            Arrays.fill(emptySpace,'.');
            emptySpace[0] = emptySpace[8] = '|';
            chamber.addFirst(emptySpace);
        }
    }

    static void showChamber() {
        for (char[] c : chamber) {
            for (int i = 0; i < c.length; i++) {
                System.out.print(c[i]);
            }
            System.out.println();
        }
        System.out.println();
    }
}