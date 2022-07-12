import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 텀 프로젝트

public class BJ9466 {
	
	static int t;
	static int n;
    static int answer;
	static boolean[] visited;
    static boolean[] checked;
    static int[] arr;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		t = Integer.parseInt(br.readLine());
		
		
		for(int test = 0; test < t; test++) {

			n = Integer.parseInt(br.readLine());
			visited = new boolean[n+1];
            checked = new boolean[n+1];
            arr = new int[n+1];

            answer = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

            for(int i = 1; i <= n; i++) {
                if(checked[i]) continue;
                dfs(i);
            }

			sb.append((n - answer)).append("\n");
		}
		
		System.out.println(sb.toString());
	}

    private static void dfs(int x){

        if(checked[x]) return;
        if(visited[x]) {
            checked[x] = true;
            answer++;
        }

        visited[x] = true;
        dfs(arr[x]);
        checked[x] = true;
        visited[x] = false;
    }

}
