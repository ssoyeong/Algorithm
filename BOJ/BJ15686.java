package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

// 치킨 배달

class Point {
	
	int x;
	int y;
	
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class BJ15686 {
	
	static int n;
	static int m;
	static ArrayList<Point> home = new ArrayList<>();
	static ArrayList<Point> chicken = new ArrayList<>();
	static Stack<Point> selected = new Stack<>();
	static int minDist = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				int val = Integer.parseInt(st.nextToken());
				if(val == 1) home.add(new Point(i, j));
				else if(val == 2) chicken.add(new Point(i, j));
			}
		}

		dfs(0);
		
		System.out.println(minDist);
		

	}
	
	private static void dfs(int idx) {
		
		if(selected.size() == m) {
			calcDist();
			return;
		}
		
		for(int i = idx; i < chicken.size(); i++) {
			selected.add(chicken.get(i));
			dfs(i + 1);
			selected.pop();
		}
		
	}
	
	private static void calcDist() {
		
		int total = 0;
		for(Point home : home) {
			int min = Integer.MAX_VALUE;
			for(Point chicken : selected) {
				min = Math.min(min, Math.abs(home.x - chicken.x) + Math.abs(home.y - chicken.y));
			}
			total += min;
			
			if(total > minDist) return;
		}
		
		minDist = Math.min(minDist, total);
		
	}
	

}
