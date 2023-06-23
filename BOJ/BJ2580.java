import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

// 스도쿠

public class BJ2580 {

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[][] board = new int[9][9];
    static int[][] temp = new int[9][9];
    static ArrayList<Point> zeros = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 0) zeros.add(new Point(i, j));
            }
        }

        backTracking(0);
    }

    private static void backTracking(int depth) {

        if(depth == zeros.size()) {
            printAnswer();
            System.exit(0);
        }

        Point target = zeros.get(depth);

         // 후보가 될 수 있는 숫자들 구하기
        HashSet<Integer> candidates = new HashSet<>();
        candidates.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        candidates = calculateCandidates(candidates, target.x, target.y);

        for(int candidate : candidates) {
            board[target.x][target.y] = candidate;
            backTracking(depth + 1);
            board[target.x][target.y] = 0;
        }
    }
    
    private static HashSet<Integer> calculateCandidates(HashSet<Integer> candidates, int r, int c) {

        for(int i = 0; i < 9; i++) {
            // 세로줄에 포함된 숫자 제거
            if(candidates.contains(board[r][i])) {
                candidates.remove(board[r][i]);
            }
            // 가로줄에 포함된 숫자 제거
            if(candidates.contains(board[i][c])) {
                candidates.remove(board[i][c]);
            }
        }

        // 3x3 영역에 포함된 숫자 제거
        int startX = r / 3 * 3;
        int startY = c / 3 * 3;
        for(int i = startX; i < startX + 3; i++) {
            for(int j = startY; j < startY + 3; j++) {
                if(candidates.contains(board[i][j])) {
                    candidates.remove(board[i][j]);
                }
            }
        }

        return candidates;
    }

    private static void printAnswer() {

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                sb.append(board[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
