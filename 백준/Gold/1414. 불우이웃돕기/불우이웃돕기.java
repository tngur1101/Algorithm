import java.util.*;
import java.io.*;

/**
 * N개의 방
 * 각 방에는 컴퓨터가 하나씩 있음
 * 각 컴퓨터는 랜선으로 연결
 * 어떤 컴퓨터 A와 컴퓨터 B가 있을 때, A와 B가 서로 랜선으로 연결되어있거나, 또 다른 컴퓨터를 통해서 연결되어있으면 서로 통신 가능
 * N개의 컴퓨터가 서로 연결되어 있는 랜선의 길이가 주어질 때, 다솜이가 기부할 수 있는 랜선의 길이의 최댓값 출력
 *
 * a~z : 1 ~ 26
 * A~Z : 27~52
 * 1 <= N <= 50
 *
 * 최소 신장 트리 활용 문제
 *
 * 첫번째 케이스의 경우
 * 1번 노드 - 1번 노드 간선 : 1
 * 1번 노드 - 2번 노드 간선 : 2,4
 * 1번 노드 - 3번 노드 간선 : 3,7
 * 2번 노드 - 2번 노드 : 5
 * 2번 노드 - 3번 노드 : 6,8
 * 3번 노드 - 3번 노드 : 9
 *
 * 최소 비용 신장 트리의 경우, 예제에 따르면 가중치가 2인 간선과 3인 간선을 선택
 * 따라서 남는 랜선의 길이는 40
 *
 * 그럼 위 문제에서 그래프는 무방향 그래프인가?
 *
 * 최소 신장 트리는 크루스칼 알고리즘과 프림 알고리즘이 있다고 배운건 기억이 남
 *
 * 크루스칼은 union-find를 이용해서 간선을 중점으로 구현
 *
 * parent 배열이 N+1만큼 있고, 초기에는 각 노드를 부모로 가리키게 하자.
 *
 * A의 아스키코드 : 65
 * a의 아스키코드 : 97
 *
 */

public class Main {

    static class Node implements Comparable<Node>{
        int from;
        int to;
        int value;
        public Node(int from, int to, int value){
            this.from = from;
            this.to = to;
            this.value = value;
        }
        // 오름차순 정렬
        public int compareTo(Node o){
            return this.value - o.value;
        }
    }
    static int N;
    static int[] parents;
    static List<Node> list = new ArrayList<>();
    static int sum;
    static int cnt;

    public static void main(String[] args) throws  Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        parents = new int[N+1];
        // parents 배열 초기화
        // 자기 자신을 root 노드로 보고 있는다.
        for(int i = 1; i <= N; i++){
            parents[i] = i;
        }

        char[] temp = new char[N];

        sum = 0;

        for(int i = 1; i <= N; i++){
            temp = br.readLine().toCharArray();
            for(int j = 0; j < N; j++){
                if(temp[j] != '0'){
                    if(temp[j] >= 'a' && temp[j] <= 'z'){
                        // 자기 자신이면 무조건 남는 랜선이기 때문에 추가
                        if(i == j+1){
                            sum += temp[j] - 96;
                        } else {    // 자기 자신이 아니면
                            sum += temp[j] - 96;
                            list.add(new Node(i, j+1, temp[j] - 96));
                        }
                    } else {
                        if (i == j+1){
                            sum += temp[j] - 38;
                        } else {
                            sum += temp[j] - 38;
                            list.add(new Node(i,j+1, temp[j] - 38));
                        }
                    }
                }
            }
        }

//        System.out.println("크루스칼 전");
//        System.out.println("sum: "+sum);
        Collections.sort(list);
        kruskal();

        if(cnt != N-1) System.out.println(-1);
        else System.out.println(sum);

    }

    private static void kruskal(){
        cnt = 0;
        for(Node node: list){
            if(union(node.from, node.to)){
                sum -= node.value;
                cnt++;
                if(cnt == N-1) break;
            }
        }
    }



    private static boolean union(int a, int b){
        int nodeA = find(a);
        int nodeB = find(b);
        if (nodeA == nodeB) return false;
        parents[nodeA] = nodeB;
        return true;
    }

    private static int find(int a){
        if(a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }


}