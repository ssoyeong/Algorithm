import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.io.IOException;

// 오아시스 재결합

public class BJ3015 {

    private static class Person {
        long height;
        long cnt;

        Person(long height, long cnt) {
            this.height = height;
            this.cnt = cnt;
        }
    }
    static int n;
    static long ans;
    static Stack<Person> stack = new Stack<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            long h = Long.parseLong(br.readLine());
            Person input = new Person(h, 1);

            while(!stack.isEmpty() && stack.peek().height <= input.height) {        // input보다 키가 크거나 같은 사람은 모두 pop (stack 안은 내림차순 유지)
                Person pop = stack.pop();
                ans += pop.cnt;                                                     // input보다 키가 크거나 작은 사람은 볼 수 있으니 +1

                if(pop.height == input.height) {                                    // input과 키가 같다면, 그동안 키가 같던 인원 수의 총합을 cnt에 저장
                    input.cnt += pop.cnt;
                }
            }

            if(!stack.isEmpty()) ans++;                                             // input보다 키가 큰 사람도 볼 수 있으니 +1
            stack.add(input);
        }

        System.out.println(ans);
    }
}