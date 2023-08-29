import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 2xn 직사각형을 1x2,2x1, 2x2 타일로 채우는 방법의 수
 * 
 * @author SSAFY
 *
 */

public class Main {
	
	static int N;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		long[] dp = new long[1001];
		
		dp[1]=1;
		dp[2]=3;
		
		for(int i=3;i<=N;i++) {
			dp[i]=dp[i-1]%10007 + 2*dp[i-2]%10007;
		}
		
		System.out.println(dp[N]%10007);

	}

}