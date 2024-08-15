import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Line implements Comparable<Line>{
        int start;
        int end;
        public Line(int start, int end){
            this.start = start;
            this.end = end;
        }
        public int compareTo(Line l){
            if(this.end != l.end) return this.end - l.end;
            return this.start - l.start;
        }
    }
    static int N,D;
    static Line[] arr;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new Line[N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if(s > e){
                int temp = s;
                s = e;
                e = temp;
            }
            arr[i] = new Line(s,e);
        }

        Arrays.sort(arr);

        st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());

        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i < N; i++) {
            pq.add(arr[i].start);
            while(!pq.isEmpty()) {
                if(pq.peek() >= arr[i].end - D) break;
                pq.poll();
            }
            answer = Math.max(answer, pq.size());
        }

        System.out.println(answer);
    }
}