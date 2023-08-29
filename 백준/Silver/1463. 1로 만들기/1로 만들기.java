import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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
	
	static long[] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		dp = new long[N+1];
		
		start(N);
		
		
	}
	
	private static void start(int N) {
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.add(N);
		int cnt=0;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-->0) {
				int cur =queue.poll();
				if(cur==1) {
					System.out.println(cnt);
					return;
				}
				
				if(cur%3==0 && dp[cur%3]==0) {
					dp[cur/3]=cnt+1;
					queue.offer(cur/3);
				}
				if(cur%2==0 && dp[cur%2]==0) {
					dp[cur/2]=cnt+1;
					queue.offer(cur/2);
				}
				if(dp[cur-1]==0) {
					dp[cur-1]=cnt+1;
					queue.offer(cur-1);
				}
			}
			cnt+=1;
		}

	}

}