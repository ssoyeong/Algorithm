import java.util.HashMap;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 2023 KAKAO BLIND RECRUITMENT - 개인정보 수집 유효기간

class Solution {
    
    static int todayToDays;
    static HashMap<Character, Integer> termsMap = new HashMap<>();
    static ArrayList<Integer> answerList = new ArrayList<>();
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        // todayToDays 초기화
        todayToDays = convertDateToDays(today);
    
        // tempsMap 초기화
        for(String term : terms) {
            String[] temp = term.split(" ");
            termsMap.put(Character.valueOf(temp[0].charAt(0)), Integer.parseInt(temp[1]) * 28);
        }
        
        for(int i = 1; i <= privacies.length; i++) {
            String[] temp = privacies[i-1].split(" ");
            int targetToDay = convertDateToDays(temp[0]);
            
            boolean result = isDestroy(targetToDay, Character.valueOf(temp[1].charAt(0)));
            if(result) answerList.add(i);
        }
        
        // ArrayList를 array로 변환
        int[] answer = new int[answerList.size()];
        for(int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
    
    private static int convertDateToDays(String date) {
        
        StringTokenizer st = new StringTokenizer(date, ".");
        
        int y = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        
        return d + m * 28 + y * 12 * 28;
    }
    
    private static boolean isDestroy(int target, Character type) {
        
        if(target + termsMap.get(type) <= todayToDays) return true;
        return false;
    }
}