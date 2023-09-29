import java.io.*;
import java.util.*;

// 탈출

public class BJ3055_2 {

   static int r, c;
   static char[][] map;
   static boolean[][] visited;
   static ArrayDeque<int[]> dochi = new ArrayDeque<>();
   static ArrayDeque<int[]> water = new ArrayDeque<>();
   static int[] dx = {-1, 0, 1, 0};
   static int[] dy = {0, 1, 0, -1};
   
   public static void main(String[] args) throws Exception {
	
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   StringTokenizer st = new StringTokenizer(br.readLine());
	   r = Integer.parseInt(st.nextToken());
	   c = Integer.parseInt(st.nextToken());
	   map = new char[r][c];
	   visited = new boolean[r][c];
	   
	   for(int i = 0; i < r; i++) {
		   String line = br.readLine();
		   for(int j = 0; j < c; j++) {
			   map[i][j] = line.charAt(j);
			   if(map[i][j] == 'S') {
				   visited[i][j] = true;
				   dochi.add(new int[] {i, j, 0});
				   map[i][j] = '.';
			   }
			   else if(map[i][j] == '*') {
				   water.add(new int[] {i, j});
			   }
		   }
	   }
	   
	   int ans = solution();
	   if(ans == -1) System.out.println("KAKTUS");
	   else System.out.println(ans);
   }
   
   private static int solution() {
	   
	   int time = -1;
	   
	   while(!dochi.isEmpty()) {
		   int[] poll = dochi.poll();
		   
		   if(poll[2] > time) {
			   fillWater();
			   time++;
		   }
		   
		   for(int d = 0; d < 4; d++) {
			   int xx = poll[0] + dx[d];
			   int yy = poll[1]+ dy[d];
			   
			   if(xx < 0 || xx >= r || yy < 0 || yy >= c || map[xx][yy] == 'X' || map[xx][yy] == '*') continue;
			   if(map[xx][yy] == 'D') {
				   return poll[2] + 1;
			   }
			   if(!visited[xx][yy]) {
				   visited[xx][yy] = true;
				   dochi.add(new int[] {xx, yy, poll[2] + 1});
			   }
		   } 
	   }
	   
	   return -1;
   }

   private static void fillWater() {
	   
	  int cnt = water.size();
	  for(int i = 0; i < cnt; i++) {
		   int[] poll = water.poll();
		   for(int d = 0; d < 4; d++) {
			   int xx = poll[0] + dx[d];
			   int yy = poll[1] + dy[d];
			   
			   if(xx < 0 || xx >= r || yy < 0 || yy >= c || map[xx][yy] == 'X') continue;
			   if(map[xx][yy] == '.') {
				   map[xx][yy] = '*';
				   water.add(new int[] {xx, yy});
			   }
		   }
	   }
   }
   
}
