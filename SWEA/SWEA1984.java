import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

// 중간 평균값 구하기

class SWEA1984 {
	
    static int t;
    static double total;
    static TreeMap<Integer, Integer> map = new TreeMap<>();
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int tc = 1; tc <= t; tc++) {
            
            total = 0;
            map.clear();
            st = new StringTokenizer(br.readLine());
            
            for(int i = 0; i < 10; i++) {
                int x = Integer.parseInt(st.nextToken());
            	total += x;
                
                if(map.containsKey(x)) {
                	int cnt = map.get(x);
                    map.remove(x);
                    map.put(x, cnt+1);
                }
                else {
                	map.put(x, 1);
                }
            }

            int first_cnt = map.get(map.firstKey());
            int last_cnt = map.get(map.lastKey());
            
			total -= map.firstKey() * first_cnt;
            total -= map.lastKey() * last_cnt;
            
            sb.append(String.format("#%d %d\n", tc, Math.round((double)total / (10 - first_cnt - last_cnt)))); 
        }
        
        System.out.println(sb.toString());
    }
}
