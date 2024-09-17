import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] arr;
	static List<Integer> list = new ArrayList<>();
	static int[] indexArr;
	static StringBuffer sb = new StringBuffer();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		// 이분탐색을 통해 증가하는 수열을 저장할 객체
		list = new ArrayList<>();
		// 입력된 각 수열의 위치를 저장
		indexArr = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		list.add(Integer.MIN_VALUE);
		
		for(int i = 1; i <= N; i++) {
			int num = arr[i];
			int left = 1;
			int right = list.size() - 1;
			
			// 확인하는 숫자가 수열의 마지막 수보다 큰 경우
			// 수열에 추가한다.
			if(num > list.get(list.size() - 1)) {
				list.add(num);
				indexArr[i] = list.size() - 1;
			}
			// 확인하는 숫자가 수열의 마지막 수보다 작은 경우
			else {
				while(left < right) {
					int mid = (left + right) / 2;
					if(list.get(mid) >= num) right = mid;
					else left = mid + 1;
				}
				list.set(right,  num);
				indexArr[i] = right;
			}
		}
		
		// 최장 길이 출력
		sb.append(list.size()-1 + "\n");
		
		// 역추적 경로를 저장할 stack
		Stack<Integer> stack = new Stack<>();
		
		// 현재 찾길 원하는 증가수열의 인덱스 값
		int idx = list.size() - 1;
		
		for(int i = N; i > 0; i--) {
			// 찾길 원하는 인덱스와 같은 경우
			if(indexArr[i] == idx) {
				// 찾길 원하는 인덱스를 하나 감소시킨다.
				idx--;
				// 스택에 경로 추가
				stack.push(arr[i]);
			}
		}
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop() + " ");
		}
		
		System.out.println(sb);
		
	}
}