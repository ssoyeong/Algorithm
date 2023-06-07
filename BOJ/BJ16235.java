import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// 나무 재테크

public class BJ16235 {

    static private class Tree {
        int x;
        int y;
        int age;

        Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }
    }
    static int n, m, k;
    static int[][] map;
    static int[][] nutrient;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    static Deque<Tree> trees = new ArrayDeque<>();
    static Queue<Tree> newTrees = new LinkedList<>();
    static Queue<Tree> deadTrees = new LinkedList<>();
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n+1][n+1];
        nutrient = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) {
            Arrays.fill(map[i], 5);
        }

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                nutrient[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            trees.add(new Tree(x, y, z));
        }

        int ans = solution();
        System.out.println(ans);
    }

    private static int solution() {

        for(int year = 0; year < k; year++) {

            spring();
            summer();
            autumn();
            winter();
        }

        return trees.size();
    }

    private static void spring() {

        while(!trees.isEmpty()) {

            Tree poll = trees.poll();

            if(map[poll.x][poll.y] >= poll.age) {
                map[poll.x][poll.y] -= poll.age;
                newTrees.add(new Tree(poll.x, poll.y, poll.age + 1));
            }
            else {
                deadTrees.add(poll);
            }
        }        
    }

    private static void summer() {

        while(!deadTrees.isEmpty()) {

            Tree poll = deadTrees.poll();
            map[poll.x][poll.y] += poll.age / 2;
        }
    }

    private static void autumn() {

        while(!newTrees.isEmpty()) {

            Tree poll = newTrees.poll();

            if(poll.age % 5 == 0) {

                for(int i = 0; i < 8; i++) {
                    int xx = poll.x + dx[i];
                    int yy = poll.y + dy[i];

                    if(xx <= 0 || xx > n || yy <= 0 || yy > n) continue;
                    trees.addFirst(new Tree(xx, yy, 1));
                }
            }

            trees.add(poll);
        }
    }

    private static void winter() {

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                map[i][j] += nutrient[i][j];
            }
        }
    }

}
