import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 운영 비용 : K*K+(K-1)*(K-1)
 * 
 * 의문 1. 모든 점에 대해서 K별, 계산을 해줘야하는가
 * 의문 2. 혹은 집인 부분에서만 K별 계산을 해줘야하는가
 * 
 */

public class Solution {

	static class Node{
		int r;
		int c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N,M, totalHomeCnt, maxHomeCnt;
	static int[][] map;
	static List<Node> list;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			list = new ArrayList<>();
			
			// 맵 입력받기
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if(map[i][j]==1)list.add(new Node(i,j));
				}
			}
			
			totalHomeCnt = list.size();
			maxHomeCnt = Integer.MIN_VALUE;
			
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					int currentHomeCnt = 0;
					getHome(i, j, currentHomeCnt);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(maxHomeCnt).append(System.lineSeparator());
		}
		System.out.println(sb);
	}
	
	private static void getHome(int x, int y, int cnt) {
		int cost = 1;
		for(int dist=0;dist<=N;dist++) {
			cost += dist*4;
			
			for(int i=0;i<list.size();i++) {
				if(Math.abs(list.get(i).r-x)+Math.abs(list.get(i).c-y)==dist) {
					cnt++;
				}
			}
			
			if(cnt>0) {
				if(cnt*M-cost>=0) {
					if(cnt>maxHomeCnt)maxHomeCnt=cnt;
					if(maxHomeCnt==totalHomeCnt)return;
				}
			}
		}
	}

}