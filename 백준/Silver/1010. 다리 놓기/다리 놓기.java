import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] dp;
	static int N,M;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=0;tc<T;tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			//	우리가 구해야 할 것 : mCn
			
			dp = new int[M+1][N+1];
			
			for(int i=0;i<=M;i++) {
				for(int j=0,end=Math.min(i, N);j<=end;j++) {
					if(j==0 || i==j) {
						dp[i][j]=1;
					}
					else {
						dp[i][j] = dp[i-1][j-1]+dp[i-1][j];
					}
				}
			}
//			print();
			
			sb.append(dp[M][N]).append(System.lineSeparator());
		}
		System.out.println(sb);
	}
	
	private static void print() {
		for(int i=0;i<=M;i++) {
			for(int j=0;j<=N;j++) {
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}

	}

}