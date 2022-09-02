import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

// 좌표 압축

public class BJ18870 {

    static int n;
    static int[] arr;
    static TreeSet<Integer> set = new TreeSet<>();
    static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            set.add(arr[i]);
        }

        int val = 0;
        for(int x : set) {
            map.put(x, val++);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(map.get(arr[i]) + " ");
        }

        sb.append("\n");
        System.out.println(sb.toString());
    }
    
}
