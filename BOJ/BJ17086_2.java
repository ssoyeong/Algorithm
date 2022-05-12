package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 아기 상어 2

public class BJ17086_2 {
	
	static int n;
	static int m;
	static int answer;
	
	static boolean[][] map;
	static int[][] dist;
	static ArrayList<Shark> sharks = new ArrayList<>();
	
	static class Shark {
		
		int x;
		int y;
		
		Shark(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new boolean[n][m];
		dist = new int[n][m];
		
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				if(st.nextToken().equals("1")) {
					map[i][j] = true;
					sharks.add(new Shark(i, j));
				}
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
		
		solution();
		System.out.println(answer);
	}
	
	private static void solution() {
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				
				if(map[i][j]) continue;
				
				for(int k = 0; k < sharks.size(); k++) {
					int distance = Math.max(Math.abs(sharks.get(k).x - i), Math.abs(sharks.get(k).y - j));
					dist[i][j] = Math.min(dist[i][j], distance);
				}
				
				answer = Math.max(answer, dist[i][j]);
			}
		}
	}
}
