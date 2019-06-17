import java.io.File;
import java.io.IOException;
import java.util.*;

public class Driver {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));
        HashMap<String, Integer> map = new HashMap<>();

        String[] punctuation = {".", ",", "'", "\"", "(", ")", "!", "&", "$", "@", "#", "*", "%", "+", "-", "?", ">", "<"};

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                String token = st.nextToken().toLowerCase();
                token = removePunct(token, punctuation);
                if (map.containsKey(token)) {
                    Integer i = map.get(token);
                    map.replace(token, i, i+1);
                } else map.put(token, 1);
            }
        }

        int numOfDistinctWords = map.size();

        int[] top = new int[7];
        for (int i = 0; i < top.length; i++) {
            Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
            Map.Entry best = null;
            while (iterator.hasNext()) {
                Map.Entry next = iterator.next();
                if ((Integer)next.getValue() > top[i]) {
                    top[i] = (Integer) next.getValue();
                    best = next;
                }
            }
            map.remove(best.getKey(), best.getValue());
        }
        int sum = 0;
        for (int i : top)
            sum+=i;
        double avg = sum/(top.length + 0.0);

        double wvc = numOfDistinctWords/avg;
        System.out.println(wvc);
    }
    private static String removePunct(String in, String[] punctuation) {
        for (String s : punctuation) {
            while (in.contains(s))
                in = in.substring(0, in.indexOf(s)) + in.substring(in.indexOf(s)+1);
        }
        return in;
    }
}