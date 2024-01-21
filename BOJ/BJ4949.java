import java.io.*;
import java.util.*;

// 균형잡힌 세상

public class BJ4949 {
    
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        boolean flag = false;

        while(true) {
            flag = false;
            stack.clear();

            String line = br.readLine();
            if(line.equals(".")) break;

            for(int i = 0; i < line.length(); i++) {
                if(line.charAt(i) == '(' || line.charAt(i) == '[') {
                    stack.add(line.charAt(i));
                }
                else if(line.charAt(i) == ')') {
                    if(stack.size() >= 1 && stack.peekLast() == '(') {
                        stack.pollLast();
                    }
                    else {
                        flag = true;
                        break;
                    }
                }
                else if(line.charAt(i) == ']') {
                    if(stack.size() >= 1 && stack.peekLast() == '[') {
                        stack.pollLast();
                    }
                    else {
                        flag = true;
                        break;
                    }
                }
            }

            if(stack.isEmpty() && !flag) sb.append("yes\n");
            else sb.append("no\n");
        }

        System.out.println(sb.toString());
    }
}
