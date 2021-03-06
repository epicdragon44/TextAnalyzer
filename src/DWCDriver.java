import java.io.File;
import java.io.IOException;
import java.util.*;

public class DWCDriver {
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

        int dwc = map.size();
        System.out.println(dwc);
    }
    private static String removePunct(String in, String[] punctuation) {
        for (String s : punctuation) {
            while (in.contains(s))
                in = in.substring(0, in.indexOf(s)) + in.substring(in.indexOf(s)+1);
        }
        return in;
    }
}