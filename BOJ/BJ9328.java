import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;

// 열쇠

public class BJ9328 {
    
    private static class Point {
        
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int tc;
    static int h, w;
    static int cnt;                 // 훔칠 수 있는 문서의 개수
    static char[][] board;
    static boolean[][] visited;
    static HashSet<Character> keys = new HashSet<>();
    static HashMap<Character, ArrayList<Point>> doors = new HashMap<>();
    static Queue<Point> queue = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < tc; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            
            cnt = 0;
            board = new char[h][w];
            visited = new boolean[h][w];
            keys.clear();
            doors.clear();
            queue.clear();

            for(int i = 0; i < h; i++) {
                String line = br.readLine();
                for(int j = 0; j < w; j++) {
                    char value = line.charAt(j);
                    board[i][j] = value;

                    if((i == 0 || i == h - 1 || j == 0 || j == w - 1) && value != '*') {

                        visited[i][j] = true;

                        if(value == '.') {
                            queue.add(new Point(i, j));
                        }
                        if('a' <= value && value <= 'z') {
                            queue.add(new Point(i, j));
                            keys.add(value);
                        }
                        else if('A' <= value && value <= 'Z') {
                            ArrayList<Point> temp = new ArrayList<>();

                            if(doors.containsKey(value)) {
                                temp.addAll(doors.get(value));
                            }
                            temp.add(new Point(i, j));
                            doors.put(value, temp);
                        }
                        else if(value == '$') {
                            queue.add(new Point(i, j));
                            cnt++;
                        }
                    }
                }
            }

            String line = br.readLine();
            if(!line.equals("0")) {
                for(int i = 0; i < line.length(); i++) {
                    keys.add(line.charAt(i));
                }
            }
            
            solution();
            sb.append(cnt + "\n");
        }

        System.out.println(sb.toString());
    }

    private static void solution() {

        // 가장자리에서 발견된 문들에 대하여 처음에 갖고 있는 키로 열 수 있는 경우
        for(char door : doors.keySet()) {
            if(keys.contains((char)(door + 32))) {

                ArrayList<Point> temp = doors.get(door);
                for(Point pos : temp) {
                    queue.add(pos);
                }
            }
        }

        while(!queue.isEmpty()) {
            
            Point poll = queue.poll();

            for(int i = 0; i < 4; i++) {
                int xx = poll.x + dx[i];
                int yy = poll.y + dy[i];

                if(xx < 0 || xx >= h || yy < 0 || yy >= w) continue;
                if(visited[xx][yy] || board[xx][yy] == '*') continue;

                char value = board[xx][yy];
                visited[xx][yy] = true;
                
                if(value == '.') {
                    queue.add(new Point(xx, yy));
                }
                else if('a' <= value && value <= 'z') {
                    queue.add(new Point(xx, yy));
                    keys.add(value);

                    if(doors.containsKey((char)(value - 32))) {                 // 찾은 키로 이미 방문한 문들 중 열 수 있는 문이 있다면
                        ArrayList<Point> temp = doors.get((char)(value - 32));
                        for(Point pos : temp) {
                            queue.add(pos);
                        }
                    }
                }
                else if('A' <= value && value <= 'Z') {
                    ArrayList<Point> temp = new ArrayList<>();

                    if(doors.containsKey(value)) {
                        temp.addAll(doors.get(value));
                    }
                    temp.add(new Point(xx, yy));
                    doors.put(value, temp);

                    if(keys.contains((char)(value + 32))) {                     // 찾은 문에 해당하는 키가 있다면
                        for(Point pos : temp) {
                            queue.add(pos);
                        }
                    }
                }
                else if(value == '$') {
                    queue.add(new Point(xx, yy));
                    cnt++;
                }
            }
        }
    }

}
