import java.util.*;

import java.io.*;

public class day7 {
    public static Map<String, Integer> inputs = new HashMap<>();
    public static String strengths = "23456789TJQKA";
    public static String pt2Strengths = "J23456789TQKA";
    public static List<String> highCard = new ArrayList<>();
    public static List<String> onePair = new ArrayList<>();
    public static List<String> twoPair = new ArrayList<>();
    public static List<String> threeKind = new ArrayList<>();
    public static List<String> fullHouse = new ArrayList<>();
    public static List<String> fourKind = new ArrayList<>();
    public static List<String> fiveKind = new ArrayList<>();
    public static List<List<String>> organizedCards = new ArrayList<>();
    public static enum Type {highCard, onePair, twoPair, threeKind, fullHouse, fourKind, fiveKind};
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line);
                inputs.put(st.nextToken(), Integer.parseInt(st.nextToken()));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println(pt1());
        System.out.println(pt2());
    }

    public static class CardComparator implements Comparator<String> {
        //1 for a greater, -1 for b greater, 0 for equal
        public int compare(String a, String b) {
            for (int i = 0; i < 5; i++) {
                char c1 = a.charAt(i);
                char c2 = b.charAt(i);

                if (strengths.indexOf(c1) > strengths.indexOf(c2)) {
                    return 1;
                } else if (strengths.indexOf(c2) > strengths.indexOf(c1)) {
                    return -1;
                }
            }
            return 0;
        }
    }

    public static Type determineType(String str) {
        Map<Character, Integer> count = new HashMap<>();
            for (Character c : str.toCharArray()) {
                count.merge(c, 1, Integer::sum);
            }
            if (count.size() == 5) {
                return Type.highCard;
            } else if (count.size() == 4) {
                return Type.onePair;
            } else if (count.size() == 3) {
                for (Character temp : count.keySet()) {
                    if (count.get(temp) == 3) {
                        return Type.threeKind;
                    }
                }
                return Type.twoPair;
            } else if (count.size() == 2) {
                for (Character temp : count.keySet()) {
                    if (count.get(temp) == 4) {
                        return Type.fourKind;
                    }
                }
                return Type.fullHouse;
            }
        return Type.fiveKind;
    }
    
    public static Type determineType2(String str) {
        Map<Character, Integer> count = new HashMap<>();
            for (Character c : str.toCharArray()) {
                count.merge(c, 1, Integer::sum);
            }
            if (count.size() == 5) {
                return Type.highCard;
            } else if (count.size() == 4) {
                return Type.onePair;
            } else if (count.size() == 3) {
                for (Character temp : count.keySet()) {
                    if (count.get(temp) == 3) {
                        return Type.threeKind;
                    }
                }
                return Type.twoPair;
            } else if (count.size() == 2) {
                for (Character temp : count.keySet()) {
                    if (count.get(temp) == 4) {
                        return Type.fourKind;
                    }
                }
                return Type.fullHouse;
            }
        return Type.fiveKind;
    }

    public static long pt1() {
        long answer = 0;
        for (String str : inputs.keySet()) {
            Type type = determineType(str);
            switch (type) {
                case highCard:
                    highCard.add(str);
                    break;

                case onePair:
                    onePair.add(str);
                    break;

                case twoPair:
                    twoPair.add(str);
                    break;

                case threeKind:
                    threeKind.add(str);
                    break;

                case fourKind:
                    fourKind.add(str);
                    break;

                case fiveKind:
                    fiveKind.add(str);
                    break;

                case fullHouse:
                    fullHouse.add(str);
                    break;
            
                default:
                    break;
            }
        }

        List<String> sorted = new ArrayList<>();
        organizedCards.add(highCard);
        organizedCards.add(onePair);
        organizedCards.add(twoPair);
        organizedCards.add(threeKind);
        organizedCards.add(fullHouse);
        organizedCards.add(fourKind);
        organizedCards.add(fiveKind);
        for (List<String> list : organizedCards) {
            Collections.sort(list, new CardComparator());
            for (String str : list) {
                sorted.add(str);
            }
        }

        for (int i = 0; i < sorted.size(); i++) {
            answer += (i+1)*inputs.get(sorted.get(i));
        }
        return answer;
    }

    public static long pt2() {
        long answer = 0;
        for (List<String> list : organizedCards) {
            list.clear();
        }

        List<Integer> temp = new ArrayList<>();
        for (String str : inputs.keySet()) {
            if (str.contains("J")) {
                
            }
        }

        return answer;
    }
}