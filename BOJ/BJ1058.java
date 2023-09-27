import java.io.*;

// 친구

public class BJ1058 {

   static final int MAX = 999999;
   static int n;
   static int[][] dist;
   
   public static void main(String[] args) throws Exception {
	
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   n = Integer.parseInt(br.readLine());
	   
	   dist = new int[n][n];
	   for(int i = 0; i < n; i++) {
		   for(int j = 0; j < n; j++) {
			   if(i != j) dist[i][j] = MAX;
		   }
	   }
	   
	   for(int i = 0; i < n; i++) {
		   String line = br.readLine();
		   for(int j = 0; j < n; j++) {
			   if(line.charAt(j) == 'Y') dist[i][j] = 1;
		   }
	   }
	   
	   System.out.println(solution());
   }
   
   private static int solution() {
	   
	   for(int k = 0; k < n; k++) {
		   for(int i = 0; i < n; i++) {
			   for(int j = 0; j < n; j++) {
				   dist[i][j] = Integer.min(dist[i][j], dist[i][k] + dist[k][j]);
			   }
		   }
	   }
	   
	   int ans = 0;
	   for(int i = 0; i < n; i++) {
		   int cnt = 0;
		   for(int j = 0; j < n; j++) {
			   if(dist[i][j] == 1 || dist[i][j] == 2) cnt++;
		   }
		   ans = Integer.max(ans, cnt);
	   }
	   
	   return ans;
   }

}
