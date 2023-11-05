import java.io.*;
import java.util.*;

public class InputGenerator {
    private static InputReader in;
    private static PrintWriter out;

    public static int[] inputGenerator(int n, String type) {
        return inputGenerator(n, type, n);
    }

    public static int[] inputGenerator(int n, String type, int maxValue) {
        int[] arr = new int[n];

        if (type.equals("sorted")) {
            for (int i = 0; i < n; i++) {
                arr[i] = i + 1;
            }

        } else if (type.equals("random")) {
            Random random = new Random();
            for (int i = 0; i < n; i++) {
                arr[i] = random.nextInt(maxValue);
            }

        } else if (type.equals("reversed")) {
            for (int i = 0; i < n; i++) {
                arr[i] = n - i;
            }

        } else {
            return null;
        }

        return arr;
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        int n = in.nextInt();
        String type = in.next();

        // Print generated result
        out.println(Arrays.toString(inputGenerator(n, type)));

        out.close();
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
        
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
        
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
        
        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
