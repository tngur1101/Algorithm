import java.util.*;
import java.io.*;

/**
 * 1km마다 연료 1L가 줄어든다.
 * 주유소에서 멈추는 횟수를 최소화하려고 한다.
 * 
 * 연료 탱크에는 연료의 제한이 없이 충분히 많이 넣을 수 있다.
 * 각각의 주유소 위치와 각 주유소에서 얻을 수 있는 연료의 양이 주어진다.
 * 
 * 성경이의 트럭과 주유소도 모두 일직선 위에 있다.
 * 주유소는 모두 성경이 트럭을 기준으로 오른쪽에 있다.
 * 
 * 입력 데이터
 * 1 <= N <= 100,000
 * 
 * N : 주유소의 개수
 * 1~N+1 : 주유소의 정보 (주유소의 위치, 채울 수 있는 연료의 양)
 * N+2 : 성경이의 위치에서 마을까지의 거리, 현재 있는 트럭의 연료의 양
 * 
 * 
 */

public class Main {
	static class GasStation implements Comparable<GasStation>{
		int loc;
		int fuel;
		public GasStation(int loc, int fuel) {
			this.loc = loc;
			this.fuel = fuel;
		}
		@Override
		public int compareTo(GasStation o) {
			return this.loc - o.loc;
		}
	}
	static int N, L, P;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		PriorityQueue<GasStation> stations = new PriorityQueue<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int l = Integer.parseInt(st.nextToken());
			int f = Integer.parseInt(st.nextToken());
			
			stations.offer(new GasStation(l, f));
		}
		
		st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> fuels = new PriorityQueue<Integer>(Collections.reverseOrder());
		
		int answer = 0;
		
		while( P < L) {
			// 갈 수 있는 주유소가 남아있고 다음 지점까지 갈 수 있는 연료가 있을 때
			while(!stations.isEmpty() && stations.peek().loc <= P) {
				fuels.offer(stations.poll().fuel);
			}
			
			// 위 과정을 했는데도 연료를 저장하는 우선순위큐가 비어있다면 => 갈 수 있는 주유소가 없는 것
			if(fuels.isEmpty()) {
				System.out.println(-1);
				return;
			}
			
			answer++;
			P += fuels.poll();
			
		}
		
		System.out.println(answer);
		
	}
	
	
}