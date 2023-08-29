import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * x에 사용할 수 있는 연산
 * 1. x가 3으로 나누어 떨어지면, 3으로 나눔
 * 2. x가 2로 나누어 떨어지면, 2로 나눔
 * 3. 1을 뺀다
 * 
 * N이 주어졌을 때, 위와 같은 연산 3개를 적절히 사용해서 1을 만들것임
 * 연산 횟수의 최솟값 구하기
 * 
 * @author SSAFY
 *
 */

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		long dp[] = new long[N+1];
		
		for(int i=2;i<=N;i++) {
			dp[i] = dp[i-1]+1;
			
			if(i%3==0) {
				dp[i] = Math.min(dp[i/3]+1, dp[i]);
			}
			if(i%2==0) {
				dp[i] = Math.min(dp[i/2]+1, dp[i]);
			}
			
//			System.out.println(Arrays.toString(dp));
		}
		
		System.out.println(dp[N]);
		
		
	}

}