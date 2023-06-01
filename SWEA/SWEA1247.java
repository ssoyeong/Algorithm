import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// [S/W 문제해결 응용] 3일차 - 최적 경로

public class SWEA1247 {

    private static class Point {
        int x;
        int y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int tc;
    static int n;
    static int ans;
    static Point office, home;
    static Point[] customers;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int t = 1; t <= tc; t++) {

            ans = Integer.MAX_VALUE;
            n = Integer.parseInt(br.readLine());
            customers = new Point[n];
            visited = new boolean[n];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i = 0; i < n + 2; i++) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                if(i == 0) {
                    office = new Point(x, y);
                }
                else if(i == 1) {
                    home = new Point(x, y);
                }
                else {
                    customers[i-2] = new Point(x, y);
                }
            }

            backTracking(0, office, 0);
            sb.append("#" + t + " " + ans + "\n");
        }

        System.out.println(sb.toString());
    }

    private static void backTracking(int depth, Point from, int total) {

        if(depth == n) {
            int dist = Math.abs(from.x - home.x) + Math.abs(from.y - home.y);
            ans = Integer.min(ans, dist + total);
            return;
        }

        for(int i = 0; i < n; i++) {
            if(visited[i]) continue;

            Point target = customers[i];
            int dist = Math.abs(from.x - target.x) + Math.abs(from.y - target.y);
            if(total + dist > ans) continue;

            visited[i] = true;
            backTracking(depth + 1, target, total + dist);
            visited[i] = false;
        }
    }
}
