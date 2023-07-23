import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

// DSLR
 
public class BJ9019 {
     
    private static class Point {
        int n;
        String answer;
        
        Point(int n, String answer) {
        	this.n = n;
            this.answer = answer;
        }
    }
    static int t;
    static int A, B;
    static boolean[] visited = new boolean[10000];
    static Queue<Point> queue = new LinkedList<>();
     
    public static void main(String[] args) throws IOException {
     
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
         
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int tc = 1; tc <= t; tc++) {
            
            Arrays.fill(visited, false);
            queue.clear();
             
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            
            String ans = solution();
            sb.append(ans + "\n");
        }
         
        System.out.println(sb.toString());
    }
     
    private static String solution() {
		
        visited[A] = true;
        queue.add(new Point(A, ""));
         
        while(!queue.isEmpty()) {
        	
            Point poll = queue.poll();
            
            if(poll.n == B) {
                return poll.answer;
            }
            
            int newN = commandD(poll.n);
            if(!visited[newN]) {
                visited[newN] = true;
                queue.add(new Point(newN, poll.answer.concat("D")));
            }
            
            newN = commandS(poll.n);
            if(!visited[newN]) {
                visited[newN] = true;
                queue.add(new Point(newN, poll.answer.concat("S")));
            }
            
            newN = commandL(poll.n);
            if(!visited[newN]) {
                visited[newN] = true;
                queue.add(new Point(newN, poll.answer.concat("L")));
            }
            
            newN = commandR(poll.n);
            if(!visited[newN]) {
                visited[newN] = true;
                queue.add(new Point(newN, poll.answer.concat("R")));
            }
        }
        
        return "";
    }
    
    private static int commandD(int n) {
        return (n * 2) % 10000;
    }
    
    private static int commandS(int n) {
        return n == 0 ? 9999 : n - 1;
    }
    
    private static int commandL(int n) {
        return (n % 1000) * 10 + n / 1000;
    }
    
    private static int commandR(int n) {
        return (n % 10) * 1000 + n / 10;
    }
    
}