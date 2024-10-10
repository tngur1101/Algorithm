import java.util.*;
import java.io.*;

/**
 * 지표면에 떨어지는 별똥별의 수를 최소화해야한다.
 * 
 * 커다란 L * L 크기의 트램벌린
 * 
 * 육제가 트램벌린으로 최대한 많은 별똥별을 튕겨낼 때, 지구에 부딪히는 별똥별의 개수를 구하기
 * 
 * N : 별똥별이 떨어지는 구역의 가로 길이
 * M : 세로길이
 * L : 트램벌린 한 변의 길이
 * K : 별똥별의 개수
 * 
 * 이후 K개의 줄
 * 별똥별이 떨어지는 위치
 * 
 * 
 */

public class Main {
	static class Pos{
		int r;
		int c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int N,M,L,K;
	static Pos[] arr;
	static Pos[] rangePos;
	static int answer;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new Pos[K];
		rangePos = new Pos[2];
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			arr[i] = new Pos(r,c);
		}
		
		answer = Integer.MAX_VALUE;
		
		simulate();
		
		System.out.println(answer);
		
	}
	
	private static void simulate() {
		for (Pos p : arr) {
            // 별똥별 좌표에서 트램펄린 위치를 계산
            for (Pos q : arr) {
                calPos(p.r, q.c); // 별똥별 p에서 시작하는 트램펄린 범위를 계산
                int result = 0;
                for (Pos ps : arr) {
                    // 트램펄린 범위 밖에 있는 별똥별의 개수 카운트
                    if (!(ps.r >= rangePos[0].r && ps.c >= rangePos[0].c && ps.r <= rangePos[1].r && ps.c <= rangePos[1].c)) {
                        result++;
                    }
                }
                // 최소값 갱신
                answer = Math.min(answer, result);
            }
        }
	}
	
	private static void calPos(int r, int c) {
		rangePos[0] = new Pos(r,c);
		rangePos[1] = new Pos(r+L, c+L);
	}
	
}