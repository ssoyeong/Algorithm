import java.io.*;
import java.util.*;

// [모의 SW 역량테스트] 미생물 격리

public class SWEA2382 {
    
    static class Group implements Comparable<Group> {
        int id, r, c, num, dir;

        Group(int r, int c) {
            this.r = r;
            this.c = c;
        }

        Group(int id, int r, int c, int num, int dir) {
            this.id = id;
            this.r = r;
            this.c = c;
            this.num = num;
            this.dir = dir;
        }

        @Override
        public boolean equals(Object o) {
            return (this.r == ((Group)o).r && this.c == ((Group)o).c);
        }

        @Override
        public int compareTo(Group o) {
            return o.num - this.num;
        }
    }
    static int tc;
    static int n, m, k;
    static int[][] board;
    static ArrayList<Group> groups = new ArrayList<>();
    static PriorityQueue<Group> queue = new PriorityQueue<>();
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};
     
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int t = 1; t <= tc; t++) {
            groups.clear();

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            
            board = new int[n][n];
            for(int id = 1; id <= k; id++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                
                board[r][c] = id;
                groups.add(new Group(id, r, c, num, dir));
            }
            
            sb.append("#" + t + " " + solution() + "\n");
        }

        System.out.println(sb.toString());
    }

    private static int solution() {

        for(int time = 0; time < m; time++) {

            queue.clear();      // 이동한 군집을 담아두는 큐
            for(int i = 0; i < groups.size(); i++) {

                Group target = groups.get(i);
                int xx = target.r + dx[target.dir];
                int yy = target.c + dy[target.dir];

                if(xx == 0 || xx == n - 1 || yy == 0 || yy == n - 1) {
                    if(target.num == 1) continue;
                    target.num /= 2;
                    target.dir = target.dir % 2 == 0 ? target.dir - 1 : target.dir + 1;
                }

                target.r = xx;
                target.c = yy;
                queue.add(target);
            }

            groups.clear();
            int[][] tempBoard = new int[n][n];

            while(!queue.isEmpty()) {

                Group poll = queue.poll();
                if(tempBoard[poll.r][poll.c] == 0) {
                    tempBoard[poll.r][poll.c] = poll.id;
                    groups.add(poll);
                }
                else {
                    int idx = groups.indexOf(new Group(poll.r, poll.c));
                    groups.get(idx).num += poll.num;
                }
            }

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    board[i][j] = tempBoard[i][j];
                }
            }
        }

        int total = 0;
        for(Group group : groups) {
            total += group.num;
        }
        return total;
    }
}
