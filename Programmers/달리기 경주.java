import java.util.HashMap;
import java.util.Arrays;

// 달리기 경주

class Solution {
    
    static class Players implements Comparable<Players> {
        String name;
        int rank;
        
        Players(String name, int rank) {
            this.name = name;
            this.rank = rank;
        }
        
        @Override
        public int compareTo(Players o) {
            return this.rank - o.rank;
        }
    }
    static HashMap<String, Integer> mapName = new HashMap<>();
    static HashMap<Integer, String> mapRank = new HashMap<>();
    
    public String[] solution(String[] players, String[] callings) {
        
        for(int i = 0; i < players.length; i++) {
            mapName.put(players[i], i + 1);
            mapRank.put(i + 1, players[i]);
        }
        
        for(int i = 0; i < callings.length; i++) {
            String target = callings[i];
            int targetRank = mapName.get(target);
            String preTarget = mapRank.get(targetRank - 1);
            
            mapName.put(target, targetRank - 1);
            mapName.put(preTarget, targetRank);
            mapRank.put(targetRank - 1, target);
            mapRank.put(targetRank, preTarget);
            
        }
        
        Players[] result = new Players[players.length];
        
        int i = 0;
        for(String name : mapName.keySet()) {
            result[i] = new Players(name, mapName.get(name));
            i++;
        }
        
        Arrays.sort(result);
        
        String[] answer = new String[players.length];
        
        i = 0;
        for(Players p : result) {
            answer[i] = p.name;
            i++;
        }
        return answer;
    }
}