import java.util.*;
import java.io.*;


public class Main {
	static class Node{
		int from;
		int to;
		int value;
		public Node(int from, int to, int value) {
			this.from = from;
			this.to = to;
			this.value = value;
		}
	}
	static int N,M;
	static int[] parent;
	static int start, end, max = Integer.MIN_VALUE;
	static ArrayList<Node> list = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i <M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			list.add(new Node(from, to, value));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		Collections.sort(list, (o1, o2) -> {
			return o2.value - o1.value;
		});
		
		for(Node node: list) {
			union(node.from, node.to);
			
			if(find(start) == find(end)) {
				System.out.println(node.value);
				System.exit(0);
			}
		}
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return;
		
		if(a > b) {
			parent[a] = b;
		} else {
			parent[b] = a;
		}
	}
	
	private static int find(int a) {
		if(a == parent[a]) return a;
		
		return parent[a] = find(parent[a]);
	}
}