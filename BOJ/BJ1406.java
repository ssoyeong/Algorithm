import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.IOException;

// 에디터

public class BJ1406 {
	
    static int m;
    static int cursor;
    static char command, ch;
    static Stack<Character> first = new Stack<>();
    static Stack<Character> second = new Stack<>();
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        cursor = str.length();
        for(int i = 0; i < str.length(); i++) {
        	first.add(str.charAt(i));
        }

        m = Integer.parseInt(br.readLine());
        
        StringTokenizer st;
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            command = st.nextToken().charAt(0);
            if(command == 'P') {
                ch = st.nextToken().charAt(0);
            }

            execute();
        }

        StringBuilder sb = new StringBuilder();
        while(!second.isEmpty()) {
            sb.append(second.pop());
        }
        sb.reverse();
        while(!first.isEmpty()) {
        	sb.append(first.pop());
        }
        sb.reverse().append("\n");
        System.out.println(sb.toString());
    }

    private static void execute() {

        if(command == 'L' && !first.isEmpty()) {
        	second.add(first.pop());
        }
        else if(command == 'D' && !second.isEmpty()) {
        	first.add(second.pop());
        }
        else if(command == 'B' && !first.isEmpty()) {
        	first.pop();
        }
        else if(command == 'P'){
        	first.add(ch);
        }
    }
    
}
