import java.util.*;
import java.io.*;


public class Main {
	static final int INF = Integer.MAX_VALUE;
	static int N,M;
	static int[] dp;
	static int[] names;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dp = new int[N];
		names = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			names[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N; i++) {
			dp[i] = INF;
		}
		
		dp[N-1] = 0;
		
		System.out.println(calculate(0));
		
		
	}
	
	private static int calculate(int idx) {
		if(dp[idx] < INF) return dp[idx];
		
		int remain = M - names[idx];
		
		for(int i = idx+1; i <= N && remain>=0; i++) {
			if(i == N) {
				dp[idx] = 0;
				break;
			}
			dp[idx] = Math.min(dp[idx], (int)Math.pow(remain, 2)+calculate(i));
			remain -= names[i]+1;
		}
		
		return dp[idx];
	}
}