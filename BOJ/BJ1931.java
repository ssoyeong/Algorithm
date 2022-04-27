package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

// 회의실 배정

public class BJ1931 {
	
	static class Meeting implements Comparable<Meeting> {
		
		int start;
		int end;
		
		Meeting(int start, int end){
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) {
			
			if(this.end == o.end) {
				return this.start - o.start;
			}
			return this.end - o.end;
		}
	}
	
	static int n;
	static Meeting[] sked;
	static int cnt;
	static int endTime;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		sked = new Meeting[n];
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			Meeting m = new Meeting(s, e);
			sked[i] = m;
		}
		
		Arrays.sort(sked);
		
		cnt++;
		endTime = sked[0].end;
		for(int i = 1; i < sked.length; i++) {
			if(sked[i].start >= endTime) {
				cnt++;
				endTime = sked[i].end;
			}
		}
		
		System.out.println(cnt);
	}

}
