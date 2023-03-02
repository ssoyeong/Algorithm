// [카카오 인턴] 키패드 누르기

class Solution {
    
    static int left = 10;
    static int right = 12;
    static int[] dx = {-1, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3};
    static int[] dy = {-1, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2};
    
    static StringBuilder answer = new StringBuilder();
    
    public String solution(int[] numbers, String hand) {
        
        for(int i = 0; i < numbers.length; i++) {
            int target = numbers[i];
            if(target == 0) target = 11;
            
            if(target == 1 || target == 4 || target == 7) {
                answer.append("L");
                left = target;
            }
            else if(target == 3 || target == 6 || target == 9) {
                answer.append("R");
                right = target;
            }
            else {
                double leftDistance = calculateDistance(target, left);
                double rightDistance = calculateDistance(target, right);
                
                if(leftDistance > rightDistance) {
                    answer.append("R");
                    right = target;
                }
                else if(leftDistance < rightDistance) {
                    answer.append("L");
                    left = target;
                }
                else {
                    if(hand.equals("left")) {
                        answer.append("L");
                        left = target;
                    }
                    else {
                        answer.append("R");
                        right = target;
                    }
                }
                
            }
        }
        return answer.toString();
    }
    
    private double calculateDistance(int a, int b) {
        return Math.abs(dx[a] - dx[b]) + Math.abs(dy[a] - dy[b]);
    }
}