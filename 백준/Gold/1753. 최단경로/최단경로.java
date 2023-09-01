import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 필요한 것
 * 길이 배열
 * min heap을 이용한 priority queue
 * 인접 리스트
 * 
 * @author SSAFY
 *
 */

public class Main {
	
	static final int INF = Integer.MAX_VALUE;
	
	static class Edge implements Comparable<Edge>{
		int idx;
		int weight;
		public Edge(int idx, int weight) {
			this.idx = idx;
			this.weight = weight;
		}
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		};
	}
	
	static int V, E;
	static int[] dist;
	static List<Edge>[] edge;
	static boolean[] visited;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		int s = Integer.parseInt(br.readLine());
		
		edge = new List[V+1];
		dist = new int[V+1];
		visited =new boolean[V+1];
		
		for(int i=1;i<=V;i++) {
			edge[i] = new ArrayList<>();
		}
		
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			//간선의 방향이 없기 떄문에 양방향으로 더해줌
			edge[u].add(new Edge(v,w));
		}
		
		//거리 배열 초기화
		for(int i=1;i<=V;i++) {
			dist[i] = INF;
		}
		
		Dijkstra(s);
		System.out.println(sb);
		
	}
	
	private static void Dijkstra(int s) {	// s: 시작 노드
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		//시작 거리 0
		dist[s] = 0;
		
		pq.offer(new Edge(s,0));
		while(!pq.isEmpty()) {
			Edge current = pq.poll();
			int currentIdx = current.idx;
			int currentWeight = current.weight;
			
			if(!visited[current.idx]) {
				visited[current.idx] = true;
				
				for(int i=0;i<edge[current.idx].size();i++) {
					if(dist[currentIdx]+edge[current.idx].get(i).weight<dist[edge[current.idx].get(i).idx]) {
						dist[edge[current.idx].get(i).idx] =dist[currentIdx]+edge[current.idx].get(i).weight;
						pq.offer(new Edge(edge[current.idx].get(i).idx, dist[edge[current.idx].get(i).idx]));
					}
				}	
			}
		}
		
		for(int i=1;i<dist.length;i++) {
			if(dist[i]==INF) {
				sb.append("INF").append(System.lineSeparator());
			}
			else {
				sb.append(dist[i]).append(System.lineSeparator());
			}
		}

	}

}