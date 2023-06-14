import java.util.PriorityQueue;

// 광물 캐기

class Solution {

    static class MineralSet implements Comparable<MineralSet> {
        
        int setNum;
        int weight;
        
        MineralSet(int setNum, int weight) {
            this.setNum = setNum;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(MineralSet o) {
            return o.weight - this.weight;
        }
    }
    
    static int[][] table = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
    static PriorityQueue<MineralSet> queue = new PriorityQueue<>();
    
    public int solution(int[] picks, String[] minerals) {
        
        int numOfPicks = picks[0] + picks[1] + picks[2];
        
        // 광물들을 5개씩 나누어 해당 세트의 가중치를 구해 queue에 추가
        for(int i = 0; i <= minerals.length / 5; i++) {
            
            int total = 0;
            
            for(int j = 0; j < 5; j++) {
                int idx = i * 5 + j;
                if(idx >= minerals.length || idx >= numOfPicks * 5) break;
                
                if(minerals[idx].equals("diamond")) total += 25;
                else if(minerals[idx].equals("iron")) total += 5;
                else total += 1;
            }
            
            queue.add(new MineralSet(i, total));
        }
        
        // 가중치가 높은 세트부터 꺼내서 다이아몬드 > 철 > 돌 곡괭이 순으로 광물을 캠
        int answer = 0;
        while(!queue.isEmpty()) {
            
            // 곡괭이 선택
            int pick = -1;       // 0(diamond), 1(iron), 2(stone)
            if(picks[0] > 0) {
                pick = 0;
                picks[0]--;
            }
            else if(picks[1] > 0) {
                pick = 1;
                picks[1]--;
            }
            else if(picks[2] > 0) {
                pick = 2;
                picks[2]--;
            }
            if(pick == -1) break;      // 남은 곡괭이가 없는 경우

            // queue에서 광물 세트를 꺼냄
            MineralSet poll = queue.poll();
   
            for(int i = poll.setNum * 5; i < poll.setNum * 5 + 5; i++) {
                if(i >= minerals.length) break;
                
                // 곡괭이와 광물에 매칭되는 피로도를 더함
                if(minerals[i].equals("diamond")) {
                    answer += table[pick][0];
                }
                else if(minerals[i].equals("iron")) {
                    answer += table[pick][1];
                }
                else {
                    answer += table[pick][2];
                }
            }
        }
        
        return answer;
    }
}