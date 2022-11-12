import java.io.BufferedReader;
import java.io.InputStreamReader;

// 회문1

class SWEA1215 {

    static final int SIZE = 8;
    static int n;
    static char[][] arr;
    static int cnt;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= 10; tc++) {
            n = Integer.parseInt(br.readLine());
            arr = new char[SIZE][SIZE];
            cnt = 0;

            for (int i = 0; i < SIZE; i++) {
                String line = br.readLine();
                for (int j = 0; j < SIZE; j++) {
                    arr[i][j] = line.charAt(j);
                }
            }

            solution();
            sb.append(String.format("#%d %d\n", tc, cnt));
        }

        System.out.println(sb.toString());
    }

    private static void solution() {

        // rows
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                chkPalindrome(i, j);
            }
        }
    }

    private static void chkPalindrome(int x, int y) {

        StringBuilder sb_row = new StringBuilder();
        StringBuilder sb_col = new StringBuilder();

        for (int i = 0; i < n; i++) {

            if (y <= SIZE - n)
                sb_row.append(arr[x][y + i]);
            if (x <= SIZE - n)
                sb_col.append(arr[x + i][y]);
        }

        if (y <= SIZE - n) {
            String word_row = sb_row.toString();
            sb_row.setLength(0);
            String word_row_rvs = sb_row.append(word_row).reverse().toString();

            if (word_row.equals(word_row_rvs)) cnt++;
        }

        if (x <= SIZE - n) {
            String word_col = sb_col.toString();
            sb_col.setLength(0);
            String word_col_rvs = sb_col.append(word_col).reverse().toString();

            if (word_col.equals(word_col_rvs)) cnt++;
        }
    }

}