import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

// 벽 부수고 이동하기 4

public class BJ16946 {
    
    private static class Node {

        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m;
    static boolean[][] map;
    static boolean[][] visited;
    static int[][] group;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static HashMap<Integer, Integer> groupInfo = new HashMap<>();
    static Queue<Node> queue = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new boolean[n][m];
        visited = new boolean[n][m];
        group = new int[n][m];

        for(int i = 0; i < n; i++) {
            String line = br.readLine();
            for(int j = 0; j < m; j++) {
                if(line.charAt(j) == '1') map[i][j] = true;
            }
        }

        solution();
        System.out.println(sb.toString());
    }

    private static void solution() {

        int groupNum = 1;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!map[i][j] && group[i][j] == 0) {
                    int cnt = numOfAdj(i, j, groupNum);
                    groupInfo.put(groupNum++, cnt);
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {

                int result = 0;
                if(map[i][j]) {
                    result = numOfPossible(i, j) % 10;
                }
                sb.append(result);
            }
            sb.append("\n");
        }
    }

    private static int numOfAdj(int r, int c, int groupNum) {

        int cnt = 1;
        queue.add(new Node(r, c));
        group[r][c] = groupNum;

        while(!queue.isEmpty()) {

            Node poll = queue.poll();

            for(int i = 0; i < 4; i++) {
       
                int xx = poll.x + dx[i];
                int yy = poll.y + dy[i];
                if(xx < 0 || xx >= n || yy < 0 || yy >= m) continue;

                if(!map[xx][yy] && group[xx][yy] == 0) {
                    queue.add(new Node(xx, yy));
                    group[xx][yy] = groupNum;
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private static int numOfPossible(int r, int c) {

        int num = 1;
        HashSet<Integer> set = new HashSet<>();

        for(int i = 0; i < 4; i++) {

            int xx = r + dx[i];
            int yy = c + dy[i];

            if(xx < 0 || xx >= n || yy < 0 || yy >= m || group[xx][yy] == 0) continue;
            if(set.contains(group[xx][yy])) continue;
            set.add(group[xx][yy]);
        }

        for(Integer group : set) {
            num += groupInfo.get(group);
        }

        return num;
    }

}
