import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        for(int[] range : targets) queue.add(range);
        
        int ans = 1;
        int min = queue.poll()[1];
        while(!queue.isEmpty()) {
            int[] poll = queue.poll();
            if(min > poll[0]) {
                min = Integer.min(min, poll[1]);
            }
            else {
                ans++;
                min = poll[1];
            }
        }

        return ans;
    }
}
