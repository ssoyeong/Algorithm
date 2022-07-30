import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

// 통계학

public class BJ2108 {

    static int n;
    static double total;
    static long avg;
    static int median;
    static int mode;
    static int range;

    static int[] arr;
    static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        for(int i = 0; i < n; i++) {

            int x = Integer.parseInt(br.readLine());

            arr[i] = x;
            if(map.containsKey(x)) {
                int cnt = map.get(x);
                map.put(x, cnt + 1);
            }
            else map.put(x, 1);
        }

        solution();

        StringBuilder sb = new StringBuilder();
        sb.append(avg).append("\n");
        sb.append(median).append("\n");
        sb.append(mode).append("\n");
        sb.append(range).append("\n");
        System.out.println(sb.toString());
    }

    private static void solution(){

        // 범위, 중앙값
        Arrays.sort(arr);
        range = Math.abs(arr[0] - arr[n-1]);
        median = arr[(int)n/2];

        // 산술평균
        for(int key : map.keySet()) {
            total += key * map.get(key);
        }
        avg = Math.round(total/n);
        
        // 최빈값
        ArrayList<Integer> keySetList = new ArrayList<>(map.keySet());
        Collections.sort(keySetList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(map.get(o1) == map.get(o2)) return o1 - o2;
                return map.get(o2) - map.get(o1);
            }
        });

        mode = keySetList.get(0);
        int modeCnt = map.get(mode);

        if(n > 1) {
            if(modeCnt == map.get(keySetList.get(1))) {
                mode = keySetList.get(1);
            }
        }
    }
    
}
