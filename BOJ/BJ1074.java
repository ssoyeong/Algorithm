import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// Z

public class BJ1074 {

    static int n;
    static int r, c;
    static int size;
    static int order;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        size = (int)Math.pow(2, n);

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        recursion(r, c, size);
        System.out.println(order);
    }
     
    private static void recursion(int r, int c, int size) {

        if(size == 1) {
            return;
        }
        
        if(r < size/2 && c < size/2) {
            recursion(r, c, size/2);
        }
        else if(r < size/2 && c >= size/2) {
            order += size * size / 4;
            recursion(r, (c - size/2), size/2);
        }
        else if(r >= size/2 && c < size/2) {
            order += 2 * (size * size / 4);
            recursion((r - size/2), c, size/2);
        }
        else {
            order += 3 * (size * size / 4);
            recursion((r - size/2), (c - size/2), size/2);
        }
    }
}
