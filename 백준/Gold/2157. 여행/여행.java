import java.util.*;
import java.io.*;

/*
* 냅섹이랑 비슷한 건가?
* 메모이제이션 ? DP 활용 문제?
*
* 1. 1번 노드에서 bfs를 하고
* 2. dp배열에 값 갱신 가능이면 다음 노드 탐색하고
* 3. M 도시 방문했으면 dp 값 중에 제일 큰 값 출력
* */

public class Main {
    static class City{
        int idx;
        int score;
        public City(int idx, int score){
            this.idx = idx;
            this.score = score;
        }
    }
    static int N,M,K;
    static int[][] dp;
    static List<City>[] route;
    static int answer;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N+1][N+1];
        route = new List[N+1];

        // 초기화
        for(int i = 0; i <= N; i++){
            route[i] = new ArrayList<>();
        }

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a > b) continue;

            route[a].add(new City(b,c));
        }

        answer = 0;

        bfs();

        for(int i = 2; i <= M; i++){
            answer = Math.max(answer, dp[i][N]);
        }

        System.out.println(answer);

    }

    private static void bfs(){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);

        // 지나는 도시 개수
        int cnt = 1;

        while(!queue.isEmpty() && cnt < M){
            int queueSize = queue.size();

            while(queueSize-- > 0){
                int now = queue.poll();


                for(City nextCity: route[now]){

                    int nextIdx = nextCity.idx;
                    int nextScore = dp[cnt][now] + nextCity.score;

                    if(dp[cnt+1][nextIdx] < nextScore){
                        dp[cnt+1][nextIdx] = nextScore;
                        queue.offer(nextIdx);
                    }
                }
            }
            cnt++;
        }

    }

}