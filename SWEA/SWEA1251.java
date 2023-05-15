import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

// [S/W 문제해결 응용] 4일차 - 하나로

public class SWEA1251 {

    private static class Edge implements Comparable<Edge> {
        int u;
        int v;
        double weight;

        Edge(int u, int v, double weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return (int)(this.weight - o.weight);
        }
    }
    static int tc;
    static int n;
    static double taxRate;
    static double total;
    static int[] parent;
    static int[][] islands;
    static PriorityQueue<Edge> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int t = 1; t <= tc; t++) {

            queue.clear();
            total = 0;

            n = Integer.parseInt(br.readLine());
            parent = new int[n];
            for(int i = 0; i < n; i++) {
                parent[i] = i;
            }
            islands = new int[2][n];

            for(int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    islands[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            taxRate = Double.parseDouble(br.readLine());

            long answer = solution();
            sb.append("#" + t + " " + answer + "\n");
        }

        System.out.println(sb.toString());
    }

    private static long solution() {
        
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                double weight = calculateWeight(islands[0][i], islands[1][i], islands[0][j], islands[1][j]);
                queue.add(new Edge(i, j, weight));
            }
        }

        int numOfSelectedEdges = 0;
        while(!queue.isEmpty()) {
            Edge poll = queue.poll();

            if(isConnected(poll.u, poll.v)) continue;
            
            numOfSelectedEdges++;
            total += poll.weight;

            if(numOfSelectedEdges == n-1) break;
        }

        return Math.round(taxRate * total);
    }

    private static double calculateWeight(int x1, int y1, int x2, int y2) {
        return Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
    }

    private static boolean isConnected(int a, int b) {

        int pa = findParent(a);
        int pb = findParent(b);

        if(pa == pb) return true;
        parent[pb] = pa;
        return false;
    }

    private static int findParent(int x) {
        if(x == parent[x]) return x;
        return parent[x] = findParent(parent[x]);
    }
    
}
