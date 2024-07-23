import java.util.*;
import java.io.*;

/**
 * 택배 보낼 박스들을 정렬
 * 각 마을마다 택배차에 실을 수 있는 개를 기록, 마을마다 기록되어 있는 택배차의 남은 용량을 실시간으로 수정
 * 현재 택배차가 머무르고 있는 마을에서 보낼 택배 박스를 택배차가 가지고 가도 되는지 확인하기
 *
 * 정렬 기준 우선 박스를 받는 마을 번호 기준으로 오름차, 받은 마을 번호가 같을 시 보내는 마을 번호 기준으로 오름차
 *
 * 용량 기록하기
 * 1. 처음에는 마을들을 택배 용량 C만큼 기록
 * 2. 마을 도착 시, 해당 마을에서 박스를 실을 용량이 있는지 체, 용량이 남는 박스를 실어주기
 * 3. 실어준 박스의 목표 마을 전까지 용량을 줄여주기
 */

public class Main {

    static int N,C,M;
    static int[][] priorities;
    static int[] capacities;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        priorities = new int[M][3];
        capacities = new int[N + 1];

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int box = Integer.parseInt(st.nextToken());

            priorities[i][0] = s;
            priorities[i][1] = e;
            priorities[i][2] = box;
        }

        sortPrioritiy();

        for(int i = 1; i < N; i++){
            capacities[i] = C;
        }

        System.out.println(getAnswer());

    }

    private static void sortPrioritiy(){
        Arrays.sort(priorities, ((x,y) -> {
            if(x[1] == y[1]){
                return x[0] - y[0];
            }

            return x[1] - y[1];
        }));
    }

    private static int getAnswer(){
        int answer = 0;
        for(int i = 0; i < priorities.length; i++){
            int s = priorities[i][0];
            int e = priorities[i][1];
            int box = priorities[i][2];

            int capacity = Integer.MAX_VALUE;

            for(int j = s; j < e; j++){
                capacity = Math.min(capacity, capacities[j]);
            }

            for(int l = s; l < e; l++){
                capacities[l] -= Math.min(capacity, box);
            }

            answer += Math.min(capacity, box);

        }
        return answer;
    }
}