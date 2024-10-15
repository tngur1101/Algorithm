import java.util.*;
import java.io.*;

/**
 * 처음 아이디어 : 조합을 통해 Y개만큼 뽑고 남은 개수 처리 => 시간초과
 * 
 * 투포인터를 활용한 슬라이딩 윈도우로 접근
 */

public class Main {
	static int N, M, X, Y;
	static boolean[] error;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		error = new boolean[N+1];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < M; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			error[tmp] = true;
		}
		
		st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        
        solution();
		
	}
	
	private static void solution() {
		int start = 1, end = 1;
		int errorCnt = 0;
		int sequenceCnt = 0;
		
		int answer = Integer.MAX_VALUE;
		
		if (N == 1) {
            System.out.println(M - Y);
            return;
        }
		
		while (true) {
            if (start == N) break;  // start가 N에 도달하면 종료

            // 조건을 만족하지 않으면 end를 증가
            if ((errorCnt < Y || sequenceCnt < X) && end <= N) {
                if (error[end++]) errorCnt++;  // 에러가 있는 줄이라면 카운트 증가
                sequenceCnt++;
            } else {
                // 조건을 만족하면 ans 갱신
                if (errorCnt >= Y && sequenceCnt >= X) {
                	answer = Math.min(answer, errorCnt);
                }
                // start를 증가시키면서 윈도우를 줄임
                if (error[start++]) errorCnt--;  // 에러가 있는 줄이면 카운트 감소
                sequenceCnt--;
            }
        }
		
		
		System.out.println(M - answer);
	}
	
	
}