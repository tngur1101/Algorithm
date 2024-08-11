import java.util.*;
import java.io.*;

/**
 * 그리디? DP?
 * 보석의 개는 30만까지 가능
 *
 * 만약 완전탐색으로 문제에 접근한다면 N*K만큼 돌기 때문에 9백억
 *
 * 보석은 무게를 기준으로 오름차순, 같은 무게라면 가격으로 내림차순하여 정렬한다.
 *
 * 배열과 우선순위큐 어떤게 더 효율적일까?
 */

public class Main {
    static class Jewel implements Comparable<Jewel>{
        int weight;
        int price;
        public Jewel(int weight, int price){
            this.weight = weight;
            this.price = price;
        }
        public int compareTo(Jewel j){
            if(this.weight == j.weight){
                return j.price - this.price;
            }
            return this.weight - j.weight;
        }
    }
    static int N,K;
    static Jewel[] jewels;
    static int[] bags;
    static PriorityQueue<Integer> candidates = new PriorityQueue<>((a,b) -> {
        return b-a;
    });

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        jewels = new Jewel[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(w,v);
        }

        bags = new int[K];
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            bags[i] = Integer.parseInt(st.nextToken());
        }

//        System.out.println("before sort jewels");
//        for(Jewel jewel: jewels){
//            System.out.println("jewel.weight: "+jewel.weight+", jewel.price: "+jewel.price);
//        }
        Arrays.sort(jewels);
//        System.out.println("after sort jewels");
//        for(Jewel jewel: jewels){
//            System.out.println("jewel.weight: "+jewel.weight+", jewel.price: "+jewel.price);
//        }

//        System.out.println("before sort bags");
//        System.out.println(Arrays.toString(bags));
        Arrays.sort(bags);
//        System.out.println("after sort bags");
//        System.out.println(Arrays.toString(bags));

        long answer = 0;
        int idx = 0;
        for(int i = 0; i < K; i++){
            for(int j = idx; j < N; j++){
                if(bags[i] < jewels[idx].weight) break;
                candidates.offer(jewels[idx++].price);
            }
//            if(bags[i] >= jewels[idx].weight) {
//                System.out.println("bags["+i+"]: "+bags[i]);
//                System.out.println("jewels["+idx+"].weight: "+jewels[idx].weight);
//                System.out.println("jewels["+idx+"].price: "+jewels[idx].price);
//                candidates.offer(jewels[idx++].price);
//            }
            if(!candidates.isEmpty()){
                int temp = candidates.poll();
//                System.out.println("temp: "+temp);
                answer += temp;
//                System.out.println(answer);
            }
        }

        System.out.println(answer);


    }
}