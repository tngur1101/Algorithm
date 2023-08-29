import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 정수 4를 1, 2, 3의 합으로 나타내는 방법은 총 7가지가 있다. 합을 나타낼 때는 수를 1개 이상 사용해야 한다.
 * 정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 프로그램을 작성하시오.
 * 
 * @author SSAFY
 *
 */

public class Main {
	
	static int N;
	static long[] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			
			dp = new long[12];
			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;
			
			for(int i=4;i<=N;i++) {
				dp[i] = dp[i-1]+dp[i-2]+dp[i-3];
			}
			System.out.println(dp[N]);
		}

	}
	
}