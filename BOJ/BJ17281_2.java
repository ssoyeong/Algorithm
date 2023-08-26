import java.io.*;
import java.util.*;

// ⚾

public class BJ17281_2 {
    
    static final int SIZE = 9;
    static int n;
    static int ans;
    static int[][] result;
    static int[] order = new int[SIZE];
    static boolean[] selected = new boolean[SIZE];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        result = new int[n][SIZE];

        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < SIZE; j++) {
                result[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        selected[0] = true;
        order[3] = 0;
        makeOrder(0);
        System.out.println(ans);
    }

    private static void makeOrder(int depth) {

        if(depth == SIZE) {
            playGame();
            return;
        }
        if(depth == 3) makeOrder(depth + 1);
        else {
            for(int i = 0; i < SIZE; i++) {
                if(!selected[i]) {
                    selected[i] = true;
                    order[depth] = i;
                    makeOrder(depth + 1);
                    selected[i] = false;
                }
            }
        }
    }

    private static void playGame() {

        int score = 0;
        int playerIdx = 0;
        boolean[] base = new boolean[4];

        for(int i = 0; i < n; i++) {

            int out = 0;
            Arrays.fill(base, false);

            while(true) {
                int res = result[i][order[playerIdx]];
                playerIdx = (playerIdx + 1) % 9;

                if(res == 0) {
                    if(++out == 3) {
                        break;
                    }
                }
                else {
                    base[0] = true;
                    for(int idx = 3; idx >= 0; idx--) {
                        int newIdx = idx + res;
                        if(newIdx > 3) {
                            if(base[idx]) {
                                score++;
                                base[idx] = false;
                            }
                        }
                        else {
                            base[newIdx] = base[idx];
                            base[idx] = false;
                        }
                    }
                }
            }
        }

        ans = Integer.max(ans, score);
    }
}
