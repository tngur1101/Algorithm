import java.util.*;
import java.io.*;

/**
 * 어차피 오븐 아래로 오븐의 지름보다 더 큰 반죽은 못내려가니까
 * 오븐을 이전값과 비교하여 더 작은 값으로 채운다.
 */

public class Main {
	static int D,N;
	static int[] oven;
	static int answer;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		D = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		oven = new int[D+1];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i <= D; i++) {
			int temp = Integer.parseInt(st.nextToken());
			if(i==1) oven[i] = temp;
			else oven[i] = Math.min(oven[i-1], temp);
		}
		
		answer = D;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int dough = Integer.parseInt(st.nextToken());
			fillOven(dough);
		}
		
		
		System.out.println(answer);
	}
	
	private static void fillOven(int doughSize) {
		boolean isFill = false;
		
//		System.out.println("doughSize: " + doughSize);
		
		// 안쪽에서부터 비교
		for(int i = answer; i > 0; i--) {
			// 지금 위치에 dough를 넣을 수 있다면
//			System.out.println("oven[i] : " + oven[i]);
			if(doughSize <= oven[i]) {
//				System.out.println("if문 들어옴");
				isFill = true;
				answer = i;
				oven[i] = -1;
				break;
			}
		}
		
		if(!isFill) answer = 0;
	}
}