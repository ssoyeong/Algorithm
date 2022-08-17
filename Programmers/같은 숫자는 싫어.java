import java.util.*;

public class Solution {
    
    static ArrayList<Integer> list = new ArrayList<>();
    
    public Integer[] solution(int[] arr) {
        
        int pre = -1;
        for(int i = 0; i < arr.length; i++) {
            
            int x = arr[i];
            
            if(x != pre) {
                list.add(x);
                pre = x;
            }
        }
        
        Integer[] answer = list.toArray(new Integer[0]);
        return answer;
    }
}
