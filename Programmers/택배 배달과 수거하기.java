import java.util.Stack;

// 2023 KAKAO BLIND RECRUITMENT - 택배 배달과 수거하기

class Solution {
    
    static class House {
        int dist;
        int num;
        
        House(int dist, int num) {
            this.dist = dist;
            this.num = num;
        }
    }
    static long answer;
    static Stack<House> delivery = new Stack<>();
    static Stack<House> pickup = new Stack<>();
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        
        for(int i = 0; i < n; i++) {
            if(deliveries[i] != 0) {
                delivery.add(new House(i + 1, deliveries[i]));
            }
            if(pickups[i] != 0) {
               pickup.add(new House(i + 1, pickups[i])); 
            }
        }
        
        while(true) {
            if(delivery.isEmpty() && pickup.isEmpty()) break;
            
            int distance = 0;       // 이동할 거리
            
            // 배달하는 길
            if(!delivery.isEmpty()) distance = delivery.peek().dist;        // delivery할 집 중에서 가장 먼 집의 거리
            
            int cntOfDeliveryBox = 0;
            while(!delivery.isEmpty()) {
                House pollDelivery = delivery.pop();
                cntOfDeliveryBox += pollDelivery.num;
                if(cntOfDeliveryBox >= cap) {
                    if(cntOfDeliveryBox > cap) {
                        delivery.add(new House(pollDelivery.dist, cntOfDeliveryBox - cap));
                    }
                    break;
                }
            }
            
            // 수거하는 길
            if(!pickup.isEmpty()) distance = Integer.max(distance, pickup.peek().dist);          // delivery할 집 vs pickup할 집 중에서 더 먼 집의 거리
            
            int cntOfPickupBox = 0;
            while(!pickup.isEmpty()) {
                House pollPickup = pickup.pop();
                cntOfPickupBox += pollPickup.num;
                if(cntOfPickupBox >= cap) {
                    if(cntOfPickupBox > cap) {
                        pickup.add(new House(pollPickup.dist, cntOfPickupBox - cap));
                    }
                    break;
                }
            }
            
            answer += distance * 2;     // 이동거리의 왕복
        }

        return answer;
    }
}