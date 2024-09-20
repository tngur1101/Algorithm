import java.util.*;
import java.io.*;

/**
 * N개의 숫자로 구분된 각각의 마을에 한명의 학생이 산다.
 * N명의 학생이 x번 마을에 모여 파티
 * M개의 단방향 도로, i번째 길을 건너는데 t시간이 든다.
 * 최단시간안에 도착
 * 
 * N명의 학생들이 고고갈 때 가장 많은 시간을 소비한 학생
 * 
 * 내 생각
 * 다익스트라를 이용해서
 * 각 학생들의 이동하는데 거리를 갱신해주고
 * 그 중 가장 이동시간이 긴 학생을 프린트한다.
 * 
 * 1. X지점에서 모든 학생들까지의 거리를 계산하고
 * 2. 모든 학생들에게서 X까지 가는 최단거리를 계산
 */

public class Main {
	static final int INF = 987654321;
	static class Town implements Comparable<Town>{
		int u;
		int v;
		public Town(int u, int v) {
			this.u = u;
			this.v = v;
		}
		public int compareTo(Town t) {
			return this.v - t.v;
		}
	}
	static int N,M,X;
	static List<Town>[] originList;
	static List<Town>[] reverseList;
	static int[] go, back;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		originList = new ArrayList[N+1];
		reverseList = new ArrayList[N+1];
		
		for(int i = 0; i < N+1; i++) {
			originList[i] = new ArrayList<>();
			reverseList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			originList[from].add(new Town(to, weight));
			reverseList[to].add(new Town(from, weight));
		}
		
		go = new int[N+1];
		back = new int[N+1];
		
		go = dijkstra(originList, X);
		back = dijkstra(reverseList, X);
		
		int answer = Integer.MIN_VALUE;
		
		for(int i = 1; i <= N; i++) {
			int dist = go[i] + back[i];
			
			if(dist > answer) {
				answer = dist;
			}
		}
		System.out.println(answer);
	}
	
	private static int[] dijkstra(List<Town>[] list, int start) {
		Queue<Town> q = new PriorityQueue<>();
		
		boolean[] visited = new boolean[N+1];
		int[] dist = new int[N+1];
		
		Arrays.fill(dist, INF);
		
		q.offer(new Town(start, 0));
		dist[start] = 0;
		
		while(!q.isEmpty()) {
			Town cur = q.poll();
			
			int to = cur.u;
			if(visited[to]) continue;
			
			visited[to] = true;
			
			for(Town next: list[to]) {
				if(dist[to] + next.v < dist[next.u]) {
					dist[next.u] = dist[to] + next.v;
					q.offer(new Town(next.u, dist[next.u]));
				}
			}
		}
		
		return dist;
		
	}
}