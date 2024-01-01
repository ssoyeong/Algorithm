import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        
        PriorityQueue<int[]> start = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[0], o2[0]));
        PriorityQueue<int[]> end = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        
        StringTokenizer st;
        for(int i = 0; i < book_time.length; i++) {
            int[] book = new int[2];
            for(int j = 0; j < 2; j++) {
                st = new StringTokenizer(book_time[i][j], ":");
                int h = Integer.parseInt(st.nextToken()) * 100;
                int m = Integer.parseInt(st.nextToken());
                if(j == 1) {
                    m += 10;
                    if(m >= 60) {
                        h += 100;
                        m -= 60;
                    }
                }
                book[j] = h + m;
            }
            start.add(book);
        }
        
        end.add(start.poll());
        while(!start.isEmpty()) {
            int[] poll = start.poll();
            if(poll[0] >= end.peek()[1]) end.poll();
            end.add(poll);
        }

        return end.size();
    }
}
