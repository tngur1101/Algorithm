import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 처음 떠올린 것은 Dfs
 * 
 * 시간복잡도
 * dfs로 하면
 * 2^98
 * 
 * 선택한 것은 DP
 * 
 * 8 => 1
 * 8 3 => 5 11
 * 8 3 2 => 3 7 8 13
 * 8 3 2 4 => -1 7 3 11 4 12 9 17
 * 
 * 점화식
 * dp[i][num] = dp[i-1][num-Ai]+dp[i-1][num+Ai]
 * dp[i][prev+arr[i]] += dp[i-1][prev]
 * dp[i][prev-arr[i]] -= dp[i-1][prev]
 */


public class Main {
    
    static int N;
    static int[] arr;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        long[][] dp = new long[N-1][21];
        dp[0][arr[0]]=1;
        
        for(int i = 1; i < N-1; i++){
            for(int j = 0; j <= 20; j++){
                if(j-arr[i] >= 0){
                    dp[i][j]+=dp[i-1][j-arr[i]];
                }
                if(j+arr[i] <= 20){
                    dp[i][j]+=dp[i-1][j+arr[i]];
                }
            }
        }

        System.out.println(dp[N-2][arr[N-1]]);
    }
}