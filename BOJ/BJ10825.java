import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 국영수

public class BJ10825 {

    private static class Student implements Comparable<Student> {

        String name;
        int korean;
        int english;
        int math;

        Student(String name, int korean, int english, int math) {

            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        @Override
        public int compareTo(Student o) {

            if(this.korean != o.korean) return o.korean - this.korean;
            if(this.english != o.english) return this.english - o.english;
            if(this.math != o.math) return o.math - this.math;
            return this.name.compareTo(o.name);
        }
    }

    static int n;
    static ArrayList<Student> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr.add(new Student(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(arr);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(arr.get(i).name).append("\n");
        }

        System.out.println(sb.toString());
    }
    
}
