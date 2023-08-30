import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static class House{
		int r;
		int g;
		int b;
		public House(int r, int g, int b) {
			this.r = r;
			this.g = g;
			this.b = b;
		}
	}
	
	static int N;	//집의 수
	static int[][] dp;	
	//dp 배열 =>
	//행 => 0: 빨, 1: 초, 2: 파
	//열 => 집의 번호
	static House[] house;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		dp = new int[N][N];
		house = new House[N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			house[i] = new House(r,g,b);
		}
		
		dp[0][0] = house[0].r;
		dp[1][0] = house[0].g;
		dp[2][0] = house[0].b;
		
		for(int i=1;i<N;i++) {
			dp[0][i] = Math.min(dp[1][i-1], dp[2][i-1])+house[i].r;
			dp[1][i] = Math.min(dp[0][i-1], dp[2][i-1])+house[i].g;
			dp[2][i] = Math.min(dp[0][i-1], dp[1][i-1])+house[i].b;
		}
		
		int answer = Math.min(dp[0][N-1], Math.min(dp[1][N-1], dp[2][N-1]));
		System.out.println(answer);
	}

}