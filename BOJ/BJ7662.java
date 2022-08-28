import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeMap;

// 이중 우선순위 큐

public class BJ7662 {

    static int t, k;
    static TreeMap<Integer, Integer> map = new TreeMap<>();
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int test = 0; test < t; test++) {
            map.clear();

            k = Integer.parseInt(br.readLine());
            for(int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                Character cmd = st.nextToken().charAt(0);
                int val = Integer.parseInt(st.nextToken());

                calculate(cmd, val);
            }

            print();
        }
        
        System.out.println(sb.toString());
    }
    
    private static void calculate(Character cmd, int val) {

        if(cmd == 'I') {
            if(map.containsKey(val)) {
                int cnt = map.get(val);
                map.remove(val);
                map.put(val, cnt + 1);
            }
            else {
                map.put(val, 1);
            }
        }
        else if(val == 1) {
            if(!map.isEmpty()) {
                int lastKey = map.lastKey();
                int cnt = map.get(lastKey);
                map.remove(lastKey);
                if(cnt != 1) map.put(lastKey, cnt - 1);
            }
        }
        else {
            if(!map.isEmpty()) {
                int firstKey = map.firstKey();
                int cnt = map.get(firstKey);
                map.remove(firstKey);
                if(cnt != 1) map.put(firstKey, cnt - 1);
            }
        }
    }

    private static void print() {

        if(map.isEmpty()) {
            sb.append("EMPTY\n");
        }
        else {
            sb.append(map.lastKey() + " " + map.firstKey() + "\n");
        }
    }
}
