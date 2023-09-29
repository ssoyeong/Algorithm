import java.io.*;
import java.util.*;

// 달이 차오른다, 가자.

public class BJ1194 {

   static class Point {
	   int x;
	   int y;
	   int cnt;
	   int keyInfo;
	   
	   Point(int x, int y, int cnt, int keyInfo) {
		   this.x = x;
		   this.y = y;
		   this.cnt = cnt;
		   this.keyInfo = keyInfo;
	   }
   }
   static int n, m;
   static char[][] map;
   static boolean[][][] visited;
   static ArrayDeque<Point> queue = new ArrayDeque<>();
   static int[] dx = {-1, 0, 1, 0};
   static int[] dy = {0, 1, 0, -1};
   
   public static void main(String[] args) throws Exception {
	
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   StringTokenizer st = new StringTokenizer(br.readLine());
	   n = Integer.parseInt(st.nextToken());
	   m = Integer.parseInt(st.nextToken());
	   
	   map = new char[n][m];
	   visited = new boolean[n][m][1 << 6];
	   
	   for(int i = 0; i < n; i++) {
		   String line = br.readLine();
		   for(int j = 0; j < m; j++) {
			   map[i][j] = line.charAt(j);
			   if(map[i][j] == '0') {
				   visited[i][j][0] = true;
				   queue.add(new Point(i, j, 0, 0));
				   map[i][j] = '.';
			   }
		   }
	   }
	   
	   System.out.println(bfs());
   }
   
   private static int bfs() {
	   
	   while(!queue.isEmpty()) {
		   Point poll = queue.poll();
		   
		   for(int i = 0; i < 4; i++) {
			   int xx = poll.x + dx[i];
			   int yy = poll.y + dy[i];
			   
			   if(xx < 0 || xx >= n || yy < 0 || yy >= m || map[xx][yy] == '#' || visited[xx][yy][poll.keyInfo]) continue;
			   if(map[xx][yy] == '1') {
				   return poll.cnt + 1;
			   }
			   
			   if('A' <= map[xx][yy] && map[xx][yy] <= 'F') {
				   if((poll.keyInfo & (1 << (map[xx][yy] - 65))) != 0) {
					   visited[xx][yy][poll.keyInfo] = true;
					   queue.add(new Point(xx, yy, poll.cnt + 1, poll.keyInfo));
				   }
			   }
			   else if('a' <= map[xx][yy] && map[xx][yy] <= 'f') {
				   int newKeyInfo = poll.keyInfo | (1 << (map[xx][yy] - 97));
				   visited[xx][yy][newKeyInfo] = true;
				   queue.add(new Point(xx, yy, poll.cnt + 1, newKeyInfo));
			   }
			   else {
				   visited[xx][yy][poll.keyInfo] = true;
				   queue.add(new Point(xx, yy, poll.cnt + 1, poll.keyInfo));
			   }
		   }
	   }
	   
	   return -1;
   }
}
