import java.util.*;
import java.io.*;

public class Main {
	static int N,K;
	static int[][] map;
	static boolean[] visited;
	static int answer;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 플로이드 워셜을 써서 전부 탐색?
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				for(int l = 0; l < N; l++) {
					if(j == l) continue;
					if(map[j][l] > map[j][i] + map[i][l]) {
						map[j][l] = map[j][i] + map[i][l];
					}
				}
			}
		}
		
		answer = Integer.MAX_VALUE;
		visited = new boolean[N];
		visited[K] = true;
		
		dfs(K, 0, 0);
		
		System.out.println(answer);		
	}
	
	private static void dfs(int k, int sum, int depth) {
		if(depth == N-1) {
			answer = Math.min(answer, sum);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(i == k) continue;
			if(visited[i]) continue;
			
			visited[i] = true;
			dfs(i, sum + map[k][i], depth+1);
			visited[i] = false;
		}
	}

}