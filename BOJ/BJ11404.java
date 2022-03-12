package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

// 플로이드

public class BJ11404 {
	
	static int numV;
	static int numE;
	static int[][] matrix;
	static int INF = 10000001;		// edge 최대 100000 * vertex 최대 100 보다 크게 설정 
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		numV = Integer.parseInt(br.readLine());
		numE = Integer.parseInt(br.readLine());
		
		matrix = new int[numV+1][numV+1];
		for(int i = 0; i < matrix.length; i++) {
			Arrays.fill(matrix[i], INF);
		}
		
		for(int i = 0; i < numE; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			
			if(matrix[x][y] > val) {
				matrix[x][y] = val;
			}
		}
		
		for(int i = 1; i <= numV; i++) {
			for(int j = 1; j <= numV; j++) {
				if(i == j) {
					matrix[i][j] = 0;
				}
			}
		}
		
		floydWarshall();
		print();
		
	}
	
	private static void floydWarshall() {
		
		for(int k = 1; k <= numV; k++) {			
			for(int i = 1; i <= numV; i++) {
				
				if(i == k) continue;
				for(int j = 1; j <= numV; j++) {
					
					if(j == k || i == j) continue;
					if(matrix[i][j] > matrix[i][k] + matrix[k][j]) {
						matrix[i][j] = matrix[i][k] + matrix[k][j];
					}
				}
			}
		}
	}
	
	private static void print() {
		for(int i = 1; i <= numV; i++) {
			for(int j = 1; j <= numV; j++) {
				if(matrix[i][j] >= INF) {
					System.out.print("0" + " ");
				}
				else{
					System.out.print(matrix[i][j] + " ");
				}
			}
			System.out.println();
		}
	}

}
