import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Driver {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));
        String name = sc.nextLine();
        HashMap<String, Integer> map = new HashMap<>();

        String[] punctuation = {".", ",", "'", "\"", "(", ")", "!", "&", "$", "@", "#", "*", "%", "+", "-", "?", ">", "<"};

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                token = removePunct(token, punctuation);
                if (map.containsKey(token)) {
                    Integer i = map.get(token);
                    map.replace(token, i, i+1);
                } else map.put(token, 1);
            }
        }
        System.out.println(name);
        System.out.println(map.size());
    }
    private static String removePunct(String in, String[] punctuation) {
        for (String s : punctuation) {
            while (in.contains(s))
                in = in.substring(0, in.indexOf(s)) + in.substring(in.indexOf(s)+1);
        }
        return in;
    }
}