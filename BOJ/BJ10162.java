import java.io.*;

// 전자레인지

public class BJ10162 {
	
	// 각 버튼에 지정된 초 단위 시간
	static final int aTime = 300;
	static final int bTime = 60;
	static final int cTime = 10;

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		int t = Integer.parseInt(br.readLine());
		
		// 각 버튼의 조작 횟수
		int aCnt = 0;
		int bCnt = 0;
		int cCnt = 0;
		
		if(t % 10 != 0) {				// 일의 자리 숫자가 0이 아니라면 정확히 맞출 수 없음
			System.out.println(-1);
		}
		else {		
			while(t > 0) {				// t의 값이 남아있을 때까지 while문 반복
				if(t / aTime > 0) {		// 버튼을 누르는 것이 유효하다면
					aCnt += t / aTime;  // 버튼을 누를 수 있는 횟수 만큼 cnt 증가
					t %= aTime;			// 버튼을 누른 만큼 시간 감소
					continue;
				}
				if(t / bTime > 0) {		// 이하 동일
					bCnt += t / bTime;
					t %= bTime;
					continue;
				}
				if(t / cTime > 0) {		// 이하 동일
					cCnt += t / cTime;
					t %= cTime;
					continue;
				}
			}
			System.out.println(aCnt + " " + bCnt + " " + cCnt);
		}
	}

}
