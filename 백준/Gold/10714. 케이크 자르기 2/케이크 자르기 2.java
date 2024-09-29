import java.util.*;
import java.io.*;

/**
 * 케이크의 오른쪽으로 이동 점화식 : (idx + 1) % N
 * 케이크의 왼쪽으로 이동 점화식 : (idx + N -1) % N
 * 
 * dp[start][end] : 양 끝이 start와 end 일 때, JOI가 가져갈 수 있는 최댓값
 * 여기서 start와 end는 양쪽 끝 값을 의미
 * ex) dp[1][5] : 양쪽 끝이 1과 5라는 소리이다. 즉 2,3,4는 이미 가져가고 없다.
 * 
 * 그럼 IOI는 양쪽 중에 무조건 큰 값을 가져간다.
 */

public class Main {
	static int N;
	static long[] cake;
	static long[][] dp;
	static long answer;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		cake = new long[N];
		dp = new long[N][N];
		for(int i = 0; i < N; i++) {
			cake[i] = Long.parseLong(br.readLine());
		}
		
		for(int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		for(int i = 0; i < N; i++) {
			answer = Math.max(answer, cake[i] + IOI(i,i));
		}
		
		System.out.println(answer);
		
	}
	
	private static int goRight(int idx) {
		return (idx + 1) % N;
	}
	
	private static int goLeft(int idx) {
		return (idx + N -1) % N;
	}
	
	private static long IOI(int start, int end) {
		// 모든 케이크를 다 가져가서 케이크가 남아있지 않을 때
		if(goRight(end) == start) return 0;
		
		// 더 큰 쪽 선택하기
		// 만약 cake의 왼쪽이 오른쪽보다 크다면
		if(cake[goRight(end)] < cake[goLeft(start)]) {
			// IOI가 왼쪽 케이크를 가져가기 때문에 
			// JOI를 호출할 때는 기존 start보다 한 칸 더 왼쪽으로 간 곳에서 시작을 하게 된다.
			// 그렇게 해서 한 칸 더 왼쪽으로 간 곳부터 end까지 최적의 선택을 하도록 한다.
			return JOI(goLeft(start), end);
		} else {
			// 이건 반대
			return JOI(start, goRight(end));
		}
	}
	
	private static long JOI(int start, int end) {
		// 마지막이면
		if(goRight(end) == start) return dp[start][end] = 0;
		
		// 이미 계산된 값이 있다면
		if(dp[start][end] != -1) return dp[start][end];
		
		// 더 큰 경우 갱신
		long left = IOI(goLeft(start), end) + cake[goLeft(start)];
		long right = IOI(start, goRight(end)) + cake[goRight(end)];
		
		return dp[start][end] = Math.max(left, right);
	}
}