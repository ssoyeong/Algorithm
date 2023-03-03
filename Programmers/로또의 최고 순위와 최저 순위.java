import java.util.HashSet;

// 2021 Dev-Matching: 웹 백엔드 개발자(상반기) - 로또의 최고 순위와 최저 순위

class Solution {
    
    static int high, low;
    static int[] answer = new int[2];
    static int[] convertRank = {6, 6, 5, 4, 3, 2, 1};
    
    public int[] solution(int[] lottos, int[] win_nums) {
        
        HashSet<Integer> set = new HashSet<>();
        for(int win_num : win_nums) {
            set.add(win_num);
        }
        
        int numOfZero = 0;
        for(int x : lottos) {
            if(x == 0) numOfZero++;
            else {
                if(set.contains(x)) low++;
            }
        }
        
        high = low + numOfZero;
        
        answer[0] = convertRank[high];
        answer[1] = convertRank[low];
        
        return answer;
    }
}