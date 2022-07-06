package baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// 회전 초밥

public class BJ15961 {

    static int n, d, k, c;
    static int max;
    static int[] arr;
    static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // first case
        for(int i = 0; i < k; i++){
        	
        	if(map.containsKey(arr[i])) {
        		int cnt = map.get(arr[i]);
        		map.put(arr[i], cnt + 1);
        	}
        	else {
        		map.put(arr[i], 1);
        	}
        	
            max = map.size();
            if(!map.containsKey(c)) max++;
        }
        
        int num = 0;
        for(int point = 0; point < n-1; point++) {
        	
        	if(map.containsKey(arr[(point + k) % n])) {
        		int cnt = map.get(arr[(point + k) % n]);
        		map.put(arr[(point + k) % n], cnt + 1);
        	}
        	else {
        		map.put(arr[(point + k) % n], 1);
        	}
        	
        	int cnt = map.get(arr[point]);
        	if(cnt == 1) map.remove(arr[point]);
        	else map.put(arr[point], cnt - 1);
        	
        	num = map.size();
            if(!map.containsKey(c)) num++;
            max = Math.max(max, num);
        }

        System.out.println(max);
    }
}

