import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] map;
	static int[][][] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		dp = new int[N][N][3];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//파이프 시작 꼭짓점은 (1,1)부터 시작
		dp[0][1][0] = 1;
		for(int j=2;j<N;j++) {
			if(map[0][j]==0) {
				dp[0][j][0] = dp[0][j-1][0] + dp[0][j-1][2];
			}
		}
		
		for(int i=1;i<N;i++) {
			for(int j=1;j<N;j++) {
				if(map[i][j]==0) {
					if(map[i][j-1]==0) {
						dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];
					}
					if(map[i-1][j]==0) {
						dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];
					}
					if(map[i-1][j]==0 && map[i][j-1]==0 && map[i-1][j-1]==0) {
						dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
					}
				}
			}
		}
		
		int answer = dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2];
		System.out.println(answer);
	}

}