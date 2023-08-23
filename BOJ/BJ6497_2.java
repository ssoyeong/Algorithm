import java.io.*;
import java.util.*;

// 전력난

public class BJ6497_2 {
    
    static int m, n;
    static boolean[] visited;
    static int[] minEdge;
    static ArrayList<int[]>[] graph;
    static PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        while(true) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            if(m == 0 && n == 0) break;
            
            visited = new boolean[m];
            minEdge = new int[m];
            graph = new ArrayList[m];
            for(int i = 0; i < m; i++) {
                graph[i] = new ArrayList<>();
                minEdge[i] = Integer.MAX_VALUE;
            }
            
            int totalCost = 0;
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                graph[x].add(new int[] {y, z});
                graph[y].add(new int[] {x, z});
                totalCost += z;
            }

            long ans = totalCost - prim();
            sb.append(ans + "\n");
        }

        System.out.println(sb.toString());
    }
    
    private static long prim() {
        
        queue.clear();
        int cnt = 0;
        long total = 0;
        minEdge[0] = 0;
        queue.add(new int[] {0, minEdge[0]});
        
        while(!queue.isEmpty()) {
            int[] poll = queue.poll();
            if(visited[poll[0]]) continue;

            visited[poll[0]] = true;
            total += poll[1];
            if(cnt++ == m - 1) return total;
            
            for(int[] edge : graph[poll[0]]) {
                if(!visited[edge[0]] && minEdge[edge[0]] > edge[1]) {
                    minEdge[edge[0]] = edge[1];
                    queue.add(edge);
                }
            }
        }
        return -1;
    }
    

}
