import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 플로이드 워셜로 풀어보기
 * 2-친구 관계: 한다리 건너서 아는 사람까지 친구로 본다
 * 
 * @author SSAFY
 *
 */

public class Main {
	
	static int N;
	static char[][] board;
	static int[][] dist;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		board = new char[N][N];
		dist = new int[51][51];
		int answer = 0;
		
		for(int i=0;i<N;i++) {
			String line = br.readLine();
			for(int j=0;j<N;j++) {
				board[i][j]=line.charAt(j);
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(i==j) {
					dist[i][j]=0;
				}else if(board[i][j]=='Y') {
					dist[i][j]=1;
				}else {
					dist[i][j]=1000000;
				}
			}
		}
		
//		print();
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				for(int k=0;k<N;k++) {
					if(i==j||j==k||i==k)continue;
					if(dist[j][k]>dist[i][j]+dist[i][k]) {
						dist[j][k] = dist[i][j]+dist[i][k];
					}
				}
			}
		}
		
//		print();
		
		for(int i=0;i<N;i++) {
			int sum = 0;
			for(int j=0;j<N;j++) {
				if(i==j)continue;
				if(dist[i][j]<=2) {
					sum++;
				}
			}
			answer = Math.max(answer, sum);
		}
		
		System.out.println(answer);
	}
	
	private static void print() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(dist[i][j]+" ");
			}
			System.out.println();
		}

	}

}