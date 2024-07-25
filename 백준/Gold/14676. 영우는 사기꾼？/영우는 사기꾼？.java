import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 헤메다가 답을 보고 풀은 문제
 * 건물들이 이전에 반드시 건설된 상태여야 지을 수 있기 때문에 위상 정렬 알고리즘을 사용해야 함
 * 위상 정렬이란 순서가 정해져 있는 작업을 차례로 수행해야 할 때 그 순서를 결정해 주기 위해 사용하는 알고리즘
 * 위상 정렬 알고리즘은 두 가지 해결책이 있는 특징이 있음
 * 1. 현재 그래프는 위상 정렬이 가능한지
 * 2. 위상 정렬이 가능하다면 그 결과는 무엇인지
 *
 * 구현은
 * 1. 스택을 이용하여 구현
 * 2. 큐를 이용하여 구현
 *
 * 큐를 이용한 위상 정렬 알고리즘 구현
 * 1. 진입차수가 0인 정점을 큐에 삽입
 * 2. 큐에서 원소를 꺼내 연결된 모든 간선을 제거
 * 3. 간선 제거 이후에 진입차수가 0이 된 정점을 큐에 삽입
 * 4. 큐가 빌 때까지 2~3번 과정을 반복 -> 모든 원소를 방문하기 전에 큐가 빈다면 사이클이 존재, 모든 원소를 방문했다면 큐에서 꺼낸 순서가 위상정렬의 결과
 *
 * 문제 풀이
 * 1. 건물 생성 시 진입차수가 0이 아니거나 건물 파괴 시 건물 개수가 없으면 치트키를 사용하여 건물을 건설 or 치트키를 사용하여 건설한 건물을 파괴하는 것이므로 치트키 사용을 알 수 있음
 * 2. 건물 생성 시, 건물 개수를 올려주고 해당 건물과 관계된 건물들의 진입차수를 줄여줌
 * 3. 건물 파괴 시, 건물 개수를 줄여주고 줄여준 건물 개수가 0일 때 해당 건물과 관계된 건물들의 진입 차수를 올려줌
 */
public class Main {

    static int N,M,K;
    static int[] degree;    // 진입 차수
    static int[] buildCnt;  // 건물 개수
    static List<List<Integer>> list = new ArrayList<>();
    static List<Set<Integer>> remove = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        degree  = new int[N+1];
        buildCnt = new int[N+1];

        for(int i = 0; i <= N; i++){
            list.add(new ArrayList<>());
            remove.add(new HashSet<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list.get(from).add(to); // 간선 저장
            degree[to]++;   // 진입 차수 저장
        }

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int now = Integer.parseInt(st.nextToken());

            // 건물 생성 시 지으려는 건물의 진입차수가 0이 이거나 즉 건물을 지을 수 없거나 건물 파괴 시 건물 개수가 0일 때
            if((order == 1 && degree[now] != 0) || order == 2 && buildCnt[now] == 0){
                System.out.println("Lier!");
                System.exit(0);
            }

            // 건물 생성할 때
            if(order == 1){
                buildCnt[now]++;
                // 지으려는 건물을 가져온다.
                for(int next : list.get(now)){
                    // 만약 건물이 삭제된 건물이 아니라면
                    if(!remove.get(next).contains(now)){
                        // 삭제해야 할 리스트에 추가
                        remove.get(next).add(now);
                        if(degree[next] == 0) continue;
                        degree[next]--;
                    }
                }
            } else if(order == 2){  // 건물 파괴할 때
                buildCnt[now]--;
                if(buildCnt[now] == 0){
                    for(int next: list.get(now)){
                        remove.get(next).clear();
                        degree[next]++;
                    }
                }
            }

        }

        System.out.println("King-God-Emperor");

    }
}