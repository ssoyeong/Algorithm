import java.util.Arrays;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for(int i = 0; i < commands.length; i++) {
            
            int n = commands[i][1] -  commands[i][0] + 1;
            int[] sub = new int[n];
            int x = commands[i][0] - 1;
            for(int j = 0; j < n; j++) {
                sub[j] = array[x];
                x++;
            }
            
            Arrays.sort(sub);
            answer[i] = sub[commands[i][2] - 1];
        }
        
        return answer;
    }
}
