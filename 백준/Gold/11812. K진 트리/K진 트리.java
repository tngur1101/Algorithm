import java.util.*;
import java.io.*;

/**
 * 적은 에너지 방법
 * 1. 이전 깊이를 모두 채운 경우에만, 새로운 깊이를 만든다.
 * 2. 새로운 깊이의 노드는 가장 왼쪽부터 차례대로 추가한다.
 *
 *
 */

public class Main {

    static long N;
    static int K,Q;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        for(int q = 0; q < Q; q++){
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            if(K==1) sb.append(Math.abs(a-b)).append("\n");
            else sb.append(LCA(a,b)).append("\n");
        }

        System.out.println(sb);
    }

    private static long LCA(long a, long b){
        // 두 노드 간의 거리
        long distance = 0;

        // a와 b의 depth를 구해준다.
        long aDepth = getDepth(a);
        long bDepth = getDepth(b);

        // a의 깊이는 b의 깊이보다 무조건 크게한다 -> 그럼 if문 나눌 필요 없이 a만 처리하면 되니까
        if(aDepth < bDepth){
            // a, b 값 swap 해주고
            long temp = a;
            a = b;
            b = temp;
            // aDepth와 bDepth도 swap
            temp = aDepth;
            aDepth = bDepth;
            bDepth = temp;
        }

        // 만약 a의 높이와 b의 높이가 같지 않으면 a의 높이가 b의 높이와 같아질 때까지 a의 부모노드 탐색하기
        while (aDepth != bDepth){
            a = getParent(a);
            aDepth = getDepth(a);
            distance++;
        }

        // 위의 과정으로 높이가 같아졌으니 a와 b의 공통조상을 찾아야하는데, 둘다 움직이니까 2씩 증가해야됨
        while (a != b){
            a = getParent(a);
            b = getParent(b);
            distance += 2;
        }

//        System.out.println("distance: "+distance);

        return distance;
    }

    // 노드의 깊이를 구하는 함수
    private static long getDepth(long idx){
        // 루트노드면 0을 반환
        if(idx == 1) return 0;

        long line = 1;
        long depth = 1;

        while(true){
            line += (long) Math.pow(K, depth++);
            if(idx <= line) break;
        }
        return depth-1;
    }

    // 부모 노드를 찾는 함수
    private static long getParent(long idx){
        return (idx - 2) / K+1;
    }
}