import java.io.*;
import java.util.*;

// 톱니바퀴 (2)

public class BJ15662 {
    
    static int t;
    static boolean[][] wheels;
    static int[] dirArr;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        wheels = new boolean[t][8];
        dirArr = new int[t];

        for(int i = 0; i < t; i++) {
            String line = br.readLine();
            for(int j = 0; j < 8; j++) {
                if(line.charAt(j) == '1') wheels[i][j] = true;
            }
        }

        StringTokenizer st;
        int k = Integer.parseInt(br.readLine());
        while(k-- > 0) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            rotate(num, dir);
        }

        int cnt = 0;
        for(int i = 0; i < t; i++) {
            if(wheels[i][0]) cnt++;
        }
        System.out.println(cnt);
    }

    private static void rotate(int num, int dir) {

        Arrays.fill(dirArr, 0);
        dirArr[num] = dir;

        // set direction of left side
        int idx = num;
        while(idx-- > 0) {
            if(wheels[idx][2] == wheels[idx + 1][6]) break;
            dirArr[idx] = -dirArr[idx + 1];
        }

        // set direction of right side
        idx = num;
        while(idx++ < t - 1) {
            if(wheels[idx][6] == wheels[idx - 1][2]) break;
            dirArr[idx] = -dirArr[idx - 1];
        }

        // rotate wheels
        for(int i = 0; i < t; i++) {
            if(dirArr[i] == 1) clockwise(i);
            else if(dirArr[i] == -1) counterclockwise(i);
        }
    }

    private static void clockwise(int n) {
        boolean tmp = wheels[n][7];
        for(int i = 7; i >= 1; i--) {
            wheels[n][i] = wheels[n][i - 1];
        }
        wheels[n][0] = tmp;
    }

    private static void counterclockwise(int n) {
        boolean tmp = wheels[n][0];
        for(int i = 0; i <= 6; i++) {
            wheels[n][i] = wheels[n][i + 1];
        }
        wheels[n][7] = tmp;
    }
}
