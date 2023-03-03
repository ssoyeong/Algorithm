import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

// 2022 KAKAO BLIND RECRUITMENT - 신고 결과 받기

class Solution {
    
    static int[] answer;
    static int[] reportedCnt;
    static ArrayList<Integer>[] reportList;
    static ArrayList<Integer> suspendList = new ArrayList<>();
    static HashMap<String, Integer> convertId = new HashMap<>();
    
    public int[] solution(String[] id_list, String[] report, int k) {
    
        answer = new int[id_list.length];
        reportedCnt = new int[id_list.length];
        
        // reportList 초기화
        reportList = new ArrayList[id_list.length];
        for(int i = 0; i < id_list.length; i++) {
            reportList[i] = new ArrayList<>();
            
            // id와 index 매핑
            convertId.put(id_list[i], i);
        }
        
        for(int i = 0; i < report.length; i++) {
            String[] input = report[i].split(" ");
            
            // 이용자id idx
            int idxA = convertId.get(input[0]);
            // 신고한id idx
            int idxB = convertId.get(input[1]);
            
            // reportList에 신고 횟수 담기
            reportList[idxA].add(idxB);
        }
        
        for(int i = 0; i < id_list.length; i++) {
            // reportList 중복 제거
            HashSet<Integer> tempSet = new HashSet<>();
            tempSet.addAll(reportList[i]);
            
            // reportedCnt에 신고당한 횟수 담기
            for(int idx : tempSet) {
                reportedCnt[idx]++;
            }
        }
        
        // suspendList에 이용 정지 idx 담기
        for(int i = 0; i < id_list.length; i++) {
            if(reportedCnt[i] >= k) suspendList.add(i);
        }
        
        // answer에 메일 받은 횟수 담기
        for(int i = 0; i < id_list.length; i++) {
            
            int mailCnt = 0;
            for(int j = 0; j < suspendList.size(); j++) {
                if(reportList[i].contains(suspendList.get(j))) {
                    mailCnt++;
                }
            }
            
            answer[i] = mailCnt;
        }
        
        return answer;
    }
}