import java.util.*;
import java.io.*;

/**
 * 앞에서부터 우선순위 큐에 집어넣으면서 더해버리면 안된다.
 * 큐의 크기 = 내가 풀기로 한 문제의 수
 * 
 * 그리고 큐의 맨 위를 지속적으로 보면서 그 값이 현재 값보다 작으면
 * 빼서 교체해야한다.
 * 
 */

public class Main {
	static class Problem implements Comparable<Problem>{
		int dead;
		int cup;
		public Problem(int dead, int cup) {
			this.dead = dead;
			this.cup = cup;
		}
		public int compareTo(Problem p) {
			return this.dead - p.dead;
		}
	}
	static int N;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	static long answer;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		List<Problem> problems = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int deadLine = Integer.parseInt(st.nextToken());
			int cupNoodle = Integer.parseInt(st.nextToken());
			
			problems.add(new Problem(deadLine, cupNoodle));
		}
		
		answer = 0;
		
		// 문제 데드라인이 가까운 것부터 정렬
		Collections.sort(problems);

		// 데드라인이 가까운 문제부터 돌면서
		for(Problem p: problems) {
			// 만약 pq.size가 현재 문제의 데드라인보다 작다면
			if(pq.size() < p.dead) {
				pq.offer(p.cup);
				answer += p.cup;
			} else {
				if(pq.peek() < p.cup) {
					answer -= pq.poll();
					pq.offer(p.cup);
					answer += p.cup;
				}
			}
		}
		
		
		System.out.println(answer);
	}
}