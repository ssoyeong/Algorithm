import java.util.HashMap;

// 완주하지 못한 선수

class Solution {
    
    public String solution(String[] participant, String[] completion) {
        
        HashMap<String, Integer> map = new HashMap<>();
        
        for(int i = 0; i < participant.length; i++) {
            
            String x = participant[i];
            
            if(map.containsKey(x)) {
                int cnt = map.get(x);
                map.remove(x);
                map.put(x, cnt + 1);
            }
            else map.put(x, 1);
        }
        
        for(int i = 0; i < completion.length; i++) {
            
            String x = completion[i];
            
            int cnt = map.get(x);
            map.remove(x);
            if(cnt > 1) map.put(x, cnt - 1);
        }
        
        String answer = "";
        for(String key : map.keySet()) {
            answer = key;
        }
        
        return answer;
    }
}
