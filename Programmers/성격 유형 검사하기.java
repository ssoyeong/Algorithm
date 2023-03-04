import java.util.HashMap;

// 2022 KAKAO TECH INTERNSHIP - 성격 유형 검사하기

class Solution {
    
    static HashMap<Character, Integer> map = new HashMap<>();
    
    public String solution(String[] survey, int[] choices) {
        
        // map 초기화
        map.put('R', 0);
        map.put('T', 0);
        map.put('F', 0);
        map.put('C', 0);
        map.put('J', 0);
        map.put('M', 0);
        map.put('A', 0);
        map.put('N', 0);
        
        // map에 점수 합계 담기
        for(int i = 0; i < choices.length; i++) {
            
            Character typeA = Character.valueOf(survey[i].charAt(0));
            Character typeB = Character.valueOf(survey[i].charAt(1));
            
            int score = 4 - choices[i];
            if(score > 0) {
                int tempScore = map.get(typeA);
                map.put(typeA, tempScore + Math.abs(score));
            }
            else if(score < 0) {
                int tempScore = map.get(typeB);
                map.put(typeB, tempScore + Math.abs(score));
            }
        }
        
        // 점수에 따라 성격 유형 결정
        StringBuilder answer = new StringBuilder();
        String type = "";
        
        type = map.get('R') < map.get('T') ? "T" : "R";
        answer.append(type);
        type = map.get('C') < map.get('F') ? "F" : "C";
        answer.append(type);
        type = map.get('J') < map.get('M') ? "M" : "J";
        answer.append(type);
        type = map.get('A') < map.get('N') ? "N" : "A";
        answer.append(type);
        
        return answer.toString();
    }
}