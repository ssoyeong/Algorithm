import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 내려가기

public class BJ2096 {

    static int n;
    static int[][] max = new int[2][3];
    static int[][] min = new int[2][3];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(i == 0) {
                max[i%2][0] = a;
                max[i%2][1] = b;
                max[i%2][2] = c;
                min[i%2][0] = a;
                min[i%2][1] = b;
                min[i%2][2] = c;
            }
            else {

                max[i%2][0] = a + Integer.max(max[1-i%2][0], max[1-i%2][1]);
                max[i%2][1] = b + Integer.max(max[1-i%2][0], Integer.max(max[1-i%2][1], max[1-i%2][2]));
                max[i%2][2] = c + Integer.max(max[1-i%2][1], max[1-i%2][2]);

                min[i%2][0] = a + Integer.min(min[1-i%2][0], min[1-i%2][1]);
                min[i%2][1] = b + Integer.min(min[1-i%2][0], Integer.min(min[1-i%2][1], min[1-i%2][2]));
                min[i%2][2] = c + Integer.min(min[1-i%2][1], min[1-i%2][2]);     
            }           
        }

        int ansMax = Integer.max(max[1-n%2][0], Integer.max(max[1-n%2][1], max[1-n%2][2]));
        int ansMin = Integer.min(min[1-n%2][0], Integer.min(min[1-n%2][1], min[1-n%2][2]));
        System.out.println(ansMax + " " + ansMin);
    }
    
}
