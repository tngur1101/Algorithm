import java.util.*;
import java.io.*;

/**
 * 문자열 게임 방식
 * 1. 알파벳 소문자로 이루어진 문자열 W가 주어짐
 * 2. 양의 정수 K가 주어짐
 * 3. 어떤 문자를 정확히 K개를 포함하는 가장 짧은 연속 문자열의 길이를 구한다.
 * 4. 어떤 문자를 정확히 K개 포함하고 문자열의 첫번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열의 길이를 구한다.
 * 
 * 일단 k가 1이면 어떤 문자 1개를 포함하는 최대 및 최소 크기가 1이니까 1 1 출력
 * 알파벳 별로 개수를 저장해서
 * 그 중 가장 최소와 최대를 출력해주면 되지 않나? 
 */

public class Main {

	static int T;
	static int[] alphabet;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		for(int t = 0; t < T; t++) {
			String s = br.readLine();
			int k = Integer.parseInt(br.readLine());
			
			if(k == 1) {
				System.out.println("1 1");
				continue;
			}
			
			alphabet = new int[26];
			
			// 알파벳별 개수 증가
			for(int i = 0; i < s.length(); i++) {
				alphabet[s.charAt(i) - 'a']++;
			}
			
			int minVal = Integer.MAX_VALUE;
			int maxVal = -1;
			
			for(int i = 0; i < s.length(); i++) {
				// 만약 사용된 알파벳의 개수가 k보다 작으면 걔는 볼 필요가 없음
				if(alphabet[s.charAt(i) - 'a'] < k) continue;
				
				int cnt = 1;
				for(int j = i+1; j < s.length(); j++) {
					if(s.charAt(i) == s.charAt(j)) cnt++;
					
					if(cnt == k) {
						minVal = Math.min(minVal, j-i+1);
						maxVal = Math.max(maxVal, j-i+1);
						break;
					}
				}
			}
			
			if(minVal == Integer.MAX_VALUE || maxVal == -1) System.out.println("-1");
			else System.out.println(minVal + " " + maxVal);
		}
	}
}