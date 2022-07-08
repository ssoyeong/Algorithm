package baekjoon.Algorithm.BOJ;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

// 카드

public class BJ11652 {
    
    static class Card implements Comparable<Card> {
        long number;
        int cnt;

        Card(long number, int cnt) {
            this.number = number;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Card o) {
            if(this.cnt == o.cnt) {
                if(this.number > o.number) return 1;
                return -1;
            }
            return o.cnt - this.cnt;
        } 
    }
    static int n;
    static HashMap<Long, Integer> map = new HashMap<>();
    static PriorityQueue<Card> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            long x = Long.parseLong(br.readLine());
            if(map.containsKey(x)){
                int cnt = map.get(x);
                map.put(x, cnt + 1);
            }
            else {
                map.put(x, 1);
            }
        }

        for(Long key : map.keySet()) {
            queue.add(new Card(key, map.get(key)));
        }

        System.out.println(queue.poll().number);

    }

}
