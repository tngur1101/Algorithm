import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static class Shortcut{
        int start;
        int end;
        int weight;
        public Shortcut(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
    static int N,D;
    static ArrayList<Shortcut>[] highway;
    static int[] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        highway = new ArrayList[10001];
        dp = new int[10001];
        for(int i = 0; i < highway.length; i++){
            highway[i] = new ArrayList<>();
            dp[i] = 10001;
        }

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if(e - s < w) continue;
            highway[e].add(new Shortcut(s,e,w));
        }

        dp[0] = 0;
        for(int i = 1; i <= D; i++){
//            System.out.println("i: "+i);
            dp[i] = dp[i-1]+1;
//            System.out.println("dp["+i+"]: "+dp[i]);
            if(highway[i].size() > 0){
                for(Shortcut s: highway[i]){
//                    System.out.println("s.start: "+s.start+", s.weight: "+s.weight);
                    if(dp[s.start] + s.weight > dp[i]) continue;
//                    System.out.println("dp["+(i-1)+"]+1: "+(dp[i-1]+1));
//                    System.out.println("dp[s.start]+s.weight: "+(dp[s.start] + s.weight));
                    dp[i] = Math.min(dp[i-1]+1, dp[s.start] + s.weight);
//                    System.out.println("dp["+i+"]: "+dp[i]);
                }
            }
        }

        System.out.println(dp[D]);

    }
}