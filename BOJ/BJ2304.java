import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 창고 다각형

public class BJ2304 {

    static private class Bar implements Comparable<Bar> {
        int pos;
        int height;

        Bar(int pos, int height) {
            this.pos = pos;
            this.height = height;
        }

        @Override
        public int compareTo(Bar o) {
            return this.pos - o.pos;
        }
    }
    static int n;
    static int maxHeight;
    static int maxLastIdx;
    static int answer;
    
    static ArrayList<Bar> arr = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            arr.add(new Bar(l, h));
            if(maxHeight <= h) {
                maxHeight = h;
                maxLastIdx = l;
            }
        }
         
        Collections.sort(arr);
        solution();
        System.out.println(answer);
    }

    private static void solution() {

        int region = -1;
        int idx = 0;
        for(int i = arr.get(0).pos; i <= arr.get(n-1).pos; i++) {
             if(i == arr.get(idx).pos) {
                region = Math.max(region, arr.get(idx).height);
                idx++;
            }

            answer += region;
            if(i == maxLastIdx) break;
        }

        region = -1;
        idx = n-1;
        for(int i = arr.get(n-1).pos; i >= 0; i--) {

            if(i == maxLastIdx) break;

            if(i == arr.get(idx).pos) {
                region = Math.max(region, arr.get(idx).height);
                idx--;
            }

            answer += region;
        } 
    }
    
}
