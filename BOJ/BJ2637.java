import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;

// 장난감 조립

public class BJ2637 {
    
    static int n;
    static int m;
    static int[] numOfParents;
    static ArrayList<Integer>[] adj;
    static HashMap<Integer, Integer>[] map;             // map.put(a, b) a부품이 b개 필요함
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        numOfParents = new int[n + 1];
        adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        map = new HashMap[n + 1];
        for(int i = 1; i <= n; i++) {
            map[i] = new HashMap<>();
        }

        StringTokenizer st;
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            
            numOfParents[x]++;
            adj[y].add(x);
            map[x].put(y, k);
        }

        solution();
        
        StringBuilder sb = new StringBuilder();
        
        // map에 있는 부품을 ans로 옮겨 오름차순으로 출력하기
        int[] ans = new int[n];
        for(int key : map[n].keySet()) {
            ans[key] = map[n].get(key);
        }

        for(int i = 1; i < n; i++) {
            if(ans[i] != 0) {
                sb.append(i + " " + ans[i] + "\n");
            }
        }

        System.out.println(sb.toString());
    }

    private static void solution() {

        // 선행 부품이 필요 없는 경우, 큐에 추가
        for(int i = 1; i <= n; i++) {
            if(numOfParents[i] == 0) {
                queue.add(i);
            }
        }

        while(!queue.isEmpty()) {
            int poll = queue.poll();

            for(int i = 0; i < adj[poll].size(); i++) {
                int target = adj[poll].get(i);
                numOfParents[target]--;
                
                int time = 1;
                if(map[poll].size() != 0) {         // poll을 대체해야 하는 경우, poll 삭제하기 (대체하지 않는 경우는 poll이 선행 부품이 필요 없는 경우)
                    time = map[target].get(poll);
                    map[target].remove(poll);
                }

                for(int key : map[poll].keySet()) { // poll을 구성하는 부품들을 target에 넣어줌
                    if(map[target].containsKey(key)) {
                        map[target].put(key, map[target].get(key) + map[poll].get(key) * time);
                    }
                    else {
                        map[target].put(key, map[poll].get(key) * time);
                    }
                }

                if(numOfParents[target] == 0) {
                    queue.add(target);
                }
            }
        }
    }
}