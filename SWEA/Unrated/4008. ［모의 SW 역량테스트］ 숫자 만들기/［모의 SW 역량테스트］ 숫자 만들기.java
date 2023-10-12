import java.io.BufferedReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

/**
 * 숫자는 움직이지 않음
 * 연산카드를 다양한 순서로 조합하여 수식을 계산
 * 연산자의 우선 순위는 고려하지 않고 왼쪽에서 오른쪽 차례대로 계산
 * 
 * next Permutation을 이용하여 연산카드 조합을 계산
 * 각 계산마다 수식 계산해주면서 최솟값 최댓값 판볋하여 갱신
 * 
 * @author SSAFY
 *
 */

public class Solution {

	static int N;
	static int numbers[];
	static int[] operator;
	static int[] out;
	static int min, max;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			
			numbers = new int[N];
			operator = new int[4];
			out = new int[N-1];
			
			st = new StringTokenizer(br.readLine());
			
			for(int i=0;i<4;i++) {
				operator[i] = Integer.parseInt(st.nextToken()); 
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				numbers[i]=Integer.parseInt(st.nextToken());
			}
			
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			
			permutation(0);
			
			sb.append("#").append(tc).append(" ").append(max-min).append(System.lineSeparator());
		}
		System.out.println(sb);
	}
	
	private static void permutation(int depth) {
		if(!isAble(out, depth))return;
		
		//기저 조건
		if(depth==N-1) {
			int cal = calculate(out);
//			System.out.println(Arrays.toString(out));
			min = Math.min(min, cal);
			max = Math.max(max, cal);
			return;
		}
		
		for(int i=0;i<4;i++) {
			out[depth]=i;
			permutation(depth+1);
		}
	}
	
	private static int calculate(int[] arr) {
		int start = numbers[0];
		int result = start;
		
		for(int i=0;i<arr.length;i++) {
			if(arr[i]==0) {	//덧셈
				result = result+numbers[i+1];
			}
			else if(arr[i]==1) {	//뺄셈
				result = result-numbers[i+1];
			}
			else if(arr[i]==2) {	//곱셈
				result = result*numbers[i+1];
			}
			else if(arr[i]==3) {	//나눗셈
				result = result/numbers[i+1];
			}
		}
		
		return result;
	}
	
	private static boolean isAble(int[] arr, int depth) {
		int[] count = new int[4];
		
		for(int i=0;i<depth;i++) {
			count[arr[i]]+=1;
			if(operator[arr[i]]<count[arr[i]]) return false;
		}
		
		return true;
		
	}

}