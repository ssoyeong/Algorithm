import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.PriorityQueue;

// [S/W 문제해결 기본] 1일차 - 최빈수 구하기

class SWEA1204 {

    static int t;
    static int n;
    static HashMap<Integer, Integer> map = new HashMap<>();
    static PriorityQueue<Number> queue = new PriorityQueue<Number>();

    static class Number implements Comparable<Number> {

        int num;
        int cnt;

        Number(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Number o) {
            if (this.cnt == o.cnt)
                return o.num - this.num;
            return o.cnt - this.cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int test_case = 0; test_case < t; test_case++) {
            
            map.clear();
            queue.clear();
        	n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 1000; i++) {
                int x = Integer.parseInt(st.nextToken());
                if(map.containsKey(x)) {
                    int cnt_tmp = map.get(x);
                    map.remove(x);
                    map.put(x, cnt_tmp+1);
                }
                else {
                    map.put(x, 1);
                }
            }
            
     		for(int key : map.keySet()) {
                queue.add(new Number(key, map.get(key)));
            }
                          
            Number poll = queue.poll();
            sb.append(String.format("#%d %d\n", n, poll.num));
        }
    
    	System.out.println(sb.toString());
    }
}
