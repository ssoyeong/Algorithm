import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 게리맨더링

public class BJ17471 {

    static int n;
    static int min = Integer.MAX_VALUE;
    static int[] population;
    static boolean[] checkTeam;
    static boolean[] visited;
    static ArrayList<Integer>[] adj;
    static Queue<Integer> queue = new LinkedList<>();
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        adj = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        population = new int[n+1];
        checkTeam = new boolean[n+1];
        visited = new boolean[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int numOfSection = Integer.parseInt(st.nextToken());
            for(int j = 0; j < numOfSection; j++) {
                adj[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        // 구역을 2개로 나눔 (makeTeam이 True인 경우와 False인 경우)
        makeTeam(1);

        if(min == Integer.MAX_VALUE) min = -1;
        System.out.println(min);
    }


    private static void makeTeam(int x) {

        if(x == n + 1) {
            if(isConnected(true) && isConnected(false)) {       // 두 구역이 각각 연결된 경우
                int totalOfTrue = calculateTotal(true);
                int totalOfFalse = calculateTotal(false);
                min = Integer.min(min, Math.abs(totalOfTrue - totalOfFalse));
            }

            return;
        }

        checkTeam[x] = true;
        makeTeam(x + 1);
        checkTeam[x] = false;
        makeTeam(x + 1);
    }

    private static boolean isConnected(boolean flag) {

        queue.clear();
        Arrays.fill(visited, false);

        // 방문을 시작할 구역 찾기
        for(int i = 1; i <= n; i++) {
            if(checkTeam[i] == flag) {
                visited[i] = true;
                queue.add(i);
                break;
            }
        }

        // 방문을 시작할 점부터, 연결된 구역 중 같은 팀인 경우 방문 처리
        while(!queue.isEmpty()) {
            int poll = queue.poll();

            for(int i = 0; i < adj[poll].size(); i++) {
                int target = adj[poll].get(i);
                if(checkTeam[target] == flag && !visited[target]) {
                    visited[target] = true;
                    queue.add(target);
                }
            }
        }

        // 방문 처리가 되지 않은 구역이 있는지 확인
        for(int i = 1; i <= n; i++) {
            if(!visited[i] && checkTeam[i] == flag) return false;
        }

        return true;
    }

    private static int calculateTotal(boolean flag) {

        int total = 0;
        for(int i = 1; i <= n; i++) {
            if(checkTeam[i] == flag) {
                total += population[i];
            }
        }

        return total;
    }
    
}
