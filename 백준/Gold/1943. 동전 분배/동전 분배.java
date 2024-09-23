import java.util.*;
import java.io.*;

/**
 * 우선 총 금액을 다 더해서
 * 2로 나눠보고 그 나머지가 1이라면
 * 바로 그 자리에서 0을 출력하고
 * 
 * dp 배열에는 각 종류의 동전으로 만들 수 있는 액수를 체크하게 해서
 * 그 전체 금액을 2로 나눈 dp값이 true이면 1을 출력하게 하자
 * 
 * v부터 sum/2까지 그 금액이 가능한지 체크하니까 문제가 발생
 * 아마 이미 사용한 동전에 의해 true가 된 걸로 또 동전을 사용해서 다음 값을 true로 만드는듯... 그냥 틀림
 */


public class Main {
	static class Coin{
		int v;
		int cnt;
		public Coin(int v, int cnt) {
			this.v = v;
			this.cnt = cnt;
		}
	}
	static Coin[] coins;
	static boolean[] dp;
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = 3;
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			coins = new Coin[N+1];
			dp = new boolean[100001];
			
			int sum = 0;	// 받은 돈의 총 금액을 계산할 변수
			
			for(int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				
				int v = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				coins[i] = new Coin(v, c);
				sum += v*c;
				
			}
			
			if(sum % 2 != 0) {
				System.out.println(0);
				continue;
			}
			
			dp[0] = true;
			for(int i = 1; i <= N; i++) {
				int v = coins[i].v;
				int c = coins[i].cnt;
				
				for(int j = sum/2; j >= v; j--) {
					if(dp[j - v]) {
						for(int k = 0; k < c; k++) {
							if(v * k + j > sum/2) break;
							dp[v * k + j] = true;
						}
					}
				}
			}
			
			if(dp[sum/2]) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}
		}
		
	}
	
	
}