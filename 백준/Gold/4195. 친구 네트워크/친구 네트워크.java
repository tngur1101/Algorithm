import java.util.*;
import java.io.*;

/**
 * 친구 관계 확인
 * 이거 파이썬이면 딕셔너리로 풀면 되는데 자바에서는 뭐로 풀어야 하냐
 * Map을 사용하여 해결해보자
 * 그런데 어떤게 키가 되어야 함?
 * 이게 value가 되었던게 key가 될 수도 있고 key가 되었던게 value가 될 수 있는 상황이 되는거 아닌가?
 *
 * 유니온 파인드로 합치자
 */

public class Main {
    static int T,F;
    static int[] parent;
    static int[] counts;    // 각 노드마다의 층의 개수를 표현하는 배열

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for(int t = 0; t < T; t++){
            st = new StringTokenizer(br.readLine());

            F = Integer.parseInt(st.nextToken());
            Map<String, Integer> map = new HashMap<>();

            init();

            // 들어오는 친구 저장 인덱스용
            int idx = 0;

            for(int f = 0; f < F; f++){
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();

                // 만약 map에 키 a가 없으면
                if(!map.containsKey(a)){
                    map.put(a, idx++);
                }

                if(!map.containsKey(b)){
                    map.put(b, idx++);
                }
                System.out.println(union(map.get(a), map.get(b)));
            }

        }

    }

    private static void init(){
        // 최대 가능한 개수를 모두가 안겹칠 때 F*2
        parent = new int[F*2];
        counts = new int[F*2];

        // 처음에는 자기 자신이니까 한명
        Arrays.fill(counts, 1);

        // 부모 노드를 자기 자신으로 초기화
        for(int i = 0; i < F*2; i++){
            parent[i] = i;
        }
    }

    private static int union(int a, int b){
        a = find(a);
        b = find(b);

        if(a != b){
            parent[b] = a;
            counts[a] += counts[b];

            // b의 부모는 이제 무조건 a니까 올일없음
            counts[b] = 1;
        }

        return counts[a];
    }

    private static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }



}