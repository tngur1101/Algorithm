import java.util.*;
import java.io.*;

public class Main {
    static final long MOD = 1000000007;
    static int N;
    static long[] arr;
    static long[] dp;
    static long ans;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        
        arr = new long[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }
        
        Arrays.sort(arr);
        
        dp = new long[N+1];
        for(int i = 0; i <= N; i++){
            if(i == 0) dp[0] = 1;
            else {
                dp[i] = (dp[i-1]*2)%MOD;
            }
        }
        
        for(int i = 1; i <= N; i++){
            ans += (dp[i-1] - dp[N-i])*arr[i];
            ans %= MOD;
        }

        System.out.println(ans);

        
    }
}