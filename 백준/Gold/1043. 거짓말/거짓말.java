import java.util.*;
import java.io.*;

/**
 * 진실을 아는 사람이 포함되면 안된다.
 * 유니온 파인드를 이용해서
 * 진실을 아는 사람과 부모가 같으면
 * 안되게 하면 안될까
 */

public class Main {
	static int[] parent;
	static int N,M;
	static List<Integer> truthList = new ArrayList<>();
	static int answer;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		initParent();
		
		st = new StringTokenizer(br.readLine());
		int truthSize = Integer.parseInt(st.nextToken());
		
		if(truthSize == 0) {
			System.out.println(M);
			return;
		} else {
			for(int i = 0; i < truthSize; i++) {
				truthList.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		List<Integer>[] partyList = new ArrayList[M];
		for(int i = 0; i < M; i++) {
			partyList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int pSize = Integer.parseInt(st.nextToken());
			
			int first = Integer.parseInt(st.nextToken());
			partyList[i].add(first);
			
			for(int j = 1; j < pSize; j++) {
				int p = Integer.parseInt(st.nextToken());
				union(first, p);
				partyList[i].add(p);
			}
		}
		
		answer = 0;
		
		for(int i = 0; i < M; i++) {
			boolean flag = true;
			for(int p : partyList[i]) {
				if(truthList.contains(find(parent[p]))) {
					flag = false;
					break;
				}
			}
			
			if(flag) answer++;
		}
		
		System.out.println(answer);
	}
	
	private static void initParent() {
		parent = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}
	
	
	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(truthList.contains(bRoot)) {
			int temp = aRoot;
			aRoot = bRoot;
			bRoot = temp;
		}
		
		parent[bRoot] = aRoot;
	}
	
	private static int find(int a) {
		if(parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	
}